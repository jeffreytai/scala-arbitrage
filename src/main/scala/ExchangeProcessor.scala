import common.{ArbitragePair}
import exchange.GenericExchange
import org.knowm.xchange.currency.CurrencyPair
import slack.SlackWebhook
import utils.ExchangeUtils

class ExchangeProcessor(currencyPair: CurrencyPair, exchanges: List[GenericExchange]) {

  def analyzePrices(): Unit = {
    if (exchanges.isEmpty || exchanges.length == 1) {
      println(s"insufficient number of exchanges to compare")
      return
    }

    // Retrieve exchange with highest bid price and lowest ask price
    val exchangePrices = exchanges.map(e => (e, e.currentPrice(currencyPair)))

    val arbitrageExchange = exchangePrices.sortBy(ep => - ep._2.bidPrice).head
    val baseExchange = exchangePrices.sortBy(ep => ep._2.askPrice).head
    val arbitragePair = new ArbitragePair(baseExchange._1, baseExchange._2, arbitrageExchange._1, arbitrageExchange._2)

    // Send alert is price difference meets threshold
    val priceDifference: BigDecimal = this.priceDifference(arbitragePair)
    val usdThreshold = ExchangeUtils.usdArbitrageThreshold(currencyPair)

    if (priceDifference.compare(usdThreshold) > 0) {
      println(s"price difference: ${priceDifference}")
      println(s"usd threshold: ${usdThreshold}")

      sendSlackAlert(arbitragePair)
    }
  }

  def priceDifference(pair: ArbitragePair): BigDecimal = {
    require(pair.arbPrice.lastPrice.compare(pair.basePrice.lastPrice) > 0 &&
      !pair.arbExchange.exchange.equals(pair.baseExchange.exchange))

    pair.arbPrice.bidPrice - pair.basePrice.askPrice
  }

  def sendSlackAlert(pair: ArbitragePair): Unit = {
    val client = new SlackWebhook("arbitrage-alert")

    val message = StringContext.treatEscapes(
      f"""
        |Arbitrage opportunity for *${currencyPair.toString}*:
        |Purchase on ${pair.baseExchange.name} for $$${pair.basePrice.askPrice}%.2f and sell on ${pair.arbExchange.name} for $$${pair.arbPrice.bidPrice}%.2f
      """.stripMargin)

    client.sendMessage(message)

    client.shutdown()
  }
}
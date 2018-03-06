import common.PriceTuple
import exchange.GenericExchange
import org.knowm.xchange.currency.CurrencyPair
import utils.ExchangeUtils

import util.control.Breaks._

class ExchangeComparer(currencyPair: CurrencyPair, exchanges: List[GenericExchange]) {

  def analyzePrices(): Unit = {
    if (exchanges.isEmpty || exchanges.length == 1) {
      println(s"insufficient number of exchanges to compare")
      return
    }

    // Map each exchange to its current price
    var exchangePrices: List[(GenericExchange, PriceTuple)] = exchanges.map(e => (e, e.currentPrice(currencyPair)))
    exchangePrices = exchangePrices.sortBy(ep => ep._2.lastPrice)

    // TODO: Remove this
    exchangePrices.foreach(ep => println(ep._2))

    var head: Int = 0
    var tail: Int = exchangePrices.length - 1

    breakable {
      while (head < tail) {
        val base = exchangePrices(head)._2
        val arb = exchangePrices(tail)._2

        val priceDifference = arb.bidPrice.-(base.askPrice)
        val usdThreshold = ExchangeUtils.usdArbitrageThreshold(currencyPair)
        println(s"price difference: $priceDifference")
        println(s"usd threshold: $usdThreshold")
        if (priceDifference.compare(usdThreshold) > 0) {

        }

        break
      }
    }
  }
}
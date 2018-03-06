import exchange._
import org.knowm.xchange.currency.CurrencyPair

object Main extends App {

  val gdax: GenericExchange = new Gdax
  val gemini: GenericExchange = new Gemini
  val bitstamp: GenericExchange = new Bitstamp
  val cexio: GenericExchange = new Cexio
  val kraken: GenericExchange = new Kraken

  val exchanges = List(gdax, gemini, bitstamp, cexio, kraken)

  val btcPriceAnalyzer = new ExchangeProcessor(CurrencyPair.BTC_USD, exchanges)
  btcPriceAnalyzer.analyzePrices()

  val ethPriceAnalyzer = new ExchangeProcessor(CurrencyPair.ETH_USD, exchanges)
  ethPriceAnalyzer.analyzePrices()
}

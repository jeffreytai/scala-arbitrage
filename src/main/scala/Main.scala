import exchange._
import org.knowm.xchange.currency.CurrencyPair

object Main extends App {

  val gdax: GenericExchange = new Gdax
  val gemini: GenericExchange = new Gemini
  val bitstamp: GenericExchange = new Bitstamp
  val cexio: GenericExchange = new Cexio
  val kraken: GenericExchange = new Kraken

  val exchanges: List[GenericExchange] = List(gdax, gemini, bitstamp, cexio, kraken)
  val comparer: ExchangeComparer = new ExchangeComparer(CurrencyPair.BTC_USD, exchanges)
  comparer.analyzePrices()
}

package exchange

import common.PriceTuple
import org.knowm.xchange.Exchange
import org.knowm.xchange.currency.CurrencyPair
import utils.ExchangeUtils

trait GenericExchange {

  val exchange: Exchange
  val name: String

  def currentPrice(currencyPair: CurrencyPair): PriceTuple = {
    ExchangeUtils.exchangePrice(currencyPair, exchange)
  }
}

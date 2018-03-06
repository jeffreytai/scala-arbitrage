package utils

import common.PriceTuple
import org.knowm.xchange.Exchange
import org.knowm.xchange.currency.CurrencyPair
import org.knowm.xchange.dto.marketdata.Ticker

object ExchangeUtils {

  val USD_ARBITRAGE_THRESHOLD = "usd_arbitrage_threshold"
  val PERCENTAGE_ARBITRAGE_THRESHOLD = "percentage_arbitrage_threshold"

  val constants = Map(
    CurrencyPair.BTC_USD -> Map(
      (USD_ARBITRAGE_THRESHOLD, 1000.0),
      (PERCENTAGE_ARBITRAGE_THRESHOLD, 0.10)
    ),
    CurrencyPair.ETH_USD -> Map(
      (USD_ARBITRAGE_THRESHOLD, 120.0),
      (PERCENTAGE_ARBITRAGE_THRESHOLD, 0.10)
    )
  )

  def usdArbitrageThreshold(currencyPair: CurrencyPair): Double = {
    if (constants.contains(currencyPair) &&
      constants.apply(currencyPair).contains(USD_ARBITRAGE_THRESHOLD)) constants.apply(currencyPair).apply(USD_ARBITRAGE_THRESHOLD)
    else Double.MaxValue
  }

  def percentageArbitrageThreshold(currencyPair: CurrencyPair): Double = {
    if (constants.contains(currencyPair) &&
      constants.apply(currencyPair).contains(PERCENTAGE_ARBITRAGE_THRESHOLD)) constants.apply(currencyPair).apply(PERCENTAGE_ARBITRAGE_THRESHOLD)
    else Double.MaxValue
  }

  /**
    * Retrieve the last price, bid price, and ask price for a currency pair
    * @param currencyPair
    * @param exchange
    * @return tuple of 3 prices
    */
  def exchangePrice(currencyPair: CurrencyPair, exchange: Exchange): PriceTuple = {
    val ticker: Ticker = exchange.getMarketDataService.getTicker(currencyPair)
    new PriceTuple(ticker.getLast, ticker.getBid, ticker.getAsk)
  }
}

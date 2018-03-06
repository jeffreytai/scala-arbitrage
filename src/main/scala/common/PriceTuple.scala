package common

class PriceTuple(last: BigDecimal, bid: BigDecimal, ask: BigDecimal) {
  val lastPrice: BigDecimal = last
  val bidPrice: BigDecimal = bid
  val askPrice: BigDecimal = ask

  override def toString = "lastPrice: " + lastPrice + ", bidPrice: " + bidPrice + ", askPrice: " + askPrice
}

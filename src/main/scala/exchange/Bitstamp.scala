package exchange

import org.knowm.xchange.{Exchange, ExchangeFactory}
import org.knowm.xchange.bitstamp.BitstampExchange

class Bitstamp extends GenericExchange {

  val exchange: Exchange = ExchangeFactory.INSTANCE.createExchange(new BitstampExchange().getClass)
  val name = "Bitstamp"

}

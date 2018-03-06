package exchange

import org.knowm.xchange.kraken.KrakenExchange
import org.knowm.xchange.{Exchange, ExchangeFactory}

class Kraken extends GenericExchange {

  val exchange: Exchange = ExchangeFactory.INSTANCE.createExchange(new KrakenExchange().getClass)
  val name = "Kraken"
}

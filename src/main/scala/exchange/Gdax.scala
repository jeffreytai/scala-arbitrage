package exchange

import org.knowm.xchange.{Exchange, ExchangeFactory}
import org.knowm.xchange.gdax.GDAXExchange

class Gdax extends GenericExchange {

  val exchange: Exchange = ExchangeFactory.INSTANCE.createExchange(new GDAXExchange().getClass)
  val name = "GDAX"
}

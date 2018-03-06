package exchange

import org.knowm.xchange.{Exchange, ExchangeFactory}
import org.knowm.xchange.gemini.v1.GeminiExchange

class Gemini extends GenericExchange {

  val exchange: Exchange = ExchangeFactory.INSTANCE.createExchange(new GeminiExchange().getClass)

}

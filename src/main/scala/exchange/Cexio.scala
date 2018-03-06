package exchange

import org.knowm.xchange.{Exchange, ExchangeFactory}
import org.knowm.xchange.cexio.CexIOExchange

class Cexio extends GenericExchange {

  val exchange: Exchange = ExchangeFactory.INSTANCE.createExchange(new CexIOExchange().getClass)
  val name = "CexIO"
}

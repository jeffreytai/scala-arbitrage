package common

import exchange.GenericExchange

class ArbitragePair(src: GenericExchange, srcPrice: PriceTuple, dest: GenericExchange, destPrice: PriceTuple) {

  val baseExchange = src
  val basePrice = srcPrice
  val arbExchange = dest
  val arbPrice = destPrice

}

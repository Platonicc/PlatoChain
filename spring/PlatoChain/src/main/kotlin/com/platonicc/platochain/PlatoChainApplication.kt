package com.platonicc.platochain

import com.platonicc.platochain.blockchain.Chain.Companion.localChain
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class PlatoChainApplication

fun main(args: Array<String>) {
	runApplication<PlatoChainApplication>(*args)
}

package com.platonicc.PlatoChain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class PlatoChainApplication

fun main(args: Array<String>) {
	runApplication<PlatoChainApplication>(*args)
}
@RestController
class TestApp{
	@GetMapping(value = ["/test/{data}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
	fun testFun(@PathVariable data:String):String{
		val obj = Block.GenesisBlock()
		obj.printBlock()
		return obj.getBlockAsString()
	}
}

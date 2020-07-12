package com.platonicc.platochain.test

import com.google.gson.Gson
import com.platonicc.platochain.blockchain.Block
import com.platonicc.platochain.blockchain.BlockData
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PostTest {
        @GetMapping(value = ["/post/test/{sender}/{amount}/{message}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
        fun testFun(@PathVariable sender: String, @PathVariable amount: String, @PathVariable message: String): String {
            val obj = Block.Genesis.create()
            val obj2 = Block.Tools.mineBlock(
                                            BlockData(sender, "aman-pc",
                                            amount.toDouble(), message),
                                            obj.blockHash)
            obj.printBlockDetails()
            obj2.printBlockDetails()
            return Gson().toJson(arrayOf(obj.getBlockMap(),obj2.getBlockMap()))
        }
}
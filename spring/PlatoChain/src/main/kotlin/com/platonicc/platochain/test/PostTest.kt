package com.platonicc.platochain.test

import com.google.gson.Gson
import com.platonicc.platochain.blockchain.Block
import com.platonicc.platochain.blockchain.BlockData
import com.platonicc.platochain.blockchain.Chain
import com.platonicc.platochain.blockchain.Chain.Companion.localChain
import com.platonicc.platochain.blockchain.Chain.Companion.isValidChain
import com.platonicc.platochain.blockchain.Chain.Companion.replaceChain
import com.platonicc.platochain.blockchain.JSONStringToChain
import org.jetbrains.annotations.TestOnly
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PostTest {

    var chain2 = localChain

        @TestOnly
        @GetMapping(value = ["/post/test/{sender}/{amount}/{message}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])

        fun testFun(@PathVariable sender: String, @PathVariable amount: String, @PathVariable message: String): String {

            Chain.addBlock(BlockData(sender, "aman-pc", amount.toDouble(), message))
            for(i in localChain){
                i.printBlockDetails()
            }
            println(isValidChain(JSONStringToChain(Gson().toJson(localChain))))
            println(localChain[0].getStruct() == Block.Genesis.getStruct())
            return Gson().toJson(localChain)
        }
}
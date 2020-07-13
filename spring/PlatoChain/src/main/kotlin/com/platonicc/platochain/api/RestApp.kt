package com.platonicc.platochain.api

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.platonicc.platochain.blockchain.BlockData
import com.platonicc.platochain.blockchain.Chain
import com.platonicc.platochain.blockchain.Chain.Companion.localChain
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

class RestApp {
    @RestController
    object GET{

        @GetMapping(value =[ "/chain", "/Chain", "/getChain", "/GetChain","/getchain"])
        fun sendChain(): String{
            return ObjectMapper().writeValueAsString(localChain)
        }
    }

    @RestController
    object POST{
        @PostMapping(value=["/mine","/Mine", "/minBlock", "/MineBlock", "mineblock"])
        fun minBlock(@RequestBody dataString:String):RedirectView{
            Chain.addBlock(ObjectMapper().readValue(dataString, object: TypeReference<BlockData>(){}))
            return RedirectView("/chain")
        }
    }

}
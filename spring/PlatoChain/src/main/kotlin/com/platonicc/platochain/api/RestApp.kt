package com.platonicc.platochain.api

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.JsonArray
import com.platonicc.platochain.blockchain.Block
import com.platonicc.platochain.blockchain.Chain.Companion.localChain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

class RestApp {
    @RestController
    object GET{

        @GetMapping(value =[ "/chain", "/Chain", "/getChain", "/getchain"])
        fun sendChain(): String{
            return ObjectMapper().writeValueAsString(localChain)
        }
        
    }

    object POST{

    }

}
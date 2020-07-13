package com.platonicc.platochain.blockchain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.security.MessageDigest

fun getBlockHash(timestamp: String?, data:BlockData?, previousHash:String?): String {
    val bytes = "$timestamp$data$previousHash".toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}

fun getBlockHash(block:Block): String {
    val bytes = "${block.timeStamp}${block.data}${block.previousHash}".toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}

fun JSONStringToChain(dataString: String):List<Block>{
   return ObjectMapper().readValue(dataString, object: TypeReference<List<Block>>(){})
}
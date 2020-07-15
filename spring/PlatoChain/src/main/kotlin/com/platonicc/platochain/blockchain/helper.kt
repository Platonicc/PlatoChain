package com.platonicc.platochain.blockchain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.security.MessageDigest

// Returns the SHA256 of the [TIME_STAMP, DATA, PREVIOUS_HASH] provided
fun getBlockHash(index: Int?, proof: Int?,timestamp: String?, data:BlockData?, previousHash:String?): String {
    val bytes = "$index$proof$timestamp$data$previousHash".toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}

// Does the same as the previous function but you just need to pass the whole block instead of each data separately!
// This function make the task easy for checking hash of pre-created blocks
fun getBlockHash(block:Block): String {
    val bytes = "${block.index}${block.proof}${block.timeStamp}${block.data}${block.previousHash}".toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}

// Converts and return the incoming JSON data of a Chain to Kotlin list of blocks
fun JSONStringToChain(dataString: String):List<Block>{
   return ObjectMapper().readValue(dataString, object: TypeReference<List<Block>>(){})
}
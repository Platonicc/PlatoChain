package com.platonicc.platochain.blockchain

import java.security.MessageDigest
import java.sql.Timestamp

fun getBlockHash(timestamp: Timestamp?, data:BlockData?, lastHash:String?): String {
    val bytes = "$timestamp$data$lastHash".toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}
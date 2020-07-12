package com.platonicc.platochain.blockchain

import com.fasterxml.jackson.databind.util.JSONPObject
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonSerializer
import com.google.gson.reflect.TypeToken
import java.sql.Timestamp

class Chain {
    companion object {

        var chain: MutableList<Block> = mutableListOf(Block.Genesis.get())

        fun addBlock(data: BlockData): Block {
            val newBlock = Block.Tools.mineBlock(data, chain[chain.size - 1].blockHash)
            chain.add(newBlock)
            return newBlock
        }
    }
}

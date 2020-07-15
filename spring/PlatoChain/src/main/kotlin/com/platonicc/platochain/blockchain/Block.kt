package com.platonicc.platochain.blockchain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.platonicc.platochain.blockchain.Chain.Companion.localChain
import java.math.BigInteger
import java.sql.Timestamp

class Block @JsonCreator constructor(
        @JsonProperty("index") var index: Int?,
        @JsonProperty("proof") var proof: Int?,
        @JsonProperty("data") var data: BlockData?,
        @JsonProperty("timeStamp") var timeStamp: String?,
        @JsonProperty("previousHash") var previousHash: String?,
        @JsonProperty("blockHash") var blockHash: String?) {

    // Returns the structure of the present data for comparison and other tasks
    @JsonIgnore
      fun getStruct(): Tools.BlockStruct {
        return Tools.BlockStruct(index, proof,data, timeStamp, previousHash, blockHash)
    }

    // Prints the whole structure of the block to the terminal
    @JsonIgnore
    fun printBlockDetails() {
        println("----------Block Data--------------")
        println("Data:          -->> ${this.data}")
        println("Time Stamp:    -->> ${this.timeStamp}")
        println("Previous Hash: -->> ${this.previousHash}")
        println("Block Hash:    -->> ${this.blockHash}")
    }

   /* @JsonIgnore
    fun getBlockMap(): HashMap<String, Any> {
        val blockMap = HashMap<String, Any>()
        blockMap["data"] = data ?: BlockData(null, null, null, null)
        blockMap["Time Stamp"] = timeStamp ?: ""
        blockMap["Previous Hash"] = previousHash ?: ""
        blockMap["Block Hash"] = blockHash ?: ""
        return blockMap
    }*/



    object Tools{

        // Data class for the structure of the class
       data class BlockStruct constructor(
                var index: Int?,
                var proof: Int?,
                var data: BlockData?,
                var timeStamp: String?,
                var previousHash: String?,
                var blockHash: String?)

        // This function is responsible for mining the blocks
        @JsonIgnore
        fun mineBlock(data: BlockData, previousHash: String?): Block {
            val index = localChain.count()+1
            val timeStamp = getTimeStamp().toString()
            //TODO Add Proof of Work
            return Block(index,0,data,timeStamp, previousHash, getBlockHash(index,0,timeStamp,data,previousHash))
        }

        // Returns the current timestamp
        @JsonIgnore
        fun getTimeStamp(): Timestamp {
            return Timestamp(System.currentTimeMillis())
        }

    }

    //TODO (Change Genesis Default values)

    // Genesis Block Data Structure
    object Genesis {
        @JsonIgnore
        private val data = BlockData("foo", "boo", 0.0, "This is the genesis block!")
        @JsonIgnore
        private const val timeStamp = "null"
        @JsonIgnore
        private const val previousHash = "---"
        @JsonIgnore

        // Returns the genesis block
        fun get(): Block {
            //TODO Calculate Proof of Work
            return Block(1,0,data, "null", previousHash, getBlockHash(1,0,timeStamp, data, previousHash))
        }

        // Returns the structure of the genesis block
        fun getStruct(): Tools.BlockStruct {
            //TODO Calculate Proof of Work
            return Tools.BlockStruct(1,0,data, timeStamp, previousHash, getBlockHash(1,0,timeStamp, data, previousHash))
        }
    }
}
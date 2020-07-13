package com.platonicc.platochain.blockchain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

class Block @JsonCreator constructor(@JsonProperty("data") var data: BlockData?,
                                                 @JsonProperty("timeStamp") var timeStamp: String?,
                                                 @JsonProperty("previousHash") var previousHash: String?,
                                                 @JsonProperty("blockHash") var blockHash: String?) {

    @JsonIgnore
      fun getStruct(): Tools.BlockStruct {
        return Tools.BlockStruct(data, timeStamp, previousHash, blockHash)
    }

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

       data class BlockStruct @JsonCreator constructor(@JsonProperty("data") var data: BlockData?,
                                                        @JsonProperty("timeStamp") var timeStamp: String?,
                                                        @JsonProperty("previousHash") var previousHash: String?,
                                                        @JsonProperty("blockHash") var blockHash: String?)

        @JsonIgnore
        fun mineBlock(data: BlockData, previousHash: String?): Block {
            val timeStamp = getTimeStamp().toString()
            return Block(data,timeStamp, previousHash, getBlockHash(timeStamp,data,previousHash))
        }

        @JsonIgnore
        fun getTimeStamp(): Timestamp {
            return Timestamp(System.currentTimeMillis())
        }

    }

    //TODO (Change Genesis Default values)
    object Genesis {
        @JsonIgnore
        private val data = BlockData("foo", "boo", 0.0, "This is the genesis block!")
        @JsonIgnore
        private const val timeStamp = "null"
        @JsonIgnore
        private const val previousHash = "---"
        @JsonIgnore
        fun get(): Block {
            return Block(data, "null", previousHash, getBlockHash(timeStamp, data, previousHash))
        }

        fun getStruct(): Tools.BlockStruct {
            return Tools.BlockStruct(data, timeStamp, previousHash, getBlockHash(timeStamp, data, previousHash))
        }
    }
}
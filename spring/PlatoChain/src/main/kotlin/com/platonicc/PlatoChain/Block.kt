package com.platonicc.PlatoChain

import java.sql.Timestamp
import kotlin.time.TimeSource

class Block(data:String?, timestamp: Timestamp?=BlockTools.getTimeStamp(), previousHash: String?, blockHash: String?){

    var data: String? = data
    var timestamp: Timestamp? = timestamp
    var previousHash: String? = previousHash
    var blockHash: String? = blockHash

    fun printBlock(){
        println("----------Block Data--------------")
        println("Data:          -->> ${this.data}")
        println("Time Stamp:    -->> ${this.timestamp}")
        println("Previous Hash: -->> ${this.previousHash}")
        println("Block Hash:    -->> ${this.blockHash}")
    }

    fun getBlockAsString(): String{
        return "----------Block Data--------------\n" +
                "Data:          -->> ${this.data}\n" +
                "Time Stamp:    -->> ${this.timestamp}\n" +
                "Previous Hash: -->> ${this.previousHash}\n" +
                "Block Hash:    -->> ${this.blockHash}\n"
    }

    companion object{
        fun GenesisBlock(): Block{
            return Block("This is genesis", BlockTools.getTimeStamp(), "----", "foo")
        }
    }

    object BlockTools{
        fun getTimeStamp(): Timestamp{
            return Timestamp(System.currentTimeMillis())
        }
    }

}
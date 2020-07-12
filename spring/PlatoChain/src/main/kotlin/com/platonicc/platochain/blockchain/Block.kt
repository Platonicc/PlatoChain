package com.platonicc.platochain.blockchain

import java.security.MessageDigest
import java.sql.Timestamp

class Block(var data: BlockData, var timestamp: Timestamp? = Tools.getTimeStamp(), var previousHash: String?){
    var blockHash: String?
    init{
        blockHash = hashBlock()
    }
    fun printBlockDetails(){
        println("----------Block Data--------------")
        println("Data:          -->> ${this.data}")
        println("Time Stamp:    -->> ${this.timestamp}")
        println("Previous Hash: -->> ${this.previousHash}")
        println("Block Hash:    -->> ${this.blockHash}")
    }

    fun getBlockDetails(): String{
        return "----------Block Data--------------\n" +
                "Data:          -->> ${this.data}\n" +
                "Time Stamp:    -->> ${this.timestamp}\n" +
                "Previous Hash: -->> ${this.previousHash}\n" +
                "Block Hash:    -->> ${this.blockHash}\n"
    }

    fun getBlockMap(): HashMap<String, Any>{
        val blockMap = HashMap<String, Any>()
        blockMap["data"] = data
        blockMap["Time Stamp"] = timestamp?:""
        blockMap["Previous Hash"] = previousHash?:""
        blockMap["Block Hash"] = blockHash?:""
        return blockMap
    }

    private fun hashBlock(): String {
        val bytes = this.toString().toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    object Tools{
        fun mineBlock(data:BlockData,previousHash: String?): Block{
            return Block(data, getTimeStamp(), previousHash)
        }

        fun getTimeStamp(): Timestamp{
            return Timestamp(System.currentTimeMillis()) }

    }

    //TODO (Change Genesis Default values)
    object Genesis{
        private val data = BlockData("foo", "boo", 0.0, "This is the genesis block!")
        private const val previousHash = "---"

        fun create(): Block {
            return Block(data, Tools.getTimeStamp(), previousHash) }
    }

}
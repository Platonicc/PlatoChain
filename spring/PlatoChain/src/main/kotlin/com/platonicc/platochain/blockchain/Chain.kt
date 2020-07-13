package com.platonicc.platochain.blockchain

class Chain {
    companion object {

        var localChain: MutableList<Block> = mutableListOf(Block.Genesis.get())


        fun addBlock(data: BlockData): Block {
            val newBlock = Block.Tools.mineBlock(data, localChain[localChain.size - 1].blockHash)
            localChain.add(newBlock)
            return newBlock
        }

        fun isValidChain(guestChain:List<Block>): Boolean{

            if(guestChain[0].getStruct() != Block.Genesis.getStruct())
                return false
            for(blockElement in guestChain.filterIndexed{ index, _ ->  index!=0}){
                val previousBlockElement = guestChain[guestChain.indexOf(blockElement)-1]
                if(blockElement.previousHash != previousBlockElement.blockHash ||
                        blockElement.blockHash != getBlockHash(blockElement))
                    return false
            }
            return true
        }

        fun replaceChain(dataString: String){
            val guestChain = JSONStringToChain(dataString)
            if(guestChain.count()>= localChain.count())
                if(isValidChain(guestChain)){
                    localChain = guestChain as MutableList<Block>
                    println("Local chain replaced!")
                }
                else
                    println("Invalid chain! Discarding the chain!")
            else
                println("Smaller chain! No replacement!")


        }
    }
}

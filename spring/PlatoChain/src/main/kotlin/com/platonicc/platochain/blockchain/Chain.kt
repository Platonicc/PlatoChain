package com.platonicc.platochain.blockchain

class Chain {
    companion object {

        // The localChain is the data structure that will store your local blockchain
        var localChain: MutableList<Block> = mutableListOf(Block.Genesis.get())

        // Adds a new block after mining it via the minBlock() to the localChain
        fun addBlock(data: BlockData): Block {
            val newBlock = Block.Tools.mineBlock(data, getLastBlock().blockHash)
            localChain.add(newBlock)
            return newBlock
        }

        // Fetches the last block element of the localChain
        private fun getLastBlock(): Block = localChain[localChain.count() - 1]

        // Check if the incoming chain is valid or not
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

        //Replaces the localChain with the incoming chain after checking its validity via the isValidChain method
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

package com.platonicc.platochain.blockchain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

// Data Class of the Block
// Will be modified according to the need of the Application
data class BlockData @JsonCreator constructor(@JsonProperty("sender")val sender:String?,
                                              @JsonProperty("receiver")val receiver:String?,
                                              @JsonProperty("amount")val amount: Double?,
                                              @JsonProperty("message")var message: String?)
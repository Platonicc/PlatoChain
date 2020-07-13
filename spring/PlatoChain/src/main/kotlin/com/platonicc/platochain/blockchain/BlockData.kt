package com.platonicc.platochain.blockchain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class BlockData @JsonCreator constructor(@JsonProperty("sender")val sender:String?,
                                              @JsonProperty("receiver")val receiver:String?,
                                              @JsonProperty("amount")val amount: Double?,
                                              @JsonProperty("message")var message: String?)
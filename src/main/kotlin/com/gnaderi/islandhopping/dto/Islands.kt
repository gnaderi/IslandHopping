package com.gnaderi.islandhopping.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("islands")
class Islands {
    @JsonProperty("islands")
    var islands: List<Island>

    constructor(islands: List<Island>) {
        this.islands = islands
    }
}

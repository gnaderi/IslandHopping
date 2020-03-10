package com.gnaderi.islandhopping.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.gnaderi.islandhopping.dto.Tile

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("tiles")
class Attributes(tiles: List<Tile>) {

    @JsonProperty("tiles")
    var tiles: List<Tile>? = tiles

}

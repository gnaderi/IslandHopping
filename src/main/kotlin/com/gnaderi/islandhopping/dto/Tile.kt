package com.gnaderi.islandhopping.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.gnaderi.islandhopping.common.TileType
import com.gnaderi.islandhopping.common.TileTypeDeserializer
import com.gnaderi.islandhopping.common.TileTypeSerializer

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("x", "y", "type")
class Tile {
    @JsonIgnore
    var id: Int? = null
    @JsonProperty("x")
    var x: Int? = null
    @JsonProperty("y")
    var y: Int? = null
    @JsonSerialize(using = TileTypeSerializer::class)
    @JsonDeserialize(using = TileTypeDeserializer::class)
    @JsonProperty("type")
    lateinit var type: TileType

    constructor() {}

    constructor(x: Int?, y: Int?, type: TileType) {
        this.x = x
        this.y = y
        this.type = type
    }

    constructor(id: Int?, x: Int?, y: Int?, type: TileType) {
        this.id = id
        this.x = x
        this.y = y
        this.type = type
    }
}

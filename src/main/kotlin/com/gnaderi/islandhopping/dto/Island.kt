package com.gnaderi.islandhopping.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "tiles")
class Island {
    @JsonProperty("id")
    var id: Int? = null
    @JsonProperty("mapId")
    var mapId: Int? = null
    @JsonProperty("tiles")
    var tiles: MutableList<Tile> = ArrayList()

    constructor()
    constructor(id: Int, mapId: Int) {
        this.id = id
        this.mapId = mapId
    }

    constructor(id: Int, mapId: Int, tiles: MutableList<Tile>) {
        this.id = id
        this.mapId = mapId
        this.tiles = tiles
    }
}

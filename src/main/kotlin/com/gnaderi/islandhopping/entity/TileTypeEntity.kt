package com.gnaderi.islandhopping.entity

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "TILE_TYPE")
class TileTypeEntity {
    @get:Id
    @get:Column(name = "ID", nullable = false)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "TYPE", nullable = false, length = 255)
    var type: String? = null

    constructor() {}

    constructor(id: Int?, type: String) {
        this.id = id
        this.type = type
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as TileTypeEntity?
        return id == that!!.id && type == that.type
    }

    override fun hashCode(): Int {

        return Objects.hash(id, type)
    }
}

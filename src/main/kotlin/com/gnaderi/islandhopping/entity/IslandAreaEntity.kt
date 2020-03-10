package com.gnaderi.islandhopping.entity

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "ISLAND_AREA")
class IslandAreaEntity {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "ID", nullable = false)
    var id: Int? = null
    @get:ManyToOne
    @get:JoinColumn(name = "ISLAND_ID", referencedColumnName = "ID", nullable = false)
    var islandByIslandId: IslandEntity? = null
    @get:ManyToOne
    @get:JoinColumn(name = "TILE_ID", referencedColumnName = "ID", nullable = false)
    var tileByTileId: TileEntity? = null

    constructor() {}

    constructor(islandByIslandId: IslandEntity, tileByTileId: TileEntity) {
        this.islandByIslandId = islandByIslandId
        this.tileByTileId = tileByTileId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as IslandAreaEntity?
        return id == that!!.id
    }

    override fun hashCode(): Int {

        return Objects.hash(id)
    }
}

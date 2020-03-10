package com.gnaderi.islandhopping.entity

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "TILE")
class TileEntity {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "ID", nullable = false)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "X", nullable = false)
    var x: Int? = null
    @get:Basic
    @get:Column(name = "Y", nullable = false)
    var y: Int? = null
    @get:OneToMany(mappedBy = "tileByTileId")
    var islandAreasById: Collection<IslandAreaEntity>? = null
    @get:ManyToOne
    @get:JoinColumn(name = "TYPE_ID", referencedColumnName = "ID", nullable = false)
    var tileTypeByTypeId: TileTypeEntity? = null
    @get:ManyToOne
    @get:JoinColumn(name = "MAP_ID", referencedColumnName = "ID", nullable = false)
    var mapId: MapEntity? = null

    val map: Int?
        @Transient
        get() = this.mapId!!.id

    constructor() {}

    constructor(x: Int?, y: Int?, tileTypeByTypeId: TileTypeEntity, mapId: MapEntity) {
        this.x = x
        this.y = y
        this.tileTypeByTypeId = tileTypeByTypeId
        this.mapId = mapId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as TileEntity?
        return id == that!!.id &&
                x == that.x &&
                y == that.y
    }

    override fun hashCode(): Int {

        return Objects.hash(id, x, y)
    }
}

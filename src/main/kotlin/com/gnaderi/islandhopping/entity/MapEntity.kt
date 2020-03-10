package com.gnaderi.islandhopping.entity

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "MAP")
class MapEntity {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "ID", nullable = false)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "NAME", nullable = false, length = 255)
    var name: String? = null
    @get:OneToMany(mappedBy = "mapByMapId")
    var islandsById: Collection<IslandEntity>? = null
    @get:OneToMany(mappedBy = "mapId")
    var tilesById: Collection<TileEntity>? = null

    constructor() {}

    constructor(name: String) {
        this.name = name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val mapEntity = other as MapEntity?
        return id == mapEntity!!.id && name == mapEntity.name
    }

    override fun hashCode(): Int {

        return Objects.hash(id, name)
    }
}

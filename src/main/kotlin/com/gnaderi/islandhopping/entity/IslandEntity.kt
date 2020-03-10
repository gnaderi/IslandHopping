package com.gnaderi.islandhopping.entity

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "ISLAND")
class IslandEntity {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "ID", nullable = false)
    var id: Int? = null
    @get:ManyToOne
    @get:JoinColumn(name = "MAP_ID", referencedColumnName = "ID", nullable = false)
    var mapByMapId: MapEntity? = null
    @get:OneToMany(mappedBy = "islandByIslandId")
    var islandAreasById: Collection<IslandAreaEntity>? = null

    constructor(mapByMapId: MapEntity) {
        this.mapByMapId = mapByMapId
    }

    constructor()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as IslandEntity?
        return id == that!!.id
    }

    override fun hashCode(): Int {

        return Objects.hash(id)
    }
}

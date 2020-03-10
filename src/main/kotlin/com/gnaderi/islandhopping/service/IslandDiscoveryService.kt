package com.gnaderi.islandhopping.service

import com.gnaderi.islandhopping.dto.Island
import com.gnaderi.islandhopping.entity.MapEntity
import com.gnaderi.islandhopping.common.ApiaryMap

interface IslandDiscoveryService {

    fun getMapFromAdvapi(): ApiaryMap?
    fun createMap(): MapEntity

    fun findIslands(): List<Island>

    fun findIslandById(id: Int?): Island

    fun mapsAsAsciiArt(): String

    fun mapAsAsciiArt(id: Int): String
}

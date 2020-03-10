package com.gnaderi.islandhopping.service

import com.gnaderi.islandhopping.dto.Island
import com.gnaderi.islandhopping.entity.TileEntity

interface IslandHopingService {
    fun discoverIslands(tilesEntities: MutableList<TileEntity>, mapId: Int): List<Island>
}

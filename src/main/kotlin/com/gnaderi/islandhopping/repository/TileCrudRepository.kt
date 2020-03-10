package com.gnaderi.islandhopping.repository

import com.gnaderi.islandhopping.entity.TileEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TileCrudRepository : CrudRepository<TileEntity, Int> {
    @Query(value = "SELECT * FROM Tile t WHERE t.MAP_ID=:mapId", nativeQuery = true)
    fun findTileEntitiesByMapId(@Param(value = "mapId") mapId: Int?): List<TileEntity>

}

package com.gnaderi.islandhopping.common

import com.gnaderi.islandhopping.dto.Island
import com.gnaderi.islandhopping.dto.Tile
import com.gnaderi.islandhopping.entity.IslandEntity
import com.gnaderi.islandhopping.entity.TileEntity
import com.gnaderi.islandhopping.exception.UnsupportedTileTypeException
import org.springframework.stereotype.Component
import java.util.*

@Component
class EntityToDtoConverter {
    fun convertIslandEntityToIsland(islandEntity: IslandEntity): Island {
        val tiles: ArrayList<Tile> = ArrayList()
        islandEntity.islandAreasById!!.forEach { e ->
            tiles.add(Tile(e.tileByTileId!!.x, e.tileByTileId!!.y,
                    TileType.valueOf(e.tileByTileId!!.tileTypeByTypeId!!.type!!)))
        }
        return Island(islandEntity.id!!, islandEntity.mapByMapId!!.id!!, tiles)
    }

    fun convertIslandEntities(islandEntities: List<IslandEntity>): List<Island> {
        val islands = ArrayList<Island>()
        islandEntities.forEach { e -> islands.add(convertIslandEntityToIsland(e)) }
        return islands
    }

    fun convertTileEntityToTiles(tiles: Collection<TileEntity>): Array<Array<Tile>> {
        val rowSize = tiles.stream().mapToInt { it!!.x!! }.max().asInt
        val colSize = tiles.stream().mapToInt { it!!.y!! }.max().asInt
        val tilesArray = Array(rowSize) {  Array(colSize) { Tile() } }
        tiles.forEach { e ->
            try {
                tilesArray[e?.x!! - 1][e.y!! - 1] = Tile(e.id, e.x!! - 1, e.y!! - 1, TileType.valueOf(e.tileTypeByTypeId!!.type!!.toUpperCase()))
            } catch (e1: UnsupportedTileTypeException) {
                e1.printStackTrace()
            }
        }
        return tilesArray
    }

    fun convertTileEntitiesListToTiles(tiles: MutableList<TileEntity>): MutableList<Tile> {
        val tileList = ArrayList<Tile>()

        tiles.forEach { e -> tileList.add(convertTileEntity2Tile(e)) }
        return tileList
    }

    private fun convertTileEntity2Tile(entity: TileEntity): Tile {
        return Tile(entity.id, entity.x, entity.y, TileType.valueOf(entity.tileTypeByTypeId!!.type!!))
    }

    fun convertMapTilesToAsciiArt(mapId:Int,tileEntities: List<TileEntity>): String {
        val tiles = convertTileEntityToTiles(tileEntities)
        val rowLen = tiles.size
        val colLen = tiles[0].size
        val sb = StringBuilder()
        sb.append("------------------------------Map[$mapId]------------------------------\n")
        for (i in 0 until rowLen) {
            for (j in 0 until colLen) {
                sb.append(if (tiles[i][j]?.type === TileType.LAND) "*" else "~")
            }
            sb.append(" \n")
        }
        return sb.toString()
    }
}

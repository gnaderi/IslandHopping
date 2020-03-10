package com.gnaderi.islandhopping.service

import com.gnaderi.islandhopping.dto.Island
import com.gnaderi.islandhopping.dto.Tile
import com.gnaderi.islandhopping.entity.TileEntity
import com.gnaderi.islandhopping.common.TileType
import com.gnaderi.islandhopping.common.EntityToDtoConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class IslandHoping : IslandHopingService {

    @Autowired
    private lateinit var entityToDtoConverter: EntityToDtoConverter

    private inner class HoppingPointer {
        internal var row = 0
        internal var col = 0
    }

    override fun discoverIslands(tilesEntities: MutableList<TileEntity>, mapId: Int): List<Island> {
        val tiles = entityToDtoConverter.convertTileEntityToTiles(tilesEntities)
        val rowLen = tiles.size
        val colLen = tiles[0].size
        var islandCounter = 0
        val islands = ArrayList<Island>()
        if (rowLen < 2 && colLen < 2) {
            islands.add(Island(islandCounter++, mapId, entityToDtoConverter.convertTileEntitiesListToTiles(tilesEntities)))
            return islands
        }
        val visitedTiles = Array(rowLen) { Array(colLen, { false }) }
        var currentIsland = Island()
        val currentHop = HoppingPointer()

        for (row in 0 until rowLen) {
            for (col in 0 until colLen) {
                if (!visitedTiles[row][col]) {
                    if (tiles[row][col]!!.type === TileType.WATER) {
                        visitedTiles[row][col] = true
                        continue
                    }
                    currentHop.row = row
                    currentHop.col = col
                    visitedTiles[currentHop.row][currentHop.col] = true
                    expandIsland(tiles, visitedTiles, currentHop, currentIsland)
                    islands.add(currentIsland)
                    currentIsland = Island(islandCounter++, mapId)
                }
            }
        }

        return islands
    }

    private fun expandIsland(tiles: Array<Array<Tile>>, visitedTiles: Array<Array<Boolean>>, hoppingPointer: HoppingPointer, currentIsland: Island) {
        val stackHoppingPointer = Stack<HoppingPointer>()
        stackHoppingPointer.push(hoppingPointer)

        while (!stackHoppingPointer.empty()) {
            var currentHoppingPointer = stackHoppingPointer.pop()
            val hopedTile = tiles[currentHoppingPointer.row][currentHoppingPointer.col]
            currentIsland.tiles.add(hopedTile)
            while (hasNextTile(tiles, visitedTiles, currentHoppingPointer)) {
                val nextHoppingPointer = hopToNext(tiles, visitedTiles, currentHoppingPointer)
                visitedTiles[nextHoppingPointer.row][nextHoppingPointer.col] = true
                stackHoppingPointer.push(currentHoppingPointer)
                currentHoppingPointer = nextHoppingPointer
            }
        }
    }

    private fun hopToNext(tiles: Array<Array<Tile>>, visitedTiles: Array<Array<Boolean>>, currentHoppingPointer: HoppingPointer): HoppingPointer {
        val num_row = visitedTiles.size
        val num_col = visitedTiles[0].size
        val x = currentHoppingPointer.row
        val y = currentHoppingPointer.col

        val nextHoppingPointer = HoppingPointer()

        if (y < num_col - 1 && !visitedTiles[x][y + 1] && tiles[x][y + 1].type === TileType.LAND) {
            visitedTiles[x][y + 1] = visitedTiles[x][y]
            nextHoppingPointer.row = x
            nextHoppingPointer.col = y + 1
            return nextHoppingPointer
        }
        if (x < num_row - 1 && !visitedTiles[x + 1][y] && tiles[x + 1][y].type === TileType.LAND) {
            visitedTiles[x + 1][y] = visitedTiles[x][y]
            nextHoppingPointer.row = x + 1
            nextHoppingPointer.col = y
            return nextHoppingPointer
        }
        if (y > 0 && !visitedTiles[x][y - 1] && tiles[x][y - 1].type === TileType.LAND) {
            visitedTiles[x][y - 1] = visitedTiles[x][y]
            nextHoppingPointer.row = x
            nextHoppingPointer.col = y - 1
            return nextHoppingPointer
        }
        if (x > 0 && !visitedTiles[x - 1][y] && tiles[x - 1][y].type === TileType.LAND) {
            visitedTiles[x - 1][y] = visitedTiles[x][y]
            nextHoppingPointer.row = x - 1
            nextHoppingPointer.col = y
            return nextHoppingPointer
        }

        return nextHoppingPointer
    }


    private fun hasNextTile(tiles: Array<Array<Tile>>, visitedTiles: Array<Array<Boolean>>, currentHoppingPointer: HoppingPointer): Boolean {
        val rowLen = visitedTiles.size
        val colLen = visitedTiles[0].size
        val x = currentHoppingPointer.row
        val y = currentHoppingPointer.col

        if (y < colLen - 1 && !visitedTiles[x][y + 1] && tiles[x][y + 1].type === TileType.LAND) {
            return true
        }
        if (x < rowLen - 1 && !visitedTiles[x + 1][y] && tiles[x + 1][y].type === TileType.LAND) {
            return true
        }
        return if (y > 0 && !visitedTiles[x][y - 1] && tiles[x][y - 1].type === TileType.LAND) {
            true
        } else x > 0 && !visitedTiles[x - 1][y] && tiles[x - 1][y].type === TileType.LAND
    }
}


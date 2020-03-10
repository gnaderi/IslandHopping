package com.gnaderi.islandhopping.service

import com.gnaderi.islandhopping.dto.Island
import com.gnaderi.islandhopping.entity.IslandAreaEntity
import com.gnaderi.islandhopping.entity.IslandEntity
import com.gnaderi.islandhopping.entity.MapEntity
import com.gnaderi.islandhopping.entity.TileEntity
import com.gnaderi.islandhopping.repository.*
import com.gnaderi.islandhopping.common.ApiaryMap
import com.gnaderi.islandhopping.common.EntityToDtoConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
@Transactional
class IslandHoppingServiceImpl : IslandDiscoveryService {
    @Autowired
    private lateinit var mapCrudRepository: MapCrudRepository
    @Autowired
    private lateinit var islandCrudRepository: IslandCrudRepository
    @Autowired
    private lateinit var tileCrudRepository: TileCrudRepository
    @Autowired
    private lateinit var tileTypeCrudRepository: TileTypeCrudRepository
    @Autowired
    private lateinit var areaCrudRepository: IslandAreaCrudRepository
    @Autowired
    private lateinit var islandHoping: IslandHoping
    @Autowired
    private lateinit var e2dConverter: EntityToDtoConverter
    @Autowired
    private lateinit var restTemplate: RestTemplate

    override fun getMapFromAdvapi(): ApiaryMap? {
        return restTemplate.getForObject("/map", ApiaryMap::class.java)
    }


    /**
     * Creates a new map using [getMapFromAdvapi] call as Apiary service map
     * source and saves it database.
     */
    override fun createMap(): MapEntity {
        val apiaryMap = getMapFromAdvapi()
        val savedMap = mapCrudRepository.save(MapEntity(apiaryMap!!.data.type))
        val tiles = apiaryMap.attributes.tiles

        tiles!!.forEach { tile ->
            val one = tileTypeCrudRepository.getOne(tile.type.id)
            tileCrudRepository.save(TileEntity(tile.x, tile.y, one, savedMap))
        }
        val tileEntitiesByMapByMapId = tileCrudRepository.findTileEntitiesByMapId(savedMap.id) as MutableList
        val islands = islandHoping.discoverIslands(tileEntitiesByMapByMapId, savedMap.id!!)
        islands.forEach { isl ->
            val islandEntity = islandCrudRepository.save(IslandEntity(savedMap))
            isl.tiles!!.forEach { tile -> areaCrudRepository.save(IslandAreaEntity(islandEntity, tileCrudRepository.findById(tile.id!!).get())) }
        }
        return mapCrudRepository.findById(savedMap.id!!).get()
    }

    override fun findIslands(): List<Island> {
        return e2dConverter.convertIslandEntities(islandCrudRepository.findAll())
    }

    override fun findIslandById(id: Int?): Island {
        return e2dConverter.convertIslandEntityToIsland(islandCrudRepository.findById(id!!).get())
    }

    override fun mapsAsAsciiArt(): String {
        val maps = mapCrudRepository.findAll()

        val stringBuilder = StringBuilder()
        maps.forEach { it -> stringBuilder.append(mapAsAsciiArt(it.id!!)) }
        return stringBuilder.toString()
    }

    override fun mapAsAsciiArt(id: Int): String {
        val tiles = tileCrudRepository.findTileEntitiesByMapId(id)
        if (tiles.isEmpty())
            return ""
        return e2dConverter.convertMapTilesToAsciiArt(id,tiles)
    }
}

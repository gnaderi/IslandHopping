package com.gnaderi.islandhopping.controller

import com.gnaderi.islandhopping.dto.Island
import com.gnaderi.islandhopping.dto.Islands
import com.gnaderi.islandhopping.service.IslandDiscoveryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api"])
class IslandHoppingController(@Autowired var appService: IslandDiscoveryService) {
    private val LOGGER = LoggerFactory.getLogger(IslandHoppingController::class.java)


    @RequestMapping("/islands", method = [RequestMethod.GET])
    fun getAllIslands(): ResponseEntity<Islands> {
        LOGGER.info("Request for get all islands in repository:")
        val islandLst = appService.findIslands()
        return ResponseEntity(Islands(islandLst), HttpStatus.OK)
    }

    @RequestMapping("/islands/{id}", method = [RequestMethod.GET])
    fun getIslandById(@PathVariable(name = "id", required = true) id: Int): ResponseEntity<Island> {
        LOGGER.info("Request for a IslandEntity tiles. Id#{}", id)
        val islandById = appService.findIslandById(id)
        return ResponseEntity(islandById, HttpStatus.OK)

    }

    @PostMapping("/maps")
    fun createMap(): ResponseEntity<String> {
        val createMap = appService.createMap()
        LOGGER.info("Map created!")
        return ResponseEntity("Map[#${createMap.id}] created!", HttpStatus.CREATED)
    }

    /***
     * Returns ascii-art representation of map using [appService].
     */
    @GetMapping("/maps", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun getMapsAsAsciiArt() = appService.mapsAsAsciiArt()

    @GetMapping("/maps/{id}", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun getMapByIdAsAsciiArt(@PathVariable(name = "id", required = true) id: Int) = appService.mapAsAsciiArt(id)


}
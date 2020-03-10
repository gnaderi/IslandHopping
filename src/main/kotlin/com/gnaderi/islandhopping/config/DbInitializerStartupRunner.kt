package com.gnaderi.islandhopping.config

import com.gnaderi.islandhopping.entity.TileTypeEntity
import com.gnaderi.islandhopping.repository.TileTypeCrudRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DbInitializerStartupRunner : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(DbInitializerStartupRunner::class.java)
    @Autowired
    private val typeCrudRepository: TileTypeCrudRepository? = null

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        typeCrudRepository!!.save(TileTypeEntity(1, "LAND"))
        logger.info("Tile Types LAND crated!")
        typeCrudRepository.save(TileTypeEntity(2, "WATER"))
        logger.info("Tile Types WATER crated!")
    }
}

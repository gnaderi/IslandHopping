package com.gnaderi.islandhopping.repository

import com.gnaderi.islandhopping.entity.TileTypeEntity
import org.springframework.data.jpa.repository.JpaRepository


/**
 * [Map] repository.
 */
internal interface TileTypeCrudRepository : JpaRepository<TileTypeEntity, Int>
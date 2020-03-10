package com.gnaderi.islandhopping.repository

import com.gnaderi.islandhopping.entity.IslandEntity
import org.springframework.data.jpa.repository.JpaRepository


/**
 * [Map] repository.
 */
internal interface IslandCrudRepository : JpaRepository<IslandEntity, Int>
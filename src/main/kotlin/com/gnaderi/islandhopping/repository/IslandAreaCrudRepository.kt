package com.gnaderi.islandhopping.repository

import com.gnaderi.islandhopping.entity.IslandAreaEntity
import org.springframework.data.jpa.repository.JpaRepository


/**
 * [Map] repository.
 */
internal interface IslandAreaCrudRepository : JpaRepository<IslandAreaEntity, Int>
package com.gnaderi.islandhopping.repository

import com.gnaderi.islandhopping.entity.MapEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface MapCrudRepository : JpaRepository<MapEntity, Int>
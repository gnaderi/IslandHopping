package com.gnaderi.islandhopping.common

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.stereotype.Component

@Component
class TileTypeSerializer : StdSerializer<TileType>(TileType::class.java) {
    override fun serialize(value: TileType?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeString(value?.type!!.toUpperCase());
    }

}
package com.gnaderi.islandhopping.common

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.springframework.stereotype.Component

@Component
class TileTypeDeserializer : StdDeserializer<TileType>(TileType::class.java) {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): TileType {
        return TileType.valueOf(parser.readValueAs(String::class.java).toUpperCase())
    }

}
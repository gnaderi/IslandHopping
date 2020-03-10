package com.gnaderi.islandhopping.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "type", "links")
class Data(@JsonProperty("id") var id: String, @JsonProperty("type") var type: String) {

}

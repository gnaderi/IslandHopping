package com.gnaderi.islandhopping.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("data", "attributes")
class ApiaryMap(@JsonProperty("data") var data: Data, @JsonProperty("attributes") var attributes: Attributes)

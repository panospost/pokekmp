package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class DiamondPearl(
    val back_default: String,
//    val back_female: Any,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String,
)
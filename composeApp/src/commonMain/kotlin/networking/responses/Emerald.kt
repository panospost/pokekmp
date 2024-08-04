package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class Emerald(
    val front_default: String,
    val front_shiny: String
)
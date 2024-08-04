package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class Home(
    val front_default: String,

    val front_shiny: String,
)
package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GenerationV(
    val black_white: BlackWhite
)
package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GenerationI(
    val red_blue: RedBlue,
    val yellow: Yellow
)
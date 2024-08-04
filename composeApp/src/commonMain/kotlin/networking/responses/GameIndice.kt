package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GameIndice(
    val game_index: Int,
    val version: Version
)
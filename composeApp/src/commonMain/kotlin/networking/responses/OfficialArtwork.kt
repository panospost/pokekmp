package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class OfficialArtwork(
    val front_default: String
)
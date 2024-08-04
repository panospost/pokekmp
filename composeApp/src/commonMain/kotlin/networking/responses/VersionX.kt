package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class VersionX(
    val name: String,
    val url: String
)
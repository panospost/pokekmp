package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class VersionDetail(
    val rarity: Int,
    val version: VersionX
)
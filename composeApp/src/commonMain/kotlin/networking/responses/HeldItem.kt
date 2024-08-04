package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)
package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class StatX(
    val name: String,
    val url: String
)
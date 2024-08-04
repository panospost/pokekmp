package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class Yellow(
    val back_default: String,
    val back_gray: String,
    val front_default: String,
    val front_gray: String
)
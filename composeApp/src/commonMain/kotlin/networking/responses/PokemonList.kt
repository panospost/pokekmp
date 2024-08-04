package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result?>
)
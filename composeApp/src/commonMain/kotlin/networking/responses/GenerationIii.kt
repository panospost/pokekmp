package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GenerationIii(
    val emerald: Emerald,
    val firered_leafgreen: FireredLeafgreen,
    val ruby_sapphire: RubySapphire
)
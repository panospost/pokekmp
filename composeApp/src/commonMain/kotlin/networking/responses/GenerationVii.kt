package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GenerationVii(
    val icons: Icons,
    val ultra_sun_ultra_moon: UltraSunUltraMoon
)
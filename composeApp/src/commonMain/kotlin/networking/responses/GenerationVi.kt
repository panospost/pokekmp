package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GenerationVi(
    val omegaruby_alphasapphire: OmegarubyAlphasapphire,
    val x_y: XY
)
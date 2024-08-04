package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GenerationIv(
    val diamond_pearl: DiamondPearl,
    val heartgold_soulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
)
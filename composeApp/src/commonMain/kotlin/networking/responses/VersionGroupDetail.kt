package com.example.pokedex.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
)
package com.app.mypokedex.ui.screens.PokemonDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pokepost.composeapp.generated.resources.Res
import pokepost.composeapp.generated.resources.compose_multiplatform
import kotlin.math.round

@Composable
fun PokemonDetailDataSection(
    pokemonWeight: Int,
    pokemonHeight: Int,
    sectionHeight: Dp = 80.dp
) {
    val pokemonWeightKgk = remember {
        round(pokemonWeight * 100f) / 1000f
    }

    val pokemonHeightKgk = remember {
        round(pokemonHeight * 100f) / 1000f
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        PokemonDetailDataItem(
            dataValue = pokemonWeightKgk, dataUnit = "lg", dataIcon = painterResource(
                resource = Res.drawable.compose_multiplatform
            ), modifier = Modifier.weight(1f)
        )
        Spacer(
            modifier = Modifier
                .size(1.dp, sectionHeight)
                .background(Color.LightGray)
        )

        PokemonDetailDataItem(
            dataValue = pokemonHeightKgk, dataUnit = "m", dataIcon = painterResource(
                resource = Res.drawable.compose_multiplatform
            ), modifier = Modifier.weight(1f)
        )
    }


}
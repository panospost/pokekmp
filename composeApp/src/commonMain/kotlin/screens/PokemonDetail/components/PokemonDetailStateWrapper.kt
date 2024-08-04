package com.app.mypokedex.ui.screens.PokemonDetail.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.Type
import util.Result
import util.NetworkError

@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo: Result<Pokemon,NetworkError>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    setColor: (List<Type>) -> Unit
) {

    when (pokemonInfo) {
        is Result.Success -> {
            LaunchedEffect(true) {
                setColor(pokemonInfo.data.types)
            }
            PokemonDetailSection(
                pokemonInfo = pokemonInfo.data,
                modifier = modifier.offset(y = (-20).dp)
            )
        }
        is Result.Error -> {
            Text(pokemonInfo.error.name, color = Color.Red, modifier = modifier)
        }
        is Result.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )

        }
    }

}
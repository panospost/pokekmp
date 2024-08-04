package com.app.mypokedex.ui.screens.pokemonlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import domain.models.PokedexListEntry
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import screens.pokemonList.PokemonListViewModel

@OptIn(KoinExperimentalAPI::class)
@ExperimentalCoilApi
@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val defaultDominantCOlor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantCOlor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape((10.dp)))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantCOlor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
                )
            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier = modifier,
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(
                        entry.imageUrl
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                onSuccess = {
//                    viewModel.calcDominantColor(it.result.drawable) { color ->
//                        dominantColor = color
//                    }
                }
            )
            Text(
                text = entry.pokemonName,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}
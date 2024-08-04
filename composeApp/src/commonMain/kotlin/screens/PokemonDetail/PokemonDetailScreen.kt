package com.example.pokedex.PokemonDetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.app.mypokedex.ui.screens.PokemonDetail.components.PokemonDetailStateWrapper
import com.app.mypokedex.ui.screens.PokemonDetail.components.PokemonDetailTopSection
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.util.parseTypeToColor
import org.koin.compose.viewmodel.koinViewModel
import util.NetworkError
import util.Result

@Composable
fun PokemonDetailScreen(
    dominantColor: Color,
    pokemonName: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailViewModel = koinViewModel()
) {
    val pokemonInfo = produceState<Result<Pokemon,NetworkError>>(initialValue = Result.Loading) {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        val color by remember {
            viewModel.dominantColor
        }
        PokemonDetailTopSection(
            color,
            navController = navController, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(
                    Alignment.TopCenter
                )
        )
        PokemonDetailStateWrapper(
            pokemonInfo = pokemonInfo,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.surface)
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )

        ) { types ->
            viewModel.setDominantColor(parseTypeToColor(types[0]))
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            if (pokemonInfo is Result.Success) {
                pokemonInfo.data?.sprites.let {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalPlatformContext.current)
                                .data(data = it?.front_default).apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                }).build()
                        ),
                        contentDescription = pokemonInfo.data?.name,
                        modifier = Modifier
                            .size(pokemonImageSize)
                            .offset(y = topPadding)
                    )
                }
            }
        }

    }
}






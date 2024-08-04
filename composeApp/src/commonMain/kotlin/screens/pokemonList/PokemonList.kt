package screens.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.mypokedex.ui.screens.pokemonlist.components.PokemonList
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import pokepost.composeapp.generated.resources.Res
import pokepost.composeapp.generated.resources.compose_multiplatform
import pokepost.composeapp.generated.resources.pokemon_23
import screens.pokemonList.components.SearchBar
import theme.backgroundRed

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = koinViewModel()
) {
    Surface(
        color = backgroundRed,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(

        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(resource = Res.drawable.pokemon_23),
                contentDescription = "pokemon logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                viewModel.searchPokemonList(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            PokemonList(navController = navController, viewModel =viewModel)
        }
    }
}
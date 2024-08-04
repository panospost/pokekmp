import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.example.pokedex.PokemonDetail.PokemonDetailScreen
import okio.FileSystem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.annotation.KoinExperimentalAPI
import screens.pokemonList.PokemonListScreen

@OptIn(KoinExperimentalAPI::class, ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {

    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            getAsyncImageLoader(context)
        }
        KoinContext{
            val navController = rememberNavController()

            Scaffold() { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    composable("pokemon_list_screen") {
                      PokemonListScreen(navController)
                    }
                    composable("pokemon_detail_screen/{dominantColor}/{pokemonName}",
                            arguments = listOf(
                                navArgument("dominantColor") {
                                    type = NavType.IntType
                                },
                                navArgument("pokemonName") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val dominantColor = remember {
                                val color = it.arguments?.getInt("dominantColor")
                                color?.let { Color(it) } ?: Color.White
                            }
                            val pokemonName = remember {
                                it.arguments?.getString("pokemonName")
                            }
                            PokemonDetailScreen(
                                dominantColor = dominantColor,
                                pokemonName = pokemonName?: "",
                                navController = navController,
                            )
                        }
                }
            }
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024) // 512MB
        .build()
}
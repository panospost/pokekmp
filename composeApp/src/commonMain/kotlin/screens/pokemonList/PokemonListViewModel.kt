package screens.pokemonList

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.PokedexListEntry
import domain.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import util.Result

class PokemonListViewModel(
    private val repository: PokemonRepository
): ViewModel() {
    private var curPage = 0
    val PAGE_SIZE = 20

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if (isSearchStarting) {
            pokemonList.value
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }

            val result = listToSearch.filter {
                it.pokemonName.contains(
                    query.trim(),
                    ignoreCase = true
                ) || it.number.toString() == query.trim()
            }

            if(isSearchStarting) {
                cachedPokemonList = pokemonList.value
                isSearchStarting = false
            }
            pokemonList.value = result
            isSearching.value = true

        }
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when (result) {
                is Result.Success-> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed { index, outentry ->
                        outentry?.let { entry ->
                            val number = if (entry.url?.endsWith("/") == true) {
                                entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                            } else {
                                entry.url.takeLastWhile { it.isDigit() }
                            }
                            val url =
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                            PokedexListEntry(entry.name?:"", url, number.toInt())
                        }

                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries.filterNotNull()
                }
                is Result.Error -> {
                    loadError.value = result.error.name
                    isLoading.value = false
                }

                else -> {}
            }
        }
    }

//    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
//        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
////        Palette.from(bmp).generate { palette ->
////            palette?.dominantSwatch?.rgb?.let { color ->
////                onFinish(Color(color))
////            }
////        }
//    }
}
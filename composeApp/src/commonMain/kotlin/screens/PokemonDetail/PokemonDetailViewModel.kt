package com.example.pokedex.PokemonDetail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.pokedex.data.remote.responses.Pokemon
import domain.repository.PokemonRepository
import util.NetworkError
import util.Result

class PokemonDetailViewModel(
    private val repository: PokemonRepository
): ViewModel() {

    var dominantColor = mutableStateOf(Color.Black)
    suspend fun getPokemonInfo(pokemonName: String): Result<Pokemon, NetworkError> {
        return repository.getPokemonInfo(pokemonName)
    }

    fun setDominantColor(color: Color){
        dominantColor.value = color
    }

}
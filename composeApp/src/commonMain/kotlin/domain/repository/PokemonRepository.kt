package domain.repository

import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.PokemonList
import networking.PokemonHttpClient
import util.NetworkError
import util.Result

class PokemonRepository(
    private val api: PokemonHttpClient
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonList, NetworkError>{
        return api.getPokemonlist(limit,offset)
    }

    suspend fun getPokemonInfo(pokemonName: String): Result<Pokemon, NetworkError>{
        return api.getPokemonInfo(pokemonName)
    }
}
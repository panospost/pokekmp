package di

import com.example.pokedex.PokemonDetail.PokemonDetailViewModel
import domain.repository.PokemonRepository
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import screens.pokemonList.PokemonListViewModel

expect val platformModule: Module

expect val networkClient: Module

expect val preferencesModule: Module

val sharedModule = module {
    singleOf(::PokemonRepository).bind<PokemonRepository>()
    viewModelOf(::PokemonListViewModel)
    viewModelOf(::PokemonDetailViewModel)
}
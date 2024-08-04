package di

import dependencies.MyRepository
import dependencies.MyRepositoryImpl
import dependencies.MyViewModel
import domain.PokemonRepository
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
    singleOf(::MyRepositoryImpl).bind<MyRepository>()
    singleOf(::PokemonRepository).bind<PokemonRepository>()

    viewModelOf(::MyViewModel)
    viewModelOf(::PokemonListViewModel)
}
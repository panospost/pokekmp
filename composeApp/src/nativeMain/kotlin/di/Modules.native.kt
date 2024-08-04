package di

import io.ktor.client.engine.darwin.Darwin
import networking.PokemonHttpClient
import networking.createHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
}

actual val networkClient = module {
    single {
        PokemonHttpClient(createHttpClient(Darwin.create()))
    }.bind<PokemonHttpClient>()
}

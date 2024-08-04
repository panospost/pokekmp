package di

import DATA_STORE_FILE_NAME
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import createDataStore
import io.ktor.client.engine.okhttp.OkHttp
import networking.PokemonHttpClient
import networking.createHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
}
actual val networkClient = module {
    single {
        PokemonHttpClient(createHttpClient(OkHttp.create()))
    }.bind<PokemonHttpClient>()
}
actual val preferencesModule = module {
    single {
        createDataStore{DATA_STORE_FILE_NAME}
    }.bind<DataStore<Preferences>>()
}
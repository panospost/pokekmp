package di

import DATA_STORE_FILE_NAME
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import createDataStore
import dependencies.DbClient
import io.ktor.client.engine.okhttp.OkHttp
import networking.InsultCensorClient
import networking.createHttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::DbClient)
}
actual val networkClient = module {
    single {
        InsultCensorClient(createHttpClient(OkHttp.create()))
    }.bind<InsultCensorClient>()
}
actual val preferencesModule = module {
    single {
        createDataStore{DATA_STORE_FILE_NAME}
    }.bind<DataStore<Preferences>>()
}
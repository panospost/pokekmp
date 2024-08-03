package di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dependencies.DbClient
import io.ktor.client.engine.darwin.Darwin
import networking.InsultCensorClient
import networking.createHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::DbClient)
}

actual val networkClient = module {
    single {
        InsultCensorClient(createHttpClient(Darwin.create()))
    }.bind<InsultCensorClient>()
}

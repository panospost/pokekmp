package di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import createDataStore
import dependencies.DbClient
import dependencies.MyViewModel
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpEngine
import networking.InsultCensorClient
import networking.createHttpClient
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::DbClient)

    viewModelOf(::MyViewModel)
}
actual val networkClient = module {
    single {
        InsultCensorClient(createHttpClient(OkHttp.create()))
    }.bind<InsultCensorClient>()
}
actual val preferencesModule = module {
    single {
        createDataStore(androidApplication())
    }.bind<DataStore<Preferences>>()
}
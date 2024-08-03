package di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import createDataStore
import org.koin.dsl.bind
import org.koin.dsl.module

actual val preferencesModule = module {
    single {
        createDataStore()
    }.bind<DataStore<Preferences>>()
}
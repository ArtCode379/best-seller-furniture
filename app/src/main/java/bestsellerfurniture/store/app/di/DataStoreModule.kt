package bestsellerfurniture.store.app.di

import bestsellerfurniture.store.app.data.datastore.BSSFTOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { BSSFTOnboardingPrefs(androidContext()) }
}
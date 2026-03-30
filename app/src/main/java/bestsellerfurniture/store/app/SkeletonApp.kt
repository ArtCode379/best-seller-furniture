package bestsellerfurniture.store.app

import android.app.Application
import bestsellerfurniture.store.app.di.dataModule
import bestsellerfurniture.store.app.di.dispatcherModule
import bestsellerfurniture.store.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BSSFTApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@BSSFTApp)
            modules(appModules)
        }
    }
}
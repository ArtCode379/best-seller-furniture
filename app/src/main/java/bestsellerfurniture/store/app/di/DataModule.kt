package bestsellerfurniture.store.app.di

import bestsellerfurniture.store.app.data.repository.CartRepository
import bestsellerfurniture.store.app.data.repository.BSSFTOnboardingRepo
import bestsellerfurniture.store.app.data.repository.OrderRepository
import bestsellerfurniture.store.app.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        BSSFTOnboardingRepo(
            bssftOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}
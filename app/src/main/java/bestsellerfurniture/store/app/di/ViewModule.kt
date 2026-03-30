package bestsellerfurniture.store.app.di

import bestsellerfurniture.store.app.ui.viewmodel.AppViewModel
import bestsellerfurniture.store.app.ui.viewmodel.CartViewModel
import bestsellerfurniture.store.app.ui.viewmodel.CheckoutViewModel
import bestsellerfurniture.store.app.ui.viewmodel.BSSFTOnboardingVM
import bestsellerfurniture.store.app.ui.viewmodel.OrderViewModel
import bestsellerfurniture.store.app.ui.viewmodel.ProductDetailsViewModel
import bestsellerfurniture.store.app.ui.viewmodel.ProductViewModel
import bestsellerfurniture.store.app.ui.viewmodel.BSSFTSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        BSSFTSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        BSSFTOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}
package bestsellerfurniture.store.app.ui.composable.screen.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import bestsellerfurniture.store.app.R
import bestsellerfurniture.store.app.data.model.Product
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTContentWrapper
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTEmptyView
import bestsellerfurniture.store.app.ui.state.DataUiState
import bestsellerfurniture.store.app.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier) {

        BSSFTContentWrapper(
            dataState = productState,

            dataPopulated = {

            },

            dataEmpty = {
                BSSFTEmptyView(
                    primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}
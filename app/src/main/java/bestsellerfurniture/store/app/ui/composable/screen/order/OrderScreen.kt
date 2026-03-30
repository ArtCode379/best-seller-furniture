package bestsellerfurniture.store.app.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import bestsellerfurniture.store.app.R
import bestsellerfurniture.store.app.data.entity.OrderEntity
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTContentWrapper
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTEmptyView
import bestsellerfurniture.store.app.ui.state.DataUiState
import bestsellerfurniture.store.app.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        BSSFTContentWrapper(
            dataState = ordersState,

            dataPopulated = {

            },

            dataEmpty = {
                BSSFTEmptyView(
                    primaryText = stringResource(R.string.orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}
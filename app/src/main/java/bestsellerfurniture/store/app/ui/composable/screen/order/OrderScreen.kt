package bestsellerfurniture.store.app.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bestsellerfurniture.store.app.R
import bestsellerfurniture.store.app.data.entity.OrderEntity
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTContentWrapper
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTEmptyView
import bestsellerfurniture.store.app.ui.state.DataUiState
import bestsellerfurniture.store.app.ui.theme.Gold
import bestsellerfurniture.store.app.ui.theme.MutedText
import bestsellerfurniture.store.app.ui.theme.OnSurface
import bestsellerfurniture.store.app.ui.theme.WarmBrown
import bestsellerfurniture.store.app.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

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
    BSSFTContentWrapper(
        dataState = ordersState,
        modifier = modifier,

        dataPopulated = {
            val orders = (ordersState as DataUiState.Populated<List<OrderEntity>>).data
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(orders, key = { it.orderNumber }) { order ->
                    OrderCard(order = order)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },

        dataEmpty = {
            BSSFTEmptyView(
                primaryText = stringResource(R.string.orders_state_empty_primary_text),
                secondaryText = "Your completed orders will appear here",
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.order_number, order.orderNumber),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = OnSurface,
                )

                // Status chip
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Gold.copy(alpha = 0.15f),
                    ),
                ) {
                    Text(
                        text = "CONFIRMED",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        color = Gold,
                        letterSpacing = 1.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.timestamp.format(dateFormatter),
                fontSize = 13.sp,
                color = MutedText,
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = Color(0xFFE0E0E0),
            )

            Text(
                text = order.description,
                fontSize = 13.sp,
                color = MutedText,
                lineHeight = 18.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.order_customer, order.customerFirstName, order.customerLastName),
                    fontSize = 13.sp,
                    color = WarmBrown,
                )

                Text(
                    text = "£%.2f".format(order.price),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Gold,
                )
            }
        }
    }
}

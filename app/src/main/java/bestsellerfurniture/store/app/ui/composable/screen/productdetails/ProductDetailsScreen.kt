package bestsellerfurniture.store.app.ui.composable.screen.productdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bestsellerfurniture.store.app.R
import bestsellerfurniture.store.app.data.model.Product
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTContentWrapper
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTEmptyView
import bestsellerfurniture.store.app.ui.state.DataUiState
import bestsellerfurniture.store.app.ui.theme.Gold
import bestsellerfurniture.store.app.ui.theme.MutedText
import bestsellerfurniture.store.app.ui.theme.OnSurface
import bestsellerfurniture.store.app.ui.theme.Primary
import bestsellerfurniture.store.app.ui.theme.WarmBrown
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
    BSSFTContentWrapper(
        dataState = productState,
        modifier = modifier,

        dataPopulated = {
            val product = (productState as DataUiState.Populated<Product>).data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Image(
                    painter = painterResource(product.imageRes),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = product.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Light,
                        color = OnSurface,
                        letterSpacing = (-0.5).sp,
                    )

                    Text(
                        text = "£%.2f".format(product.price),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Gold,
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                color = WarmBrown.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(8.dp),
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                    ) {
                        Text(
                            text = stringResource(product.category.titleRes).uppercase(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = WarmBrown,
                            letterSpacing = 1.5.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = product.description,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = MutedText,
                        lineHeight = 24.sp,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onAddToCart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(
                            text = stringResource(R.string.button_add_to_cart_label),
                            fontSize = 15.sp,
                            letterSpacing = 0.5.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },

        dataEmpty = {
            BSSFTEmptyView(
                primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

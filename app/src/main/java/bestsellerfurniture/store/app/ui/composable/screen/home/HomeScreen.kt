package bestsellerfurniture.store.app.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bestsellerfurniture.store.app.R
import bestsellerfurniture.store.app.data.model.Product
import bestsellerfurniture.store.app.data.model.ProductCategory
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTContentWrapper
import bestsellerfurniture.store.app.ui.composable.shared.BSSFTEmptyView
import bestsellerfurniture.store.app.ui.state.DataUiState
import bestsellerfurniture.store.app.ui.theme.Gold
import bestsellerfurniture.store.app.ui.theme.MutedText
import bestsellerfurniture.store.app.ui.theme.OnSurface
import bestsellerfurniture.store.app.ui.theme.WarmBrown
import bestsellerfurniture.store.app.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

private data class HeroSlide(
    val imageRes: Int,
    val title: String,
    val subtitle: String,
)

private val heroSlides = listOf(
    HeroSlide(R.drawable.hero1, "Modern Living", "Curated comfort for your home"),
    HeroSlide(R.drawable.hero2, "Elegant Interiors", "Timeless design, exceptional craft"),
    HeroSlide(R.drawable.hero3, "New Arrivals", "Discover the latest collection"),
)

private data class CategoryItem(
    val category: ProductCategory?,
    val label: String,
    val iconRes: Int,
)

private val categoryItems = listOf(
    CategoryItem(null, "All", R.drawable.star_svgrepo_com),
    CategoryItem(ProductCategory.LIVING_ROOM, "Living Room", R.drawable.home_svgrepo_com),
    CategoryItem(ProductCategory.DINING, "Dining", R.drawable.cart_svgrepo_com),
    CategoryItem(ProductCategory.BEDROOM, "Bedroom", R.drawable.star_svgrepo_com),
    CategoryItem(ProductCategory.OFFICE, "Office", R.drawable.calendar_svgrepo_com),
    CategoryItem(ProductCategory.OUTDOOR, "Outdoor", R.drawable.link_svgrepo_com),
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    BSSFTContentWrapper(
        dataState = productsState,
        modifier = modifier,

        dataPopulated = {
            val products = (productsState as DataUiState.Populated<List<Product>>).data
            var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
            val filteredProducts = if (selectedCategory == null) products
                else products.filter { it.category == selectedCategory }

            val pagerState = rememberPagerState(pageCount = { heroSlides.size })

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // Hero carousel
                item(span = { GridItemSpan(2) }) {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(16.dp)),
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier.fillMaxSize(),
                            ) { page ->
                                val slide = heroSlides[page]
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Image(
                                        painter = painterResource(slide.imageRes),
                                        contentDescription = slide.title,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(
                                                    colors = listOf(
                                                        Color.Transparent,
                                                        Color(0xCC1A1A1A),
                                                    )
                                                )
                                            ),
                                    )
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(20.dp),
                                    ) {
                                        Text(
                                            text = slide.title,
                                            color = Color.White,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Light,
                                            letterSpacing = (-0.5).sp,
                                        )
                                        Text(
                                            text = slide.subtitle,
                                            color = Gold,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Normal,
                                        )
                                    }
                                }
                            }
                        }

                        // Pager dots
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            repeat(heroSlides.size) { i ->
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                        .size(if (pagerState.currentPage == i) 8.dp else 6.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (pagerState.currentPage == i) Gold
                                            else Color(0xFFE0E0E0)
                                        )
                                )
                            }
                        }
                    }
                }

                // Category chips
                item(span = { GridItemSpan(2) }) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 8.dp),
                    ) {
                        items(categoryItems) { item ->
                            FilterChip(
                                selected = selectedCategory == item.category,
                                onClick = { selectedCategory = item.category },
                                label = {
                                    Text(
                                        text = item.label,
                                        fontSize = 13.sp,
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(item.iconRes),
                                        contentDescription = item.label,
                                        modifier = Modifier.size(16.dp),
                                    )
                                },
                                shape = RoundedCornerShape(20.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFF1A1A1A),
                                    selectedLabelColor = Color.White,
                                    selectedLeadingIconColor = Gold,
                                    containerColor = Color.White,
                                    labelColor = OnSurface,
                                    iconColor = MutedText,
                                ),
                            )
                        }
                    }
                }

                // Section header
                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "FEATURED COLLECTION",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = WarmBrown,
                        letterSpacing = 1.5.sp,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    )
                }

                // Product grid
                items(filteredProducts, key = { it.id }) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onNavigateToProductDetails(product.id) },
                    )
                }

                // Bottom spacer
                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },

        dataEmpty = {
            BSSFTEmptyView(
                primaryText = stringResource(R.string.products_state_empty_primary_text),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun ProductCard(
    product: Product,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop,
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = OnSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "£%.2f".format(product.price),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Gold,
                    )

                    Text(
                        text = stringResource(product.category.titleRes).uppercase(),
                        fontSize = 9.sp,
                        color = WarmBrown,
                        letterSpacing = 1.sp,
                    )
                }
            }
        }
    }
}

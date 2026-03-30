package bestsellerfurniture.store.app.data.repository

import bestsellerfurniture.store.app.R
import bestsellerfurniture.store.app.data.model.Product
import bestsellerfurniture.store.app.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Velvet Sofa Milano",
            description = "Luxurious 3-seater velvet sofa in emerald green with brass legs. Perfect centerpiece for any living room.",
            category = ProductCategory.LIVING_ROOM,
            price = 1299.99,
            imageRes = R.drawable.sofa_milano,
        ),
        Product(
            id = 2,
            title = "Oak Dining Table Sienna",
            description = "Solid oak dining table seating 6-8 people. Natural grain finish with tapered legs.",
            category = ProductCategory.DINING,
            price = 899.99,
            imageRes = R.drawable.dining_table,
        ),
        Product(
            id = 3,
            title = "Leather Armchair Baron",
            description = "Full-grain leather armchair with deep cushioning and walnut wood armrests.",
            category = ProductCategory.LIVING_ROOM,
            price = 749.99,
            imageRes = R.drawable.armchair,
        ),
        Product(
            id = 4,
            title = "King Bed Frame Aurora",
            description = "Upholstered king-size bed frame in soft grey fabric with integrated headboard.",
            category = ProductCategory.BEDROOM,
            price = 1599.99,
            imageRes = R.drawable.bed_frame,
        ),
        Product(
            id = 5,
            title = "Marble Coffee Table Luxe",
            description = "Italian marble top coffee table with brushed gold steel base. Diameter 90cm.",
            category = ProductCategory.LIVING_ROOM,
            price = 599.99,
            imageRes = R.drawable.coffee_table,
        ),
        Product(
            id = 6,
            title = "Walnut Bookshelf Tower",
            description = "5-tier solid walnut bookshelf with adjustable shelves. Height 180cm, width 80cm.",
            category = ProductCategory.OFFICE,
            price = 449.99,
            imageRes = R.drawable.bookshelf,
        ),
        Product(
            id = 7,
            title = "Dining Chair Set Nordic",
            description = "Set of 4 Scandinavian-inspired dining chairs in natural beech with linen seats.",
            category = ProductCategory.DINING,
            price = 399.99,
            imageRes = R.drawable.dining_chairs,
        ),
        Product(
            id = 8,
            title = "Linen Bedside Table Duo",
            description = "Pair of minimalist bedside tables with single drawer and open shelf. White oak finish.",
            category = ProductCategory.BEDROOM,
            price = 349.99,
            imageRes = R.drawable.bedside_table,
        ),
        Product(
            id = 9,
            title = "Executive Desk Prestige",
            description = "Large executive desk in dark walnut with cable management and 3 drawers.",
            category = ProductCategory.OFFICE,
            price = 1099.99,
            imageRes = R.drawable.exec_desk,
        ),
        Product(
            id = 10,
            title = "Outdoor Lounge Set Riviera",
            description = "4-piece rattan lounge set with weather-resistant cushions. Includes sofa, 2 chairs, and coffee table.",
            category = ProductCategory.OUTDOOR,
            price = 1899.99,
            imageRes = R.drawable.outdoor_set,
        ),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}

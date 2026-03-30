package bestsellerfurniture.store.app.data.model

import androidx.annotation.StringRes
import bestsellerfurniture.store.app.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    LIVING_ROOM(R.string.category_living_room),
    DINING(R.string.category_dining),
    BEDROOM(R.string.category_bedroom),
    OFFICE(R.string.category_office),
    OUTDOOR(R.string.category_outdoor),
}

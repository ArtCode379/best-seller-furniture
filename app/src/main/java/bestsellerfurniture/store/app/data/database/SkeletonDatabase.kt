package bestsellerfurniture.store.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bestsellerfurniture.store.app.data.dao.CartItemDao
import bestsellerfurniture.store.app.data.dao.OrderDao
import bestsellerfurniture.store.app.data.database.converter.Converters
import bestsellerfurniture.store.app.data.entity.CartItemEntity
import bestsellerfurniture.store.app.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BSSFTDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}
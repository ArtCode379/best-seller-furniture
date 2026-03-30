package bestsellerfurniture.store.app.di

import androidx.room.Room
import bestsellerfurniture.store.app.data.database.BSSFTDatabase
import org.koin.dsl.module

private const val DB_NAME = "bssft_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = BSSFTDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<BSSFTDatabase>().cartItemDao() }

    single { get<BSSFTDatabase>().orderDao() }
}
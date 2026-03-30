package bestsellerfurniture.store.app.data.repository

import bestsellerfurniture.store.app.data.datastore.BSSFTOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BSSFTOnboardingRepo(
    private val bssftOnboardingStoreManager: BSSFTOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return bssftOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            bssftOnboardingStoreManager.setOnboardedState(state)
        }
    }
}
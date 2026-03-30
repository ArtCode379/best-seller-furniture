package bestsellerfurniture.store.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import bestsellerfurniture.store.app.ui.composable.approot.AppRoot
import bestsellerfurniture.store.app.ui.theme.ProductAppBSSFTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppBSSFTTheme {
                AppRoot()
            }
        }
    }
}
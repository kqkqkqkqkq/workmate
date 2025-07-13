package k.workmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import k.navigation.WorkmateNavigation
import k.ui_kit.theme.WorkmateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            WorkmateTheme {
                WorkmateNavigation()
            }
        }
    }
}
package k.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import k.ui.DetailScreen
import k.ui.MainScreen

@Composable
fun WorkmateNavigation() {
    val backStack = rememberNavBackStack<Screen>(Screen.Main)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.Main> {
                MainScreen() {
                    backStack.add(Screen.Detail("123"))
                }
            }
            entry<Screen.Detail> { key ->
                DetailScreen(id = key.id) { backStack.removeLastOrNull() }

            }
        }
    )

}
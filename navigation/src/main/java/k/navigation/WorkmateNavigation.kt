package k.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import k.ui.DetailScreen
import k.ui.MainScreen
import k.ui_kit.theme.WorkmateTheme

@Composable
fun WorkmateNavigation() {
    WorkmateTheme {
        val backStack = rememberNavBackStack<Screen>(Screen.Main)

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
                rememberSceneSetupNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Screen.Main> {
                    MainScreen(
                        onItemClick = { id ->
                            backStack.add(Screen.Detail(id))
                        }
                    )
                }
                entry<Screen.Detail> { key ->
                    DetailScreen(id = key.id) { backStack.removeLastOrNull() }
                }
            }
        )
    }
}
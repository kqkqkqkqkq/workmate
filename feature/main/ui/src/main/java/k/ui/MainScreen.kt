package k.ui

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import k.ui.state.MainScreenContent
import k.ui.state.MainScreenFailure
import k.ui.state.MainScreenInitial
import k.ui.state.MainScreenLoading
import k.ui_logic.MainScreenState
import k.ui_logic.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onItemClick: (Int) -> Unit,
) {
    MainScreenUI(
        onItemClick = onItemClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenUI(
    viewModel: MainViewModel = koinViewModel(),
    onItemClick: (Int) -> Unit,
) {

    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val refreshState = rememberPullToRefreshState()
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                delay(3000)
                viewModel.loadCharacters()
                viewModel.getAllCharacters()
                isRefreshing = false
            }
        },
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background,
            )
    ) {
        when (val currentState = state) {
            is MainScreenState.Content -> MainScreenContent(
                characters = currentState.characters,
                onItemClick = onItemClick,
            )

            is MainScreenState.Failure -> MainScreenFailure(
                message = currentState.message ?: "Unknown Error"
            )

            is MainScreenState.Initial -> {
                MainScreenInitial()
                LaunchedEffect(Unit) {
                    viewModel.getAllCharacters()
                }
            }

            is MainScreenState.Loading -> MainScreenLoading()
        }
    }
}
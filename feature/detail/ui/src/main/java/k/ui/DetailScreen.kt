package k.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import k.ui.state.DetailScreenContent
import k.ui.state.DetailScreenFailure
import k.ui.state.DetailScreenInitial
import k.ui.state.DetailScreenLoading
import k.ui_logic.DetailScreenState
import k.ui_logic.DetailViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun DetailScreen(
    id: Int,
    onBackClick: () -> Unit,
) {
    DetailScreenUI(
        id = id,
        onBackClick = onBackClick,
    )
}

@Composable
fun DetailScreenUI(
    viewModel: DetailViewModel = koinViewModel(),
    id: Int,
    onBackClick: () -> Unit,
) {

    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is DetailScreenState.Content -> DetailScreenContent(
            character = currentState.state.character,
            onBackClick = onBackClick,
        )

        is DetailScreenState.Failure -> DetailScreenFailure(
            message = currentState.message ?: "Unknown Error",
        )

        is DetailScreenState.Initial -> {
            DetailScreenInitial()
            LaunchedEffect(Unit) {
                viewModel.getCharacter(id)
            }
        }

        is DetailScreenState.Loading -> DetailScreenLoading()
    }

}
package k.ui.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import k.ui.components.CharacterItem
import k.ui.components.CharacterPager
import k.ui.components.FilterBottomSheet
import k.ui.components.CharactersTopBar
import k.ui_logic.MainViewModel
import k.ui_logic.utils.SearchState
import k.ui_models.model.CharacterUIO
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    viewModel: MainViewModel = koinViewModel(),
    characters: List<CharacterUIO>,
    onItemClick: (Int) -> Unit,
) {
    val currentPage by viewModel.currentPage.collectAsState()

    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    val searchState by viewModel.searchState.collectAsState()
    val text by viewModel.searchText.collectAsState()
    val pagerSize by viewModel.pagerSize.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CharactersTopBar(
                searchState = searchState,
                text = text,
                onTextChange = {
                    viewModel.changeSearchText(it)
                },
                onCloseClicked = {
                    viewModel.changeSearchText("")
                    viewModel.changeSearchState(SearchState.CLOSED)
                    viewModel.getAllCharacters()
                },
                onSearchClicked = {
                    viewModel.search(it)
                },
                onSearchTriggered = {
                    viewModel.changeSearchState(SearchState.OPENED)
                },
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp),
                    contentPadding = PaddingValues(
                        horizontal = 2.dp,
                        vertical = 4.dp,
                    ),
                    verticalArrangement = Arrangement.Top,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (characters.isEmpty()) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "Couldn't find the characters",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    }
                    else {
                        items(characters) { character ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CharacterItem(
                                    character = character,
                                    onItemClick = onItemClick,
                                )
                            }
                        }

                        if (pagerSize > 1) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    CharacterPager(
                                        currentPage = currentPage,
                                        totalPages = pagerSize,
                                        onPageSelected = { viewModel.selectPage(it) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Button(
                onClick = {
                    scope.launch {
                        isBottomSheetVisible = true
                        sheetState.expand()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = "Filter",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.background,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }

    FilterBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismiss = {
            scope.launch { sheetState.hide() }
                .invokeOnCompletion { isBottomSheetVisible = false }
        },
    )
}

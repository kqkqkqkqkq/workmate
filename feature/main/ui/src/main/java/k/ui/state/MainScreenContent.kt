package k.ui.state

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import k.ui.components.CharacterItem
import k.ui_models.model.CharacterUIO

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenContent(
    characters: List<CharacterUIO>,
    onItemClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 4.dp),
            contentPadding = PaddingValues(
                horizontal = 2.dp,
                vertical = 4.dp,
            ),
            verticalArrangement = Arrangement.Top,
//            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            items(characters) { character ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    CharacterItem(
                        character = character,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}

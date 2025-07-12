package k.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import k.ui.components.CharacterItem
import k.ui_logic.fetchData

@Composable
fun MainScreenUI(
    onItemClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Top,
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            items(5) {
//                CharacterItem(it) {
//                    onItemClick(it)
//                }
//            }
//        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    fetchData()
                }
            ) {
                Text("Fetch data")
            }
        }
    }
}
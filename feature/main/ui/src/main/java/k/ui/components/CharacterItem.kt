package k.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import k.ui_models.model.CharacterUIO

@Composable
fun CharacterItem(
    character: CharacterUIO,
    onItemClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .size(128.dp, 56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
        ),
    ) {
        TextButton(
            onClick = { onItemClick(character.id) },
        ) {
            Text("Navigate to ${character.id}")
        }
    }
}
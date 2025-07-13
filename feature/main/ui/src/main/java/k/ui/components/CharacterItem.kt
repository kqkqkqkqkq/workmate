package k.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import k.ui_models.model.CharacterUIO

@Composable
fun CharacterItem(
    character: CharacterUIO,
    onItemClick: (Int) -> Unit,
) {

    val COLORS = mapOf(
        "Alive" to Color.Green,
        "Dead" to Color.Red,
        "unknow" to Color.LightGray,
    )

    Card(
        modifier = Modifier
            .size(144.dp, 192.dp)
            .padding(
                vertical = 8.dp,
            )
            .clickable {
                onItemClick(character.id)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(144.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.2f)),
                contentAlignment = Alignment.BottomEnd,
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = "character",
                    modifier = Modifier
                        .fillMaxSize(),
                )
                Row(
                    modifier = Modifier
                        .size(
                            width = 72.dp,
                            height = 16.dp,
                        )
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(12.dp, 0.dp, 0.dp, 0.dp),
                        )
                        .padding(
                            horizontal = 8.dp,
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = COLORS[character.status] ?: Color.LightGray,
                                shape = CircleShape,
                            )
                    )
                    Text(
                        text = character.status,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 4.dp
                    )
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.2f)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}
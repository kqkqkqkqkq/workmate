package k.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.2f),
            disabledContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.2f),
            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.2f),
        ),
        textStyle = MaterialTheme.typography.bodySmall,
        placeholder = {
            Text(
                text = "Search character",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                modifier = Modifier,
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    onSearchClicked(text)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (text.isNotEmpty()) onTextChange("")
                    else onCloseClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "clear icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked(text)
            }
        ),
    )
}
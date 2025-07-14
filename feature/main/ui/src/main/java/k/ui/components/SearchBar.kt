package k.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import k.ui_logic.MainViewModel

@Composable
fun SearchBar(
//    viewModel: MainViewModel,
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(48.dp),
        textStyle = MaterialTheme.typography.bodySmall,
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "search icon",
            )
        }
    )
}
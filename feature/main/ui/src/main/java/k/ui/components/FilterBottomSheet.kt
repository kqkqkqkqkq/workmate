package k.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import k.ui_logic.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    viewModel: MainViewModel,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
) {
    val statusAlive by viewModel.statusAlive.collectAsState()
    val statusDead by viewModel.statusDead.collectAsState()
    val statusUnknown by viewModel.statusUnknown.collectAsState()

    val genderMale by viewModel.genderMale.collectAsState()
    val genderFemale by viewModel.genderFemale.collectAsState()
    val genderGenderless by viewModel.genderGenderless.collectAsState()
    val genderUnknown by viewModel.genderUnknown.collectAsState()

    val type by viewModel.type.collectAsState()
    val species by viewModel.species.collectAsState()

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            dragHandle = { BottomSheetDefaults.DragHandle() },
            scrimColor = Black.copy(alpha = 0.5f),
            contentWindowInsets = { WindowInsets(0, 0, 0, 0) },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(
                        vertical = 24.dp,
                        horizontal = 12.dp,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Status:",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier,
                    )
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        CheckBoxRow(
                            text = "Alive",
                            checkedState = statusAlive,
                            onCheckBoxChange = { viewModel.setStatusAlive(it) }
                        )
                        CheckBoxRow(
                            text = "Dead",
                            checkedState = statusDead,
                            onCheckBoxChange = { viewModel.setStatusDead(it) }
                        )
                        CheckBoxRow(
                            text = "Unknown",
                            checkedState = statusUnknown,
                            onCheckBoxChange = { viewModel.setStatusUnknown(it) }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Gender:",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier,
                    )
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        CheckBoxRow(
                            text = "Male",
                            checkedState = genderMale,
                            onCheckBoxChange = { viewModel.setGenderMale(it) }
                        )
                        CheckBoxRow(
                            text = "Female",
                            checkedState = genderFemale,
                            onCheckBoxChange = { viewModel.setGenderFemale(it) }
                        )
                        CheckBoxRow(
                            text = "Genderless",
                            checkedState = genderGenderless,
                            onCheckBoxChange = { viewModel.setGenderGenderless(it) }
                        )
                        CheckBoxRow(
                            text = "Unknown",
                            checkedState = genderUnknown,
                            onCheckBoxChange = { viewModel.setGenderUnknown(it) }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Type:",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier,
                    )
                    OutlinedTextField(
                        value = type,
                        onValueChange = { viewModel.setType(it) },
                        modifier = Modifier
                            .fillMaxWidth(0.6f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.2f),
                            disabledContainerColor = MaterialTheme.colorScheme.onBackground.copy(
                                0.2f
                            ),
                            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(
                                0.2f
                            ),
                        ),
                        textStyle = MaterialTheme.typography.bodySmall,
                        singleLine = true,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Species:",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier,
                    )
                    OutlinedTextField(
                        value = species,
                        onValueChange = { viewModel.setSpecies(it) },
                        modifier = Modifier
                            .fillMaxWidth(0.6f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.2f),
                            disabledContainerColor = MaterialTheme.colorScheme.onBackground.copy(
                                0.2f
                            ),
                            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(
                                0.2f
                            ),
                        ),
                        textStyle = MaterialTheme.typography.bodySmall,
                        singleLine = true,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        val status = buildList {
                            if (statusAlive) add("Alive")
                            if (statusDead) add("Dead")
                            if (statusUnknown) add("Unknown")
                        }

                        val gender = buildList {
                            if (genderMale) add("Male")
                            if (genderFemale) add("Female")
                            if (genderGenderless) add("Genderless")
                            if (genderUnknown) add("Unknown")
                        }

                        viewModel.filter(
                            name = null,
                            status = status.takeIf { it.isNotEmpty() },
                            species = species.takeIf { it.isNotEmpty() },
                            gender = gender.takeIf { it.isNotEmpty() },
                            type = type.takeIf { it.isNotEmpty() },
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = "Apply",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.background,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}
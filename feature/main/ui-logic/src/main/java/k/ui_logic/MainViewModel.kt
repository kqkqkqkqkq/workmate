package k.ui_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.characters_data.ICharacterRepository
import k.ui_models.mapper.toCharacterUIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ICharacterRepository,
): ViewModel() {

    private val _state = MutableStateFlow<MainScreenState>(MainScreenState.Initial)
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    fun getAllCharacters() {
        viewModelScope.launch {
            _state.value = MainScreenState.Loading
            try {
                val characters = repository.getAllCharacters()
                _state.value = MainScreenState.Content(
                    characters = characters.map { it.toCharacterUIO() }
                )
            } catch (e: Exception) {
                _state.value = MainScreenState.Failure(
                    message = "Unexpected error: ${e.message ?: "Unknown error"}"
                )
            }
        }
    }
}
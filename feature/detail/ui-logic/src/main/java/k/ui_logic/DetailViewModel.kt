package k.ui_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.characters_data.ICharacterRepository
import k.ui_models.mapper.toCharacterUIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ICharacterRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Initial)
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = DetailScreenState.Loading
            try {
                val character = repository.getCharacter(id).toCharacterUIO()
                _state.value = DetailScreenState.Content(
                    DetailScreenContentState(
                        character = character
                    )
                )
            } catch (e: Exception) {
                _state.value = DetailScreenState.Failure(
                    message = "Unexpected error: ${e.message ?: "Unknown error"}"
                )
            }
        }
    }
}
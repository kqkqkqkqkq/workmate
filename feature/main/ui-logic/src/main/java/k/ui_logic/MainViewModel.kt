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

class MainViewModel(
    private val repository: ICharacterRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<MainScreenState>(MainScreenState.Initial)
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val search: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearch: StateFlow<Boolean> = _isSearching.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun getNextPage() {
        TODO()
    }

    fun getPreviousPage() {
        TODO()
    }
}
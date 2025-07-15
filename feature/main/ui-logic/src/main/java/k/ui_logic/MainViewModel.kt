package k.ui_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.characters_data.ICharacterRepository
import k.ui_logic.utils.Constants
import k.ui_logic.utils.SearchState
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
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _searchState = MutableStateFlow(SearchState.CLOSED)
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    private val _pagerSize = MutableStateFlow(Constants.TOTAL_PAGES)
    val pagerSize: StateFlow<Int> = _pagerSize.asStateFlow()

    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MainScreenState.Loading
            try {
                val characters =
                    repository.getAllCharacters(_currentPage.value, Constants.PAGE_SIZE)
                _state.value = MainScreenState.Content(
                    characters = characters.map { it.toCharacterUIO() }
                )
                _pagerSize.value = repository.getDatabaseSize() / Constants.PAGE_SIZE + 1
            } catch (e: Exception) {
                _state.value = MainScreenState.Failure(
                    message = "Unexpected error: ${e.message ?: "Unknown error"}"
                )
            }
        }
    }

    fun selectPage(newPage: Int) {
        _currentPage.value = newPage
        getAllCharacters()
    }

    fun changeSearchText(newText: String) {
        _searchText.value = newText
    }

    fun changeSearchState(newState: SearchState) {
        _searchState.value = newState
    }

    fun search(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MainScreenState.Loading
            try {
                val characters = repository.searchCharacterByName(text)
                _state.value = MainScreenState.Content(
                    characters = characters.map { it.toCharacterUIO() }
                )
                _pagerSize.value = characters.size / Constants.PAGE_SIZE + 1
            } catch (e: Exception) {
                _state.value = MainScreenState.Failure(
                    message = "Unexpected error: ${e.message ?: "Unknown error"}"
                )
            }
        }
    }

    fun filter() {
//        _pagerSize.value = (characters.size / Constants.PAGE_SIZE) + 1
    }

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MainScreenState.Loading
            try {
                repository.loadCharacters()
            } catch (e: Exception) {
                try {
                    val characters =
                        repository.getAllCharacters(_currentPage.value, Constants.PAGE_SIZE)
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
}
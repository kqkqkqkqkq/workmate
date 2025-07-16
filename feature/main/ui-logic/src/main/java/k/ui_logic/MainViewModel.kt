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
                _pagerSize.value = 1
            } catch (e: Exception) {
                _state.value = MainScreenState.Failure(
                    message = "Unexpected error: ${e.message ?: "Unknown error"}"
                )
            }
        }
    }

    fun filter(
        name: String?,
        status: List<String>?,
        species: String?,
        type: String?,
        gender: List<String>?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MainScreenState.Loading
            try {
                val characters = repository.filterCharacters(
                    name = name,
                    status = status,
                    species = species,
                    type = type,
                    gender = gender,
                )
                _state.value = MainScreenState.Content(
                    characters = characters.map { it.toCharacterUIO() }
                )
                _pagerSize.value = 1
            } catch (e: Exception) {
                _state.value = MainScreenState.Failure(
                    message = "Unexpected error: ${e.message ?: "Unknown error"}"
                )
            }
        }
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
                    _statusAlive.value = false
                    _statusDead.value = false
                    _statusUnknown.value = false
                    _genderMale.value = false
                    _genderFemale.value = false
                    _genderGenderless.value = false
                    _genderUnknown.value = false
                    _type.value = ""
                    _species.value = ""
                } catch (e: Exception) {
                    _state.value = MainScreenState.Failure(
                        message = "Unexpected error: ${e.message ?: "Unknown error"}"
                    )
                }
            }
        }
    }

    private val _statusAlive = MutableStateFlow(false)
    val statusAlive: StateFlow<Boolean> = _statusAlive.asStateFlow()

    private val _statusDead = MutableStateFlow(false)
    val statusDead: StateFlow<Boolean> = _statusDead.asStateFlow()

    private val _statusUnknown = MutableStateFlow(false)
    val statusUnknown: StateFlow<Boolean> = _statusUnknown.asStateFlow()

    private val _genderMale = MutableStateFlow(false)
    val genderMale: StateFlow<Boolean> = _genderMale.asStateFlow()

    private val _genderFemale = MutableStateFlow(false)
    val genderFemale: StateFlow<Boolean> = _genderFemale.asStateFlow()

    private val _genderGenderless = MutableStateFlow(false)
    val genderGenderless: StateFlow<Boolean> = _genderGenderless.asStateFlow()

    private val _genderUnknown = MutableStateFlow(false)
    val genderUnknown: StateFlow<Boolean> = _genderUnknown.asStateFlow()

    private val _type = MutableStateFlow("")
    val type: StateFlow<String> = _type.asStateFlow()

    private val _species = MutableStateFlow("")
    val species: StateFlow<String> = _species.asStateFlow()

    fun setStatusAlive(value: Boolean) {
        _statusAlive.value = value
    }

    fun setStatusDead(value: Boolean) {
        _statusDead.value = value
    }

    fun setStatusUnknown(value: Boolean) {
        _statusUnknown.value = value
    }

    fun setGenderMale(value: Boolean) {
        _genderMale.value = value
    }

    fun setGenderFemale(value: Boolean) {
        _genderFemale.value = value
    }

    fun setGenderGenderless(value: Boolean) {
        _genderGenderless.value = value
    }

    fun setGenderUnknown(value: Boolean) {
        _genderUnknown.value = value
    }

    fun setType(value: String) {
        _type.value = value
    }

    fun setSpecies(value: String) {
        _species.value = value
    }
}
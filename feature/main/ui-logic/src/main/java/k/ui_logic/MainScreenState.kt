package k.ui_logic

import k.ui_models.model.CharacterUIO


sealed interface MainScreenState {
    data object Initial: MainScreenState

    data object Loading: MainScreenState

    data class Failure(val message: String?): MainScreenState

    data class Content(val characters: List<CharacterUIO>): MainScreenState
}
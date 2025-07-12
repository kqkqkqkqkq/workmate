package k.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen: NavKey {
    @Serializable
    data object Main: Screen()
    @Serializable
    data class Detail(val id: Int): Screen()
}
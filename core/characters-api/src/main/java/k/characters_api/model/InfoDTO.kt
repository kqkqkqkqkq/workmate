package k.characters_api.model

import kotlinx.serialization.Serializable

@Serializable
data class InfoDTO (
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)
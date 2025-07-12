package k.characters_api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginDTO(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)
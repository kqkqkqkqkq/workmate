package k.characters_api.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO(
    val info: InfoDTO,
    val results: List<CharacterDTO>,
)


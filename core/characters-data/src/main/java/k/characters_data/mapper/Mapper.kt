package k.characters_data.mapper

import k.characters_api.model.CharacterDTO
import k.characters_api.model.LocationDTO
import k.characters_api.model.OriginDTO
import k.characters_data.model.Character
import k.characters_data.model.Location
import k.characters_data.model.Origin
import k.characters_database.model.CharacterDBO

private const val SEPARATOR = "#"

private fun convertToString(list: List<String>): String = list.joinToString(SEPARATOR)

private fun convertToArray(string: String): List<String> =
    if (string.isEmpty()) emptyList() else string.split(SEPARATOR)

fun CharacterDTO.toCharacterDBO() = CharacterDBO(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    originName = origin.name,
    originUrl = origin.url,
    locationName = location.name,
    locationUrl = location.url,
    image = image,
    episode = convertToString(episode),
    url = url,
    created = created,
)

fun CharacterDBO.toCharacterDTO() = CharacterDTO(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = OriginDTO(
        name = originName,
        url = originUrl,
    ),
    location = LocationDTO(
        name = locationName,
        url = locationUrl,
    ),
    image = image,
    episode = convertToArray(episode),
    url = url,
    created = created,
)

fun CharacterDTO.toCharacter() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = Origin(
        name = origin.name,
        url = origin.url,
    ),
    location = Location(
        name = location.name,
        url = location.url,
    ),
    image = image,
    episode = episode,
    url = url,
    created = created,
)

fun CharacterDBO.toCharacter() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = Origin(
        name = originName,
        url = originUrl,
    ),
    location = Location(
        name = locationName,
        url = locationUrl,
    ),
    image = image,
    episode = convertToArray(episode),
    url = url,
    created = created,
)

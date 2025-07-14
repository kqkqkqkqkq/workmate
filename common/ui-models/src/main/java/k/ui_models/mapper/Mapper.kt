package k.ui_models.mapper

import k.characters_data.model.Character
import k.characters_data.model.Location
import k.characters_data.model.Origin
import k.ui_models.model.CharacterUIO
import k.ui_models.model.LocationUIO
import k.ui_models.model.OriginUIO

fun Character.toCharacterUIO() = CharacterUIO(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = OriginUIO(
        name = origin.name,
        url = origin.url,
    ),
    location = LocationUIO(
        name = location.name,
        url = location.url,
    ),
    image = image,
    episode = episode,
    url = url,
    created = created,
)

fun CharacterUIO.toCharacter() = Character(
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

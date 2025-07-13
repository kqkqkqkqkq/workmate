package k.characters_data

import k.characters_data.model.Character

interface ICharacterRepository {
    suspend fun getAllCharacters(): List<Character>

    suspend fun getCharacter(id: Int): Character
}
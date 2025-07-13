package k.characters_data

import k.characters_api.CharacterApi
import k.characters_data.mapper.toCharacter
import k.characters_data.model.Character
import k.characters_database.CharacterDatabase

class CharacterRepository(
    private val database: CharacterDatabase,
    private val api: CharacterApi,
) : ICharacterRepository {
    override suspend fun getAllCharacters(): List<Character> =
        api.getAllCharacters().results.map { it.toCharacter() }

    override suspend fun getCharacter(id: Int): Character {
        TODO("Not yet implemented")
    }

}
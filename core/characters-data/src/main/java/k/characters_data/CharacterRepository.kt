package k.characters_data

import k.characters_api.CharacterApi
import k.characters_data.mapper.toCharacter
import k.characters_data.mapper.toCharacterDBO
import k.characters_data.model.Character
import k.characters_database.CharacterDatabase
import k.characters_database.model.CharacterDBO

class CharacterRepository(
    private val database: CharacterDatabase,
    private val api: CharacterApi,
) : ICharacterRepository {

    private suspend fun loadCharacters() {
        val info = api.getAllCharacters()
        val totalPages = info.info.pages
        val characters = mutableListOf<CharacterDBO>()
        characters += info.results.map { it.toCharacterDBO() }
        for (page in 2..totalPages) {
            characters += api.getAllCharacters(page = page).results.map { it.toCharacterDBO() }
        }
        database.dao.insertCharacters(characters)
    }

    override suspend fun getAllCharacters(): List<Character> {
        // TODO("create pagination")
        val characters = database.dao.getAllCharacters()
        if (characters.isEmpty()) {
            loadCharacters()
            return database.dao.getAllCharacters().map { it.toCharacter() }
        }
        return characters.map { it.toCharacter() }
    }

    override suspend fun getCharacter(id: Int): Character {
        val character = database.dao.getCharacter(id).toCharacter()
        return character
    }

    override suspend fun searchCharacterByName(name: String): Character? {
        val character = database.dao.searchCharacterByName(name)?.toCharacter()
        return character
    }

}
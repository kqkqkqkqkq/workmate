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

    override suspend fun loadCharacters() {
        val info = api.getAllCharacters()
        val totalPages = info.info.pages
        val characters = mutableListOf<CharacterDBO>()
        characters += info.results.map { it.toCharacterDBO() }
        for (page in 2..totalPages) {
            characters += api.getAllCharacters(page = page).results.map { it.toCharacterDBO() }
        }
        database.dao.insertCharacters(characters)
    }

    override suspend fun getAllCharacters(page: Int, pageSize: Int): List<Character> {
        val offset = (page - 1) * pageSize
        val characters = database.dao.getPage(
            limit = pageSize,
            offset = offset,
        )
        if (characters.isEmpty()) {
            loadCharacters()
            return database.dao.getPage(
                limit = pageSize,
                offset = offset,
            ).map { it.toCharacter() }
        }
        return characters.map { it.toCharacter() }
    }

    override suspend fun getCharacter(id: Int): Character {
        val character = database.dao.getCharacter(id).toCharacter()
        return character
    }

    override suspend fun searchCharacterByName(name: String): List<Character> {
        val character = database.dao.searchCharacterByName(name).map { it.toCharacter() }
        return character
    }

    override suspend fun getDatabaseSize(): Int =
        database.dao.getDatabaseSize()

    override suspend fun filterCharacters(
        name: String?,
        status: List<String>?,
        species: String?,
        type: String?,
        gender: List<String>?,
    ): List<Character> {
        val character = database.dao.filterCharacters(
            name,
            status,
            species,
            type,
            gender,
        ).map { it.toCharacter() }
        return character
    }

}
package k.characters_data

import android.util.Log
import k.characters_api.CharacterApi
import k.characters_data.mapper.toCharacter
import k.characters_data.mapper.toCharacterDBO
import k.characters_data.model.Character
import k.characters_database.CharacterDatabase
import k.characters_database.model.CharacterDBO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class CharacterRepository(
    private val database: CharacterDatabase,
    private val api: CharacterApi,
) : ICharacterRepository {

    private suspend fun loadCharacters() {
        val info = api.getAllCharacters()
        val totalPages = info.info.pages
        val characterDBO = mutableListOf<CharacterDBO>()
        characterDBO += info.results.map { it.toCharacterDBO() }
        for (page in 2..totalPages) {
            characterDBO += api.getAllCharacters(page = page).results.map { it.toCharacterDBO() }
        }
        Log.e("[DATA]", characterDBO.size.toString())
    }

    override suspend fun getAllCharacters(): List<Character> {
        loadCharacters()
        return api.getAllCharacters().results.map { it.toCharacter() }
    }

    override suspend fun getCharacter(id: Int): Character {
        TODO("Not yet implemented")
    }

}
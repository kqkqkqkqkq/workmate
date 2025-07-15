package k.characters_data

import k.characters_data.model.Character

interface ICharacterRepository {
    suspend fun loadCharacters()

    suspend fun getAllCharacters(page: Int, pageSize: Int): List<Character>

    suspend fun getCharacter(id: Int): Character

    suspend fun searchCharacterByName(name: String): List<Character>

    suspend fun getDatabaseSize(): Int

    suspend fun filterCharacters(
        name: String?,
        status: List<String>?,
        species: String?,
        type: String?,
        gender: List<String>?,
    ): List<Character>
}
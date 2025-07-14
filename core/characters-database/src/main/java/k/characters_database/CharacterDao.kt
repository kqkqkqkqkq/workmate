package k.characters_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import k.characters_database.model.CharacterDBO
import k.characters_database.utils.Constants.TABLE_NAME

@Dao
interface CharacterDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllCharacters(): List<CharacterDBO>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterDBO

    @Query("SELECT * FROM $TABLE_NAME WHERE name = :name")
    suspend fun searchCharacterByName(name: String): CharacterDBO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterDBO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterDBO>)

    @Delete
    suspend fun delete(character: CharacterDBO)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clean()

    @Query(
        """
        SELECT * FROM $TABLE_NAME
        WHERE (:name IS NULL OR name LIKE :name)
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species = :species)
          AND (:type IS NULL OR type = :type)
          AND (:gender IS NULL OR gender = :gender)
    """
    )
    suspend fun filterCharacters(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null,
    ): List<CharacterDBO>
}
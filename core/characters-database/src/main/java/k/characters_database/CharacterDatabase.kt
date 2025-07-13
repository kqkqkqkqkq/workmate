package k.characters_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import k.characters_database.model.CharacterDBO
import k.characters_database.utils.Constants.DATABASE_NAME

class CharacterDatabase internal constructor(private val database: CharacterRoomDatabase) {
    val dao: CharacterDao
        get() = database.characterDao()
}

@Database(entities = [CharacterDBO::class], version = 1, exportSchema = false)
internal abstract class CharacterRoomDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

fun createCharacterDatabase(context: Context): CharacterDatabase {
    val characterRoomDatabase = Room.databaseBuilder(
        checkNotNull(context.applicationContext),
        CharacterRoomDatabase::class.java,
        DATABASE_NAME,
    ).build()
    return CharacterDatabase(characterRoomDatabase)
}
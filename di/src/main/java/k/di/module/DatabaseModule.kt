package k.di.module

import k.characters_database.CharacterDatabase
import k.characters_database.createCharacterDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<CharacterDatabase> {
        createCharacterDatabase(context = get())
    }
}
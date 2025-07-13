package k.di.module

import k.characters_data.CharacterRepository
import k.characters_data.ICharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ICharacterRepository> {
        CharacterRepository(
            database = get(),
            api = get(),
        )
    }
}
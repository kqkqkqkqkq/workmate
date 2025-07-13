package k.di.module

import k.characters_api.CharacterApi
import k.characters_api.createCharacterApi
import org.koin.dsl.module

private const val BASE_URL = "https://rickandmortyapi.com/api/"

val apiModule = module {
    single<CharacterApi> {
        createCharacterApi(BASE_URL)
    }
}

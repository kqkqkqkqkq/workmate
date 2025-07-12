package k.ui_logic

import android.util.Log
import k.characters_api.createCharacterApi
import kotlinx.coroutines.runBlocking

fun fetchData () = runBlocking{
    val retrofit = createCharacterApi("https://rickandmortyapi.com/api/")

    Log.e("[DATA]", retrofit.getAllCharacters().results[0].toString())
}
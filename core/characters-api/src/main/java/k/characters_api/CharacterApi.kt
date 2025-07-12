package k.characters_api


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import k.characters_api.model.CharacterDTO
import k.characters_api.model.ResponseDTO
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * [API Documentation](https://rickandmortyapi.com/documentation)
 */
interface CharacterApi {
    @GET("character")
    suspend fun getAllCharacters(): ResponseDTO

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): CharacterDTO

//    @GET("character/")
//    suspend fun filterCharacters(): ResponseDTO
//    TODO("add filter")
}

fun createCharacterApi(
    baseUrl: String,
): CharacterApi = retrofit(baseUrl, Json).create()

private fun retrofit(
    baseUrl: String,
    json: Json,
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .build()
}
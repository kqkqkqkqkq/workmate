package k.characters_api


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import k.characters_api.model.CharacterDTO
import k.characters_api.model.ResponseDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * [API Documentation](https://rickandmortyapi.com/documentation)
 */
interface CharacterApi {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1,
    ): ResponseDTO

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): CharacterDTO

    @GET("character")
    suspend fun filterCharacters(
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null,
    ): ResponseDTO
}

fun createCharacterApi(
    baseUrl: String,
): CharacterApi = buildRetrofit(baseUrl, Json).create()

private fun buildRetrofit(
    baseUrl: String,
    json: Json,
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .build()
}
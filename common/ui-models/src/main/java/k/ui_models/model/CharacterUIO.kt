package k.ui_models.model

data class CharacterUIO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginUIO,
    val location: LocationUIO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

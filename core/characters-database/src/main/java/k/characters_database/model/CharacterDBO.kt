package k.characters_database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import k.characters_database.utils.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CharacterDBO(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("status") val status: String,
    @ColumnInfo("species") val species: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("gender") val gender: String,
    @ColumnInfo("originName") val originName: String,
    @ColumnInfo("originUrl") val originUrl: String,
    @ColumnInfo("locationName") val locationName: String,
    @ColumnInfo("locationUrl") val locationUrl: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("episode") val episode: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("created") val created: String,
)


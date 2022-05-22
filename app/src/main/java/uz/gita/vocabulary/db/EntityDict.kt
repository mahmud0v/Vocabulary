package uz.gita.vocabulary.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "eng_uz")
data class EntityDict(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id:Int,
    val en_word:String?,
    val type:String?,
    var transcript:String?,
    val translation:String?,
    val description:String?,
    var countable:String?,
    var favourite:Int?,
):Parcelable
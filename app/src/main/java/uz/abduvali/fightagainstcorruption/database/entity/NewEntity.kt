package uz.abduvali.fightagainstcorruption.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class NewEntity(
    @PrimaryKey
    val url: String,
    val author: String,
    val content: String,
    val description: String,
    val title: String,
    val theme: String,
    val urlToImage: String?
) : Serializable
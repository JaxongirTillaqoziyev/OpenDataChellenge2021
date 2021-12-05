package uz.abduvali.fightagainstcorruption.models

data class NewData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
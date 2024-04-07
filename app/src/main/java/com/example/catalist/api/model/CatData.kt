package com.example.catalist.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CatData(
    val id: String,
    val name: String,
    val alt_names: String = "",
    val description: String,
    val origin: String,
    val temperament: String,
    val life_span: String,
    val weight: Weight,
    val adaptability: Int,
    val stranger_friendly: Int,
    val grooming: Int,
    val dog_friendly: Int,
    val energy_level: Int,
    val rare: Int,
    val wikipedia_url: String = "",
    val image : Image = Image(
        id = "",
        url = "",
        width = 0,
        height = 0,
    )
)
@Serializable
data class Weight(
    val imperial: String,
    val metric: String,
)
@Serializable
data class Image(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
)

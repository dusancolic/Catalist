package com.example.catalist.model

class CatData(
    val id: String,
    val name: String,
    val alternativeName: String?,
    val description: String,
    val origin: String,
    val temperament: String,
    val lifeSpan: String,
    val weight: String,
    val adaptability: Int,
    val affectionLevel: Int,
    val childFriendly: Int,
    val dogFriendly: Int,
    val energyLevel: Int,
    val rare: Int,
    val link: String,
) {
}
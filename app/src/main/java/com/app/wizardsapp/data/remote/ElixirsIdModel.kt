package com.app.wizardsapp.data.remote

data class ElixirsIdModel(
    val characteristics: String,
    val difficulty: String,
    val effect: String,
    val id: String,
    val ingredients: List<Ingredients>,
    val inventors: List<Inventor>,
    val manufacturer: String,
    val name: String,
    val sideEffects: String,
    val time: String
)

data class Inventor(
    val firstName: String,
    val id: String,
    val lastName: String
)

data class Ingredients(
    val id: String,
    val name: String,
)
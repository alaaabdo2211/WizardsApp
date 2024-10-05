package com.app.wizardsapp.data.remote

data class ElixirsIdModel(
    val characteristics: Any,
    val difficulty: String,
    val effect: String,
    val id: String,
    val ingredients: List<Ingredients>,
    val inventors: List<Inventor>,
    val manufacturer: Any,
    val name: String,
    val sideEffects: Any,
    val time: Any
)

data class Inventor(
    val firstName: Any,
    val id: String,
    val lastName: String
)

data class Ingredients(
    val id: String,
    val name: String,
)
package com.app.wizardsapp.data.remote

data class WizardsByIdModel(
    val elixirs: List<Elixir>,
    val firstName: String?,
    val id: String,
    val lastName: String?
)


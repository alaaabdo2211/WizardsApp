package com.app.wizardsapp.data.remote

class WizardsModel : ArrayList<WizardsModelItem>()

data class WizardsModelItem(
    val elixirs: List<Elixir>,
    val firstName: String,
    val id: String,
    val lastName: String
)
package com.app.wizardsapp.ui.theme

sealed class Screens(val name: String) {
    data object Wizards : Screens("Wizards")
    data object WizardDetails : Screens("WizardDetails")
    data object Elixirs : Screens("Elixirs")
}
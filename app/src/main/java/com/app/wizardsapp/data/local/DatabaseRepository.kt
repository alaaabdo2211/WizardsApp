package com.app.wizardsapp.data.local

import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val wizardsDoa: WizardsDoa) {

    suspend fun getWizards(): List<Wizards> {
        return wizardsDoa.getWizards()
    }

    suspend fun saveWizards(wizardsModel: Wizards) {
        wizardsDoa.insertWizards(wizardsModel)
    }


    suspend fun getWizardsById(id: String): WizardsByIdData? {
        return wizardsDoa.getWizardsById(id)
    }
    suspend fun saveWizardsById(wizardsByIdModel: WizardsByIdData) {
        wizardsDoa.insertWizardsById(wizardsByIdModel)
    }


    suspend fun getElixirsById(id: String): ElixirsByIdData? {
        return wizardsDoa.getElixirsById(id)
    }
    suspend fun saveElixirsById(elixirsIdModel: ElixirsByIdData) {
        wizardsDoa.insertElixirsById(elixirsIdModel)
    }

}
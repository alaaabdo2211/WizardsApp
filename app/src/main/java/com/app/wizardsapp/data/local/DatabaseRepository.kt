package com.app.wizardsapp.data.local

import com.app.wizardsapp.data.remote.ElixirsIdModel
import com.app.wizardsapp.data.remote.WizardsByIdModel
import com.app.wizardsapp.data.remote.WizardsModel
class DatabaseRepository (private val wizardsDoa: WizardsDoa,private val wizardByIdDoa: WizardByIdDoa,private val elixirsByIdDoa: ElixirsByIdDoa){

        suspend fun getWizards() : Wizards {
            return wizardsDoa.getWizards()
        }
    suspend fun saveWizards(wizardsModel: Wizards) {
        wizardsDoa.insertWizards(wizardsModel)
    }


    suspend fun getWizardsById(): WizardsByIdData {
        return wizardByIdDoa.getWizardsById()
    }
    suspend fun saveWizardsById(wizardsByIdModel: WizardsByIdData) {
        wizardByIdDoa.insertWizardsById(wizardsByIdModel)
    }


    suspend fun getElixirsById(): ElixirsByIdData {
        return elixirsByIdDoa.getElixirsById()
    }
    suspend fun saveElixirsById(elixirsIdModel: ElixirsByIdData) {
        elixirsByIdDoa.insertElixirsById(elixirsIdModel)
    }

}
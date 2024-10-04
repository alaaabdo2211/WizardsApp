package com.app.wizardsapp.data

import com.app.wizardsapp.data.remote.ElixirsIdModel
import com.app.wizardsapp.data.remote.WizardsByIdModel
import com.app.wizardsapp.data.remote.WizardsModel
import com.app.wizardsapp.network.ApiService

class WizardsRepository(private val apiService: ApiService) {

    suspend fun getWizards(): WizardsModel {
        return apiService.getWizards()
    }

    suspend fun getWizardsById(id: String): WizardsByIdModel {
        return apiService.getWizardsById(id)
    }

    suspend fun getElixirsById(id: String): ElixirsIdModel {
        return apiService.getElixirs(id)
    }
}
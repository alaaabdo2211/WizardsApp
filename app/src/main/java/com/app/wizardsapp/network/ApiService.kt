package com.app.wizardsapp.network
import com.app.wizardsapp.data.remote.ElixirsIdModel
import com.app.wizardsapp.data.remote.WizardsByIdModel
import com.app.wizardsapp.data.remote.WizardsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("Wizards")
    suspend fun getWizards(): WizardsModel

    @GET ("Wizards/{id}")
    suspend fun getWizardsById(@Path("id") id: String) : WizardsByIdModel

    @GET ("Elixirs/{id}")
    suspend fun getElixirs(@Path("id") id: String) : ElixirsIdModel

}
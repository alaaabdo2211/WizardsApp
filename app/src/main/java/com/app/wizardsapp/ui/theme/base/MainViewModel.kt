package com.app.wizardsapp.ui.theme.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.wizardsapp.data.ApiRepository
import com.app.wizardsapp.data.local.DatabaseRepository
import com.app.wizardsapp.data.local.ElixirsByIdData
import com.app.wizardsapp.data.local.Wizards
import com.app.wizardsapp.data.local.WizardsByIdData
import com.app.wizardsapp.data.remote.ElixirsIdModel
import com.app.wizardsapp.data.remote.WizardsByIdModel
import com.app.wizardsapp.data.remote.WizardsModel
import com.app.wizardsapp.data.remote.WizardsModelItem
import com.app.wizardsapp.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Collections.addAll
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApiRepository, private val databseRepository: DatabaseRepository
) : ViewModel() {

    private val _wizardsState = MutableStateFlow<ApiState<WizardsModel>>(ApiState.Loading)
    val wizardsState: StateFlow<ApiState<WizardsModel>> = _wizardsState


    private val _wizardsByIdState = MutableStateFlow<ApiState<WizardsByIdModel>>(ApiState.Loading)
    val wizardsByIdState: StateFlow<ApiState<WizardsByIdModel>> = _wizardsByIdState

    private val _elixirsByIdState = MutableStateFlow<ApiState<ElixirsIdModel>>(ApiState.Loading)
    val elixirsByIdState: StateFlow<ApiState<ElixirsIdModel>> = _elixirsByIdState


    fun fetchWizards() {

        viewModelScope.launch {
            _wizardsState.value = ApiState.Loading

            try {

                val wizards = repository.getWizards()  // API call
                _wizardsState.value = ApiState.Success(wizards)
                wizards.forEach {
                    databseRepository.saveWizards(
                        Wizards(
                            elixirs = it.elixirs,
                            id = it.id,
                            firstName = it.firstName ?: "",
                            lastName = it.lastName ?: "",
                        )
                    )
                }
                Log.d("wizards", repository.getWizards().toString())

            } catch (e: Exception) {
                val wizards = databseRepository.getWizards()

                val data = WizardsModel().apply {
                    addAll(wizards.map {
                        WizardsModelItem(
                            elixirs = it.elixirs,
                            id = it.id,
                            firstName = it.firstName,
                            lastName = it.lastName,
                        )
                    })
                }

                if (data.isEmpty()) {
                    _wizardsState.value = ApiState.Error(message = "Failed to fetch data: ${e.message}",
                        onRetry = { fetchWizards() }  // Retry logic
                    )
                }else{
                    _wizardsState.value = ApiState.Success(data)
                }

                Log.e("error handle data", e.toString())

            }
        }
    }

    fun fetchWizardById(id: String) {
        viewModelScope.launch {
            _wizardsByIdState.value = ApiState.Loading

            try {
                val wizardById = repository.getWizardsById(id)
                _wizardsByIdState.value = ApiState.Success(wizardById)
                databseRepository.saveWizardsById(
                    WizardsByIdData(
                        id = id,
                        firstName = wizardById.firstName.toString(),
                        lastName = wizardById.lastName.toString(),
                        elixirs = wizardById.elixirs
                    )
                )
                Log.d(
                    "wizardsById",
                    repository.getWizardsById("118e7366-1c65-4275-8121-8f6c553e5783").toString()
                )
            } catch (e: Exception) {
                val wizardsById = databseRepository.getWizardsById(id)

                if (wizardsById == null) {
                    _wizardsByIdState.value =
                        ApiState.Error(message = "Failed to fetch data: ${e.message}",
                            onRetry = { fetchWizards() }  // Retry logic
                        )
                } else {
                    _wizardsByIdState.value = ApiState.Success(
                        WizardsByIdModel(
                            id = id,
                            firstName = wizardsById.firstName,
                            lastName = wizardsById.lastName,
                            elixirs = wizardsById.elixirs
                        )
                    )
                }

                Log.e("error handle data", e.toString())
            }
        }
    }

    fun fetchElixirsById(id: String) {
        viewModelScope.launch {
            _elixirsByIdState.value = ApiState.Loading

            try {
                val elixirs = repository.getElixirsById(id)
                _elixirsByIdState.value = ApiState.Success(elixirs)
                databseRepository.saveElixirsById(
                    ElixirsByIdData(
                        id = elixirs.id ?: "",
                        name = elixirs.name ?: "",
                        effect = elixirs.effect ?: "",
                        time = elixirs.time ?: "",
                        sideEffects = elixirs.sideEffects ?: "",
                        manufacturer = elixirs.manufacturer ?: "",
                        inventors = elixirs.inventors,
                        characteristics = elixirs.characteristics ?: "",
                        difficulty = elixirs.difficulty ?: "",
                        ingredients = elixirs.ingredients,
                    )
                )
                Log.d(
                    "elixirsById",
                    repository.getElixirsById("4979b527-74c8-4a3c-8879-53178eb9a73d").toString()
                )
            } catch (e: Exception) {
                val elixirById = databseRepository.getElixirsById(id)

                if (elixirById == null) {
                    _elixirsByIdState.value =
                        ApiState.Error(message = "Failed to fetch data: ${e.message}",
                            onRetry = { fetchWizards() }  // Retry logic
                        )
                } else {
                    _elixirsByIdState.value = ApiState.Success(
                        ElixirsIdModel(
                            id = id,
                            name = elixirById.name ?: "",
                            effect = elixirById.effect ?: "",
                            time = elixirById.time ?: "",
                            sideEffects = elixirById.sideEffects ?: "",
                            manufacturer = elixirById.manufacturer ?: "",
                            inventors = elixirById.inventors,
                            characteristics = elixirById.characteristics ?: "",
                            difficulty = elixirById.difficulty ?: "",
                            ingredients = elixirById.ingredients,
                        )
                    )
                }

                Log.e("error handle data", e.toString())
            }
        }
    }


}


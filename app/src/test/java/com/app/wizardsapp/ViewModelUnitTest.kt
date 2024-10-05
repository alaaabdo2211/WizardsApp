package com.app.wizardsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.wizardsapp.data.ApiRepository
import com.app.wizardsapp.data.remote.Elixir
import com.app.wizardsapp.data.remote.ElixirsIdModel
import com.app.wizardsapp.data.remote.Ingredients
import com.app.wizardsapp.data.remote.Inventor
import com.app.wizardsapp.data.remote.WizardsByIdModel
import com.app.wizardsapp.data.remote.WizardsModel
import com.app.wizardsapp.ui.theme.base.MainViewModel
import com.app.wizardsapp.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)

class ViewModelUnitTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        Dispatchers.setMain(testDispatcher)

        viewModel = MainViewModel(apiRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchWizards success`() = runTest {

        val mockWizards = WizardsModel()
        `when`(apiRepository.getWizards()).thenReturn(mockWizards)


        viewModel.fetchWizards()


        val result = viewModel.wizardsState.first()
        assert(result is ApiState.Success && result.data == mockWizards)
        verify(apiRepository).getWizards()
    }

    @Test
    fun `fetchWizards failure`() = runTest {
        val exception = Exception("Network error")
        `when`(apiRepository.getWizards()).thenThrow(exception)

        viewModel.fetchWizards()

        val result = viewModel.wizardsState.first()
        assert(result is ApiState.Error && result.message == "Failed to fetch data: Network error")
        verify(apiRepository).getWizards()
    }

    @Test
    fun `fetchWizardById success`() = runTest {
        val mockWizardById = WizardsByIdModel(
            id = "599265f8-029a-45ea-9a66-29715f34aef0",
            firstName = "Glover",
            lastName = "Hipworth",
            elixirs = listOf(
                Elixir(
                    id = "a22648df-4c70-4da0-9c9f-fe2c88c05df1", name = "Grand Pepperup Potion"
                ), Elixir(
                    id = "cb01e99b-8298-4402-9d6a-47b96c44ba2c", name = "Pepperup Potion"
                )
            )

        )
        `when`(apiRepository.getWizardsById("599265f8-029a-45ea-9a66-29715f34aef0")).thenReturn(
            mockWizardById
        )

        viewModel.fetchWizardById("599265f8-029a-45ea-9a66-29715f34aef0")

        val result = viewModel.wizardsByIdState.first()
        assert(result is ApiState.Success && result.data == mockWizardById)
        verify(apiRepository).getWizardsById("599265f8-029a-45ea-9a66-29715f34aef0")
    }

    @Test
    fun `fetchElixirsById success`() = runTest {
        val mockElixirsById = ElixirsIdModel(
            id = "a22648df-4c70-4da0-9c9f-fe2c88c05df1",
            name = "Grand Pepperup Potion",
            effect = "Relieves and/or cures cold symptoms",
            sideEffects = "Causes steam to come out of the drinker's ears for a few hours",
            characteristics = "Red in colour",
            time = "",
            difficulty = "Unknown",
            ingredients = listOf(
                Ingredients(
                    id = "45011b45-36e1-490a-9db3-32fd4d825cbe", name = "Octopus Powder"
                ), Ingredients(
                    id = "5f27f2ee-c12c-45dc-ad2d-b5ab7466d5a1", name = "Bicorn horn"
                ), Ingredients(
                    id = "eab464b5-de1a-447d-9154-d73900e53818", name = "Mandrake Root"
                )
            ),
            inventors = listOf(
                Inventor(
                    id = "599265f8-029a-45ea-9a66-29715f34aef0",
                    firstName = "Glover",
                    lastName = "Hipworth"
                )
            ),
            manufacturer = ""
        )
        `when`(apiRepository.getElixirsById("a22648df-4c70-4da0-9c9f-fe2c88c05df1")).thenReturn(
            mockElixirsById
        )

        viewModel.fetchElixirsById("a22648df-4c70-4da0-9c9f-fe2c88c05df1")

        val result = viewModel.elixirsByIdState.first()
        assert(result is ApiState.Success && result.data == mockElixirsById)
        verify(apiRepository).getElixirsById("a22648df-4c70-4da0-9c9f-fe2c88c05df1")
    }


}
package com.app.wizardsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.wizardsapp.data.remote.ElixirsIdModel
import com.app.wizardsapp.data.remote.WizardsByIdModel
import com.app.wizardsapp.data.remote.WizardsModel

@Dao
interface WizardsDoa {

    @Query("SELECT * FROM wizards")
    suspend fun getWizards(): Wizards

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizards(wizardsModel: Wizards)

}

@Dao
interface WizardByIdDoa {
    @Query("SELECT * FROM wizards_by_id")
    suspend fun getWizardsById(): WizardsByIdData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizardsById(wizardsById: WizardsByIdData)


}

@Dao
interface ElixirsByIdDoa {
    @Query("SELECT * FROM elixirs_by_id")
    suspend fun getElixirsById(): ElixirsByIdData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElixirsById(elixirsIdModel: ElixirsByIdData)

}
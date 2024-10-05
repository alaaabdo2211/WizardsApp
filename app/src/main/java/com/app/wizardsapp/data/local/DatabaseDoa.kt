package com.app.wizardsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WizardsDoa {

    @Query("SELECT * FROM wizards")
    suspend fun getWizards(): List<Wizards>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizards(wizardsModel: Wizards)

    @Query("SELECT * FROM elixirs_by_id WHERE id = :id")
    suspend fun getElixirsById(id: String): ElixirsByIdData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElixirsById(elixirsIdModel: ElixirsByIdData)

    @Query("SELECT * FROM wizards_by_id WHERE id = :id")
    suspend fun getWizardsById(id: String): WizardsByIdData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizardsById(wizardsById: WizardsByIdData)

}
package com.app.wizardsapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.wizardsapp.data.remote.Elixir
import com.app.wizardsapp.data.remote.Ingredients
import com.app.wizardsapp.data.remote.Inventor

@Entity(tableName = "wizards")
data class Wizards(
    @ColumnInfo(name = "elixirs") val elixirs: List<Elixir>,

    @PrimaryKey @ColumnInfo(name = "id") val id: String,

    @ColumnInfo(name = "firstName") val firstName: String,

    @ColumnInfo(name = "lastName") val lastName: String,
)

@Entity(tableName = "wizards_by_id")
data class WizardsByIdData(
    @ColumnInfo(name = "elixirs") val elixirs: List<Elixir>,

    @PrimaryKey @ColumnInfo(name = "id") val id: String,

    @ColumnInfo(name = "firstName") val firstName: String,

    @ColumnInfo(name = "lastName") val lastName: String,
)

@Entity(tableName = "elixirs_by_id")
data class ElixirsByIdData(
    @ColumnInfo(name = "characteristics") val characteristics: String,

    @PrimaryKey @ColumnInfo(name = "id") val id: String,

    @ColumnInfo(name = "difficulty") val difficulty: String,

    @ColumnInfo(name = "effect") val effect: String,
    @ColumnInfo(name = "ingredients") val ingredients: List<Ingredients>,
    @ColumnInfo(name = "inventors") val inventors: List<Inventor>,
    @ColumnInfo(name = "manufacturer") val manufacturer: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sideEffects") val sideEffects: String,
    @ColumnInfo(name = "time") val time: String,
)
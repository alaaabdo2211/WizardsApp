package com.app.wizardsapp.utils

import androidx.room.TypeConverter
import com.app.wizardsapp.data.remote.Elixir
import com.app.wizardsapp.data.remote.Ingredients
import com.app.wizardsapp.data.remote.Inventor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Convertors {

    @TypeConverter
    fun fromElixirList(value: List<Elixir>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toElixirList(value: String): List<Elixir>? {
        val listType = object : TypeToken<List<Elixir>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromIngredientsList(value: List<Ingredients>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toIngredientsList(value: String): List<Ingredients>? {
        val listType = object : TypeToken<List<Ingredients>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromInventorList(value: List<Inventor>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toInventorList(value: String): List<Inventor>? {
        val listType = object : TypeToken<List<Inventor>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
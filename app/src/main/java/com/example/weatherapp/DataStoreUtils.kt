package com.example.weatherapp

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "recent_cities")

object DataStoreUtils {
    private val RECENT_CITIES_KEY = stringSetPreferencesKey("recent_cities")

    fun getRecentCities(context: Context): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[RECENT_CITIES_KEY] ?: emptySet()
        }
    }

    suspend fun addCity(context: Context, city: String) {
        context.dataStore.edit { preferences ->
            val currentCities = preferences[RECENT_CITIES_KEY] ?: emptySet()
            val updatedCities = (currentCities + city).take(5) // Зберігати максимум 5 міст
            preferences[RECENT_CITIES_KEY] = updatedCities.toSet()
        }
    }

    suspend fun removeCity(context: Context, city: String) {
        context.dataStore.edit { preferences ->
            val currentCities = preferences[RECENT_CITIES_KEY] ?: emptySet()
            preferences[RECENT_CITIES_KEY] = currentCities - city
        }
    }
}

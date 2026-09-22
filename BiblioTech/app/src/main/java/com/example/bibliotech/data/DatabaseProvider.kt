package com.example.bibliotech.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: BibliotecaDatabase? = null

    fun getDatabase(context: Context): BibliotecaDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BibliotecaDatabase::class.java,
                "bibliotech_database"
            )
                .build()

            INSTANCE = instance
            instance
        }
    }
}
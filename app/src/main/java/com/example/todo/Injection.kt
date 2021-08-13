package com.example.todo

import android.content.Context
import com.example.todo.database.LocalDataSource
import com.example.todo.database.TodoDatabase
import com.example.todo.repository.TodoRepository

object Injection {

    fun provideRepository(context: Context): TodoRepository {
        val database = TodoDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.todoDao())

        return TodoRepository.getInstance(localDataSource)
    }
}
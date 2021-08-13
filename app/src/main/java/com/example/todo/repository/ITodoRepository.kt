package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.database.TodoEntity


interface ITodoRepository {

    fun getAllTodos(): LiveData<List<TodoEntity>>

    fun getAllCompleted(): LiveData<List<TodoEntity>>

    suspend fun insert(todoEntity: TodoEntity)

    suspend fun update(todoEntity: TodoEntity)

    suspend fun deleteSelectedTodos()

    suspend fun clearTodos()
}

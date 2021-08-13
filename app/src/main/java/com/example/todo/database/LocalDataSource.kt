package com.example.todo.database

import androidx.lifecycle.LiveData


class LocalDataSource private constructor(private val todoDao: TodoDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(todoDao:TodoDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(todoDao)
    }

    fun getAllTodos(): LiveData<List<TodoEntity>> = todoDao.loadTodos()

    fun getAllCompleted(): LiveData<List<TodoEntity>> = todoDao.loadCompleted()

    suspend fun insert(todo: TodoEntity) = todoDao.insertTodo(todo)

    suspend fun update(todo: TodoEntity) = todoDao.updateTodo(todo)

    suspend fun deleteSelectedTodos() = todoDao.deleteSelectedTodos()

    suspend fun clearTodos() = todoDao.clearTodos()
}

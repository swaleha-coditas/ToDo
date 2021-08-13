package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.database.LocalDataSource
import com.example.todo.database.TodoEntity

class TodoRepository(private val localDataSource: LocalDataSource) : ITodoRepository {

    companion object {
        @Volatile
        private var instance: TodoRepository? = null

        fun getInstance(localData: LocalDataSource): TodoRepository =
            instance ?: synchronized(this) {
                instance ?: TodoRepository(localData)
            }
    }

    override fun getAllTodos(): LiveData<List<TodoEntity>> {
        return localDataSource.getAllTodos()
    }

    override fun getAllCompleted(): LiveData<List<TodoEntity>> {
        return localDataSource.getAllCompleted()
    }

    override suspend fun insert(todoEntity: TodoEntity) {
        localDataSource.insert(todoEntity)
    }

    override suspend fun update(todoEntity: TodoEntity) {
        localDataSource.update(todoEntity)
    }

    override suspend fun deleteSelectedTodos() {
        localDataSource.deleteSelectedTodos()
    }

    override suspend fun clearTodos() {
        localDataSource.clearTodos()
    }
}
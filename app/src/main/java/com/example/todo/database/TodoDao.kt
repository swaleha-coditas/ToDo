package com.example.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoEntity")
    fun loadTodos(): LiveData<List<TodoEntity>>

    @Query("SELECT * FROM TodoEntity WHERE checked = 1")
    fun loadCompleted(): LiveData<List<TodoEntity>>

    @Insert
    suspend fun insertTodo(todo: TodoEntity)

    @Update
    suspend fun updateTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Query("DELETE FROM TodoEntity WHERE checked = 1")
    suspend fun deleteSelectedTodos()

    @Query("DELETE FROM TodoEntity")
    suspend fun clearTodos()
}

package com.example.todo.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todo.Injection
import com.example.todo.database.TodoEntity
import com.example.todo.repository.TodoRepository
import kotlinx.coroutines.launch


class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    fun getAllTodos(): LiveData<List<TodoEntity>> = repository.getAllTodos()

    fun getAllCompleted(): LiveData<List<TodoEntity>> = repository.getAllCompleted()

    fun addTodo(title: String, desc: String) {
        viewModelScope.launch {
            repository.insert(TodoEntity(0, title, desc, false))
        }
    }

    fun updateTodo(id: Int, title: String, desc: String, checked: Boolean) {
        viewModelScope.launch {
            repository.update(TodoEntity(id, title, desc, checked))
        }
    }

    fun deleteSelected() {
        viewModelScope.launch {
            repository.deleteSelectedTodos()
        }
    }

    fun clearTodos() {
        viewModelScope.launch {
            repository.clearTodos()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class TodoViewModelFactory(private val mTodoRepository: TodoRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: TodoViewModelFactory? = null

        fun getInstance(context: Context): TodoViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: TodoViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(mTodoRepository) as T
    }
}
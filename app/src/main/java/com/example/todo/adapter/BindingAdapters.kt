package com.example.todo.util

import android.graphics.Paint
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.todo.R
import com.example.todo.database.TodoEntity
import com.example.todo.viewmodel.TodoViewModel


@BindingAdapter(value = ["todo", "vm"])
fun isChecking(checkBox: CheckBox, todoEntity: TodoEntity, viewModel: TodoViewModel) {
    checkBox.setOnCheckedChangeListener(null)
    checkBox.isChecked = todoEntity.checked

    checkBox.setOnCheckedChangeListener { _, b ->
        if (b) {
            viewModel.updateTodo(
                todoEntity.id,
                todoEntity.title,
                todoEntity.description,
                true
            )
        } else {
            viewModel.updateTodo(
                todoEntity.id,
                todoEntity.title,
                todoEntity.description,
                false
            )
        }
        Log.i("checked", "checked " + todoEntity.checked)
    }
}

@BindingAdapter("android:strikeThrough")
fun isStriked(textView: TextView, isCheck: Boolean) {
    if (isCheck) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags =
            textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("android:goToEdit")
fun goToEditFragment(imageView: ImageView, todoEntity: TodoEntity) {
    imageView.setOnClickListener { view ->
        view.findNavController()
            .navigate(R.id.action_homeFragment_to_editFragment)
    }
}

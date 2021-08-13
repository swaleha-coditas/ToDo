package com.example.todo.ui.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.EditFragmentBinding
import com.example.todo.util.hideKeyboard
import com.example.todo.util.shortToast
import com.example.todo.viewmodel.TodoViewModel
import com.example.todo.viewmodel.TodoViewModelFactory

class EditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mTodo = EditFragmentArgs.fromBundle(requireArguments()).todo

        val binding = EditFragmentBinding.inflate(inflater).apply {
            todoEntity = mTodoEnity
        }

        val viewModelFactory = TodoViewModelFactory.getInstance(requireContext())
        val todoViewModel = ViewModelProvider(this, viewModelFactory)[TodoViewModel::class.java]

        binding.submitButton.setOnClickListener {
            val updatedTitle = binding.title.text.toString()
            val updatedDesc = binding.description.text.toString()

            if (updatedTitle.isNotBlank() && updatedDesc.isNotBlank()) {
                todoViewModel.updateTodo(mTodo!!.id, updatedTitle, updatedDesc, mTodo.checked)
                activity?.hideKeyboard()
                findNavController().popBackStack()
            } else {
                context?.shortToast(getString(R.string.fill_all_fields))
            }
        }

        return binding.root
    }

}
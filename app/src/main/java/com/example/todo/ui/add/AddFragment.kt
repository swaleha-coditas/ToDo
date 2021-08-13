package com.example.todo.ui.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentAddBinding
import com.example.todo.util.hideKeyboard
import com.example.todo.util.shortToast
import com.example.todo.viewmodel.TodoViewModel
import com.example.todo.viewmodel.TodoViewModelFactory

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddBinding.inflate(inflater)
        val viewModelFactory = TodoViewModelFactory.getInstance(requireContext())
        val todoViewModel = ViewModelProvider(this, viewModelFactory)[TodoViewModel::class.java]

        binding.submitButton.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()

            if (title.isNotBlank() && description.isNotBlank()) {
                todoViewModel.addTodo(title, description)
                activity?.hideKeyboard()
                findNavController().popBackStack()
            } else {
                context?.shortToast(getString(R.string.fill_all_fields))
            }
        }

        return binding.root
    }

}
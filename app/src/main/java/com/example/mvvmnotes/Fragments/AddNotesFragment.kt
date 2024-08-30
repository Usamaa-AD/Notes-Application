package com.example.mvvmnotes.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnotes.DatabaseFiles.NotesDatabase
import com.example.mvvmnotes.DatabaseFiles.NotesEntity
import com.example.mvvmnotes.DatabaseFiles.NotesRepository
import com.example.mvvmnotes.DatabaseFiles.ViewModel
import com.example.mvvmnotes.DatabaseFiles.ViewModelFactory
import com.example.mvvmnotes.HomeActivity
import com.example.mvvmnotes.databinding.FragmentAddNotesBinding
class AddNotesFragment : Fragment() {
    private lateinit var binding: FragmentAddNotesBinding
    private lateinit var viewModel : ViewModel
    private val notesDao by lazy { NotesDatabase.getDatabase(requireContext()).notesDao() }
    private val notesRepository by lazy { NotesRepository(notesDao) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNotesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(notesRepository)).get(
            ViewModel::class.java)

        binding.addNotes.setOnClickListener {
            val title = binding.title.editText?.text.toString()
            val content = binding.content.editText?.text.toString()
            if (!title.isEmpty() && !content.isEmpty()){
                viewModel.insertNote(NotesEntity(0,title,content))
                Toast.makeText(requireContext(), "Note Added", Toast.LENGTH_SHORT).show()
                binding.title.editText?.text?.clear()
                binding.content.editText?.text?.clear()
                activity?.startActivity(Intent(requireContext(),HomeActivity::class.java))
                activity?.finish()
            }else{
                Toast.makeText(requireContext(), "Please fill both fields", Toast.LENGTH_SHORT).show()
            }

        }



    return binding.root
    }

    companion object {

    }
}
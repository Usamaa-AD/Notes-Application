package com.example.mvvmnotes.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnotes.Adapter.HomeRvAdapter
import com.example.mvvmnotes.DatabaseFiles.NotesDatabase
import com.example.mvvmnotes.DatabaseFiles.NotesEntity
import com.example.mvvmnotes.DatabaseFiles.NotesRepository
import com.example.mvvmnotes.DatabaseFiles.ViewModel
import com.example.mvvmnotes.DatabaseFiles.ViewModelFactory
import com.example.mvvmnotes.databinding.FragmentHomeBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), HomeRvAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val notesDao by lazy { NotesDatabase.getDatabase(requireContext()).notesDao() }
    private val notesRepository by lazy { NotesRepository(notesDao) }
    lateinit var viewModel: ViewModel
    private lateinit var adapter: HomeRvAdapter
    private lateinit var dataList: ArrayList<NotesEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        dataList = ArrayList()
        adapter = HomeRvAdapter(requireContext(), dataList, this)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(notesRepository)
        ).get(ViewModel::class.java)
        viewModel.getAllNotes().observe(requireActivity(), Observer {
            dataList.clear()
            dataList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }

    companion object {

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun updateSpecificNote(id: Int, newTitle: String, newContent: String) {
       GlobalScope.launch {
           viewModel.updateNote(NotesEntity(id,newTitle,newContent))

       }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun deleteSpecificNote(note: NotesEntity) {
        GlobalScope.launch {
            viewModel.deleteNode(note)
        }
    }

}
package com.example.mvvmnotes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmnotes.DatabaseFiles.NotesEntity
import com.example.mvvmnotes.R
import com.example.mvvmnotes.databinding.AlertDialogDesignBinding
import com.example.mvvmnotes.databinding.SearchRvDesignBinding

class SearchRvAdapter(val context: Context,val dataList:ArrayList<NotesEntity>,val onItemClickListener: OnItemClickListener):RecyclerView.Adapter<SearchRvAdapter.ViewHolder>() {
inner class ViewHolder(val binding: SearchRvDesignBinding):RecyclerView.ViewHolder(binding.root)
interface OnItemClickListener{
    fun updateSpecificNote( id: Int,newTitle: String, newContent: String)
    fun deleteSpecificNote(note: NotesEntity)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.noteTitle.text = dataList[position].title
        holder.binding.noteDescription.text = dataList[position].content
        holder.binding.menu.setOnClickListener {
            val menu = PopupMenu(context, it)
            menu.inflate(R.menu.menu_notes)
            menu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit -> {
                        val view = AlertDialogDesignBinding.inflate(LayoutInflater.from(context))
                        val alertDialog = AlertDialog.Builder(context).setView(view.root)
                        alertDialog.setPositiveButton("Update") { DialogInterface, _ ->
                            val newTitle = view.titleOfNote.text.toString()
                            val newContent = view.contentOfNote.text.toString()
                            if (newTitle.isEmpty() || newContent.isEmpty()){
                                Toast.makeText(context, "Please fill both fields" , Toast.LENGTH_SHORT).show()
                            }else{

                                onItemClickListener.updateSpecificNote(dataList[position].id,newTitle,newContent)
                                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()
                                DialogInterface.dismiss()

                            }
                        }
                        alertDialog.setNegativeButton("Cancel") { DialogInterface, _ ->
                            DialogInterface.dismiss()
                        }
                            .create().show()
                        true

                    }


                    R.id.delete -> {
                        onItemClickListener.deleteSpecificNote(dataList[position])
                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }

            menu.show()
        }
    }
}
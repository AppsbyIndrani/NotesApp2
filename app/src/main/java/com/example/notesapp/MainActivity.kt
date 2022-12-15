package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), NoteInterface {

    lateinit var viewModel: NoteViewModel
    lateinit var binding : ActivityMainBinding
    //lateinit var rvList:RecyclerView
   // lateinit var clNote:ConstraintLayout
   //  var txtNote:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotes.layoutManager=LinearLayoutManager(this)

        val adapter=NotesAdapter(this,this)
        binding.rvNotes.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })



    }

    override fun onItemClicked(note: Note)
    {
       viewModel.deleteNode(note)
        val snackbar=Snackbar.make(binding.clNote,"Deleted ${note.text}",Snackbar.LENGTH_LONG)
        snackbar.show()

    }

    fun submitData(view: android.view.View) {
        val notetext=binding.edText.text.toString()
        if (notetext.isNotEmpty())
        {
            viewModel.insertNode(Note(notetext))
            val snackbar=Snackbar.make(binding.clNote,"${notetext} inserted",Snackbar.LENGTH_LONG)
            snackbar.show()
           binding.edText.text.clear()
        }
    }

}
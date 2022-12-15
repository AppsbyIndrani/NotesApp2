package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(var context: Context, var listener: NoteInterface) : RecyclerView.Adapter<NotesAdapter.MyViewHolder>()
{
    val allNotes=ArrayList<Note>()

    inner class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val txtNote=itemView.findViewById<TextView>(R.id.txtNotes)
        val imgDelete=itemView.findViewById<ImageView>(R.id.imgDelete)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val viewHolder=MyViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_notes,parent,false))
        viewHolder.imgDelete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val currentNote=allNotes[position]
        holder.txtNote.text=currentNote.text

    }

    fun updateList(newlist : List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newlist)

        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

}

interface NoteInterface{
    fun onItemClicked(note: Note)
}
package com.example.applicationtodo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtodo.model.User
import com.example.applicationtodo.R

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val idTxt: TextView = itemView.findViewById(R.id.id_txt)
        val titleTxt: TextView = itemView.findViewById(R.id.title_txt)
        val descriptionTxt: TextView = itemView.findViewById(R.id.description_txt)
        val dueDateTxt: TextView = itemView.findViewById(R.id.dueDate_txt)
        val categoryTxt: TextView = itemView.findViewById(R.id.category_txt)
        val rowLayout: ConstraintLayout = itemView.findViewById(R.id.rowLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.idTxt.text = currentItem.id.toString()
        holder.titleTxt.text = currentItem.title
        holder.descriptionTxt.text = currentItem.description
        holder.dueDateTxt.text = "Due Date: ${currentItem.dueDate}"
        holder.categoryTxt.text = currentItem.category


    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}

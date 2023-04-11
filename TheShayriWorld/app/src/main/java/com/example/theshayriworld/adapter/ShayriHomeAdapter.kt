package com.example.theshayriworld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theshayriworld.R
import com.example.theshayriworld.modelclass.ShayriModelclass

class ShayriHomeAdapter(
    var shayriList: ArrayList<ShayriModelclass>,
    var invoke:((ShayriModelclass)->Unit)) : RecyclerView.Adapter<ShayriHomeAdapter.ViewHolder>() {
    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {
        var txtName: TextView = v.findViewById(R.id.txtName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var View=LayoutInflater.from(parent.context).inflate(R.layout.iteam_allshayri,parent,false)
        var v=ViewHolder(View)
        return v
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = shayriList[position].categoryName
        holder.txtName.setOnClickListener{
            invoke.invoke(shayriList[position])
        }

    }

    override fun getItemCount(): Int {
        return shayriList.size
    }
}
package com.example.theshayriworld.adapter

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theshayriworld.R
import com.example.theshayriworld.modelclass.CategoryDisplayModelClass

class CategoryAdepter(var click:((CategoryDisplayModelClass)->Unit),var invokeTwo:((Int,Int)->Unit)) : RecyclerView.Adapter<CategoryAdepter.MyViewholder>() {

    var categoryListList= ArrayList<CategoryDisplayModelClass>()
    class MyViewholder(v:View):RecyclerView.ViewHolder(v) {
        var txtCategory: TextView = v.findViewById(R.id.txtCategory)
        var imggFav:ImageView=v.findViewById(R.id.imggFav)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        var View=LayoutInflater.from(parent.context).inflate(R.layout.iteam_category,parent,false)
         var v = MyViewholder(View)
        return v
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.txtCategory.text=categoryListList[position].shayri
        Log.e("TAG", "onBindViewHolder: "+"shayri")

        if(categoryListList[position].fav==1)
        {
            holder.imggFav.setImageResource(R.drawable.likeeeeeeee)

        }
        else{
            holder.imggFav.setImageResource(R.drawable.llike)

        }
        holder.imggFav.setOnClickListener {
           if(categoryListList[position].fav==1)
           {
               invokeTwo.invoke(0,categoryListList[position].s_id)
               holder.imggFav.setImageResource(R.drawable.llike)
               categoryListList[position].fav=0

           }
            else{
               invokeTwo.invoke(1,categoryListList[position].s_id)
               holder.imggFav.setImageResource(R.drawable.likeeeeeeee)
               categoryListList[position].fav=1
           }
        }
        holder.txtCategory.setOnClickListener {
            click.invoke(categoryListList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryListList.size
    }

    fun updateFuction(categoryListList: ArrayList<CategoryDisplayModelClass>) {
this.categoryListList=ArrayList()
        this.categoryListList.addAll(categoryListList)
        notifyDataSetChanged()
    }
}
package com.example.theshayriworld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theshayriworld.R
import com.example.theshayriworld.modelclass.ShayriFavoriteModelclass

class ShayriFavoriteAdapter(
    var favriteList: ArrayList<ShayriFavoriteModelclass>, var invo: ((Int, Int) -> Unit)
) : RecyclerView.Adapter<ShayriFavoriteAdapter.MyViewholder>() {

    class MyViewholder(v: View) : RecyclerView.ViewHolder(v) {
        var txtSshowShayri: TextView = v.findViewById(R.id.txtSshowShayri)
        var imggFavoriteee: ImageView = v.findViewById(R.id.imggFavoriteee)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        var View =
            LayoutInflater.from(parent.context).inflate(R.layout.iteam_favorite, parent, false)
        var v = MyViewholder(View)
        return v
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.txtSshowShayri.text = favriteList[position].shayri
        holder.txtSshowShayri.setOnClickListener {
            invo.invoke(favriteList[position].s_id, favriteList[position].fav)
        }


            holder.imggFavoriteee.setImageResource(R.drawable.likeeeeeeee)


        holder.imggFavoriteee.setOnClickListener {
            if(favriteList[position].fav==1)
            {
                invo.invoke(0,favriteList[position].s_id)
                favriteList[position].fav=0
            }
            else{
                invo.invoke(1,favriteList[position].s_id)
                favriteList[position].fav=1
            }
            deleteFav(position)
        }
    }


    override fun getItemCount(): Int {

        return favriteList.size

    }

    fun updatedList(favriteList: ArrayList<ShayriFavoriteModelclass>)
    {
        this.favriteList=favriteList
        notifyDataSetChanged()
    }
    fun deleteFav(position: Int)
    {
        favriteList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position,favriteList.size)
    }
}
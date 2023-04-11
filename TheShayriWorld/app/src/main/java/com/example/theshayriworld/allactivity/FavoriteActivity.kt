package com.example.theshayriworld.allactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theshayriworld.adapter.ShayriFavoriteAdapter
import com.example.theshayriworld.databaseclass.MyDatabase
import com.example.theshayriworld.databinding.ActivityFavoriteBinding
import com.example.theshayriworld.modelclass.ShayriFavoriteModelclass

class FavoriteActivity : AppCompatActivity() {

    lateinit var binding:ActivityFavoriteBinding
   lateinit var db:MyDatabase
   lateinit var adapter:ShayriFavoriteAdapter
    var favriteList=ArrayList<ShayriFavoriteModelclass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

            db=MyDatabase(this)

        intView()
    }

    private fun intView() {
        favriteList=db.DisplayFavouriteShayri()

         adapter=ShayriFavoriteAdapter(favriteList) { s_id, fav ->
            db.favAdd(s_id, fav)
             adapter.updatedList(favriteList)
        }
        binding.rcvFavorite.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvFavorite.adapter = adapter


        binding.imgBackArrowShayri.setOnClickListener {
           onBackPressed()
        }
    }
}
package com.example.theshayriworld.allactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theshayriworld.adapter.ShayriHomeAdapter
import com.example.theshayriworld.databaseclass.MyDatabase
import com.example.theshayriworld.databinding.ActivityShayromePageBinding
import com.example.theshayriworld.modelclass.ShayriModelclass

class ShayromePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityShayromePageBinding
  lateinit  var db:MyDatabase
    var shayriList = ArrayList<ShayriModelclass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShayromePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

         db = MyDatabase(this)
        intView()
    }

    private fun intView() {
shayriList=db.readData()

        var adapter = ShayriHomeAdapter(shayriList) {
            val intent = Intent(this, AllCategoryActivity::class.java)
            intent.putExtra("title",it.categoryName)
            intent.putExtra("id",it.c_id)
            Log.e("IDDDDDDD", "intView: "+it.c_id)
startActivity(intent)
        }
        binding.rcvAllShayri.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvAllShayri.adapter = adapter


        binding.imgLiked.setOnClickListener {
            var i=Intent(this,FavoriteActivity::class.java)
            startActivity(i)
        }
    }


}
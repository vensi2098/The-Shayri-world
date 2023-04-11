package com.example.theshayriworld.allactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theshayriworld.adapter.CategoryAdepter
import com.example.theshayriworld.databaseclass.MyDatabase
import com.example.theshayriworld.databinding.ActivityAllCategoryBinding
import com.example.theshayriworld.modelclass.CategoryDisplayModelClass

class AllCategoryActivity : AppCompatActivity() {
    lateinit var binding:ActivityAllCategoryBinding
lateinit var adapter:CategoryAdepter
    lateinit  var db: MyDatabase
    var getId=0
    var categoryListList = ArrayList<CategoryDisplayModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAllCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabase(this)
        Intviewww()
    }

    private fun Intviewww() {

        var titleName:String?=intent.getStringExtra("title")
        binding.txtAllCategory.text=titleName
       getId=intent.getIntExtra("id",0)

        Log.e("TAG", "Intviewww: "+getId)


       adapter = CategoryAdepter( {
             var i=Intent(this,ShayriDisplayActivity::class.java)
            i.putExtra("shayri iteam",it.shayri)
            startActivity(i)
            finish()
        },{fav,s_id->
            db.favAdd(fav,s_id)

        })
        binding.rcvAllCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvAllCategory.adapter = adapter

binding.imgBackArrowCateg.setOnClickListener {
    var i=Intent(this,ShayromePageActivity::class.java)
    startActivity(i)
}
        binding.imgLike.setOnClickListener {
            var i=Intent(this@AllCategoryActivity,FavoriteActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        categoryListList=db.displayShayri(getId)
        adapter.
        updateFuction(categoryListList)
    }
}
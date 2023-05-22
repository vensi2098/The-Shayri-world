package com.example.theshayriworld.allactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theshayriworld.adapter.ShayriHomeAdapter
import com.example.theshayriworld.databaseclass.MyDatabase
import com.example.theshayriworld.databinding.ActivityShayromePageBinding
import com.example.theshayriworld.modelclass.ShayriModelclass

class ShayromePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityShayromePageBinding
    lateinit var db: MyDatabase
    var shayriList = ArrayList<ShayriModelclass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShayromePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabase(this)
        intView()
    }

    private fun intView() {
        shayriList = db.readData()

        var adapter = ShayriHomeAdapter(shayriList) {
            val intent = Intent(this, AllCategoryActivity::class.java)
            intent.putExtra("title", it.categoryName)
            intent.putExtra("id", it.c_id)
            Log.e("IDDDDDDD", "intView: " + it.c_id)
            startActivity(intent)
        }
        binding.rcvAllShayri.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvAllShayri.adapter = adapter


        binding.imgLiked.setOnClickListener {
            var i = Intent(this, FavoriteActivity::class.java)
            startActivity(i)
        }
        binding.imgMenu.setOnClickListener {
            binding.drawerLyt.openDrawer(GravityCompat.START)
        }
        binding.lytHome.setOnClickListener {
            binding.drawerLyt.closeDrawer(GravityCompat.START)
            Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
        }
        binding.lytFavourite.setOnClickListener {
            binding.drawerLyt.closeDrawer(GravityCompat.START)
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        binding.lytPrivacyPolicy.setOnClickListener {
            val url = "https://vensibaldha.blogspot.com/2023/04/privacy-policy.html"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)

        }
        binding.lytShare.setOnClickListener {
            binding.drawerLyt.closeDrawer(GravityCompat.START)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing url")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=com.dipakmsg.englishsadmsg"
            )
            startActivity(intent)
        }
        binding.lytQuit.setOnClickListener {
            binding.drawerLyt.closeDrawer(GravityCompat.START)

            System.exit(0)
        }
    }
    var isBackPressedOnce=false
    override fun onBackPressed() {

        if (isBackPressedOnce) {
            super.onBackPressed()
            return
        }
        Toast.makeText(this, "press back button twice", Toast.LENGTH_SHORT).show()
        isBackPressedOnce=true

        Handler().postDelayed({
            isBackPressedOnce=false
        },1500)
    }

}
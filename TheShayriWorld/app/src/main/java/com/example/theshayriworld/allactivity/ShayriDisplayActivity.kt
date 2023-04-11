package com.example.theshayriworld.allactivity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.theshayriworld.databinding.ActivityShayriDisplayBinding
import com.example.theshayriworld.modelclass.CategoryDisplayModelClass

class ShayriDisplayActivity : AppCompatActivity() {
    var categoryListList = ArrayList<CategoryDisplayModelClass>()
    lateinit var  binding:ActivityShayriDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityShayriDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intView()

    }

    private fun intView() {

        binding.imgBackArrowShayri.setOnClickListener {
           onBackPressed()
        }
        var shayriName:String?=intent.getStringExtra("shayri iteam")
        binding.txtShayri.text=shayriName

        val gallery = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val uri = data!!.data
                binding.imfShow.setImageURI(uri)
            }
        }

            binding.imgAdd.setOnClickListener { v ->
                val galleryy =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                //            startActivityForResult(gallery, 100);
                gallery.launch(galleryy)
            }

      binding.imgShareShayri.setOnClickListener {
          var i= Intent(Intent.ACTION_SEND)
          i.setType("text/plain")
          i.putExtra(Intent.EXTRA_TEXT, shayriName)
          startActivity(i)
      }
        }



}

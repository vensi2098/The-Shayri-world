package com.example.theshayriworld.modelclass

data class ShayriModelclass(var c_id: Int, var categoryName: String)

data class CategoryDisplayModelClass(
    val s_id: Int,
    var shayri: String,
    var c_id: Int,
    var fav: Int
)

data class ShayriFavoriteModelclass(
    val s_id: Int,
    var shayri: String, var fav: Int
)


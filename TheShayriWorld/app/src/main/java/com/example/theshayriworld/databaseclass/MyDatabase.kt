package com.example.theshayriworld.databaseclass

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import com.example.theshayriworld.modelclass.CategoryDisplayModelClass
import com.example.theshayriworld.modelclass.ShayriFavoriteModelclass
import com.example.theshayriworld.modelclass.ShayriModelclass
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MyDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private val mDataBase: SQLiteDatabase? = null
    private var mNeedUpdate = false
    private val mContext: Context

    init {
        if (Build.VERSION.SDK_INT >= 17) DB_PATH =
            context.applicationInfo.dataDir + "/databases/" else DB_PATH =
            "/data/data/" + context.packageName + "/databases/"
        mContext = context
        copyDataBase()
        this.readableDatabase
    }

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            try {
                copyDBFile()
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(DB_PATH + DB_NAME)
        return dbFile.exists()
    }

    //    TODO copy file
    @Throws(IOException::class)
    private fun copyDBFile() {
        val mInput = mContext.assets.open(DB_NAME)
        val mOutput: OutputStream = FileOutputStream(DB_PATH + DB_NAME)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) mNeedUpdate = true
    }

    //    TODO update database
    @Throws(IOException::class)
    fun updateDataBase() {
        if (mNeedUpdate) {
            val dbFile = File(DB_PATH + DB_NAME)
            if (dbFile.exists()) dbFile.delete()
            copyDataBase()
            mNeedUpdate = false
        }
    }

    @Synchronized
    override fun close() {
        mDataBase?.close()
        super.close()
    }

    fun readData(): ArrayList<ShayriModelclass> {
        var shayriList = ArrayList<ShayriModelclass>()
        val db = readableDatabase
        val sql = "select * from categoryTb"
        var c = db.rawQuery(sql, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val id = c.getInt(0)
                val name = c.getString(1)

                Log.e(TAG, "readData:==> $id   $name ")

                var model = ShayriModelclass(id, name)
                shayriList.add(model)

            } while (c.moveToNext())
        }
        return shayriList
    }

    companion object {
        private const val TAG = "MyDatabase"
        private const val DB_NAME = "quotess.db"
        private var DB_PATH = ""
        private const val DB_VERSION = 1
    }

    fun displayShayri(getId: Int): ArrayList<CategoryDisplayModelClass> {
        Log.e("TAG", "displayShayri: "+getId )
        var categoryListList = ArrayList<CategoryDisplayModelClass>()
        val db = readableDatabase
        val sql = "select * from quotesTB where quotes_category_id ='$getId'"
        var c = db.rawQuery(sql, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val id = c.getInt(0)
                val name = c.getString(1)
                val c_id = c.getInt(2)
                val fav = c.getInt(3)

                Log.e(TAG, "readData:==> $id   $name $c_id")

                var model1 = CategoryDisplayModelClass(id, name, c_id, fav)
                categoryListList.add(model1)

            } while (c.moveToNext())
        }
        return categoryListList
    }

    fun favAdd(fav: Int, s_id: Int) {
        val update = writableDatabase
        val updateSql = "update quotesTB set favorite='$fav' where quotes_id ='$s_id' "
        update.execSQL(updateSql)
    }

    fun DisplayFavouriteShayri(): ArrayList<ShayriFavoriteModelclass> {
        var favriteList=ArrayList<ShayriFavoriteModelclass>()
        val db = readableDatabase
        val sql = "select * from quotesTB where favorite=1"
        val c: Cursor =db.rawQuery(sql,null)
        if(c.moveToFirst())
        {
            do{
                val id=c.getInt(0)
                val name=c.getString(1)
                val fav=c.getInt(2)
                var model= ShayriFavoriteModelclass(id,name,fav)

                favriteList.add(model)

            }while (c.moveToNext())
        }

        return favriteList

    }
}

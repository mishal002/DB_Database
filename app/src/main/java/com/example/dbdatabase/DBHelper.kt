package com.example.exdatabase

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.security.AccessControlContext

class DBHelper(val context: Context?) : SQLiteOpenHelper(context,"mydb.db",null,1) {

    var DBName = "mydb.db"
    var path : String? = null

    init {
        path = if (Build.VERSION.SDK_INT >= 17) {
            context!!.applicationInfo.dataDir + "/databases/"
        } else {
            "/data/data/" + context!!.packageName + "/databases/"
        }
        copyDataBase()
        this.readableDatabase
    }

    override fun onCreate(p0: SQLiteDatabase?) {}

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    private fun checkDataBase(): Boolean {
        val dbFile = File(path + DBName)
        return dbFile.exists()
    }

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            copyDBFile()
        }
    }

    private fun copyDBFile() {
        val mInput = context!!.assets.open(DBName)
        val mOutput: OutputStream = FileOutputStream(path + DBName)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    @SuppressLint("Range")
    fun readData()
    {
        var db = readableDatabase
        var query = "SELECT * FROM customer"

        var cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst())
        {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var name = cursor.getString(cursor.getColumnIndex("name"))
                var mobile = cursor.getString(cursor.getColumnIndex("mobile"))

                Log.e("TAG", "readdata: $id $name $mobile")

            }while (cursor.moveToNext())
        }


    }
}
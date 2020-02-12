package com.example.obviousassignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.obviousassignment.R
import com.google.gson.Gson
import com.example.obviousassignment.model.ImageListModel
import com.google.gson.reflect.TypeToken
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.emedinaa.kotlinmvvm.view.ImageListAdapter
import com.example.obviousassignment.Util


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val json=  Util.readJSONFromAsset(this)

        if (!Util.isOnline(this))
        {
            Toast.makeText(this,"You are offline",Toast.LENGTH_LONG).show()
            return
        }

        setintoModel(json)

    }

    private fun setintoModel(json: String?) {


        val imagelist: List<ImageListModel>
        val listType = object : TypeToken<List<ImageListModel>>() {

        }.type
        imagelist = Gson().fromJson(json, listType)
        Log.v("dataa",""+imagelist)

        setDataAdapter(imagelist)
    }

    private fun setDataAdapter(imagelist: List<ImageListModel>?)
    {

        val recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        val numberOfColumns = 2
        recyclerView.setLayoutManager(GridLayoutManager(this, 2))
        val adapter = imagelist?.let { ImageListAdapter(it,this,itemClick) }
        recyclerView.adapter =  adapter;

    }

    val itemClick = View.OnClickListener {



        val poistion :Int= it.tag as Int



        val intent =  Intent(this,NormalActivity::class.java)
        intent.putExtra("Current_position",""+poistion)
        startActivity(intent)



    }


}

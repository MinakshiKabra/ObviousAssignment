package com.example.obviousassignment.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.obviousassignment.R
import com.example.obviousassignment.model.ImageListModel
import kotlinx.android.synthetic.main.activty_details.*

class ImageDetailActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setData()

    }

    private fun setData() {
        val imagelistModel: ImageListModel = intent.getSerializableExtra("data") as ImageListModel
        tv_title.setText(imagelistModel.title)
        tv_date.setText(imagelistModel.date)
        tv_copyright.setText(imagelistModel.copyright)
        tv_detail.setText(imagelistModel.explanation)
        supportActionBar!!.setTitle(imagelistModel.title)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {

            android.R.id.home -> {

                this.finish()
                return true
            }
        }
        return false
    };


}
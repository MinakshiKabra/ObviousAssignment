package com.emedinaa.kotlinmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.obviousassignment.R
import com.example.obviousassignment.model.ImageListModel
import com.squareup.picasso.Picasso


class ImageListAdapter(
    private var imagelist: List<ImageListModel>,
    private var context: Context,
    var itemClick: View.OnClickListener
):RecyclerView.Adapter<ImageListAdapter.MViewHolder>(){


    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_gallery, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        val imageData= imagelist[position]



        Picasso.with(context).load(imageData.url).error(R.mipmap.ic_launcher).into(vh.imageView)

        vh.imageView.setTag(position)
        vh.imageView.setOnClickListener(itemClick)





    }



    override fun getItemCount(): Int {
        return imagelist.size
    }


    class MViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val imageView: ImageView = view.findViewById(R.id.image)

    }
}
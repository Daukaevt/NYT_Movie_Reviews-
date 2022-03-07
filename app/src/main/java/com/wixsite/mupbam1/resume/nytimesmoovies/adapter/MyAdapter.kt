package com.wixsite.mupbam1.resume.nytimesmoovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wixsite.mupbam1.resume.nytimesmoovies.R


class MyAdapter(val cntxt: Context, private val tags: ArrayList<String>) : RecyclerView
                    .Adapter<MyAdapter.MyViewHolder>() {

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val poster: ImageView = itemView.findViewById<View>(R.id.ivPoster) as ImageView
            val title: TextView = itemView.findViewById<View>(R.id.tvTitle) as TextView
            val content: TextView = itemView.findViewById<View>(R.id.tvContent) as TextView

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            var context=parent.context
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val radius = 50

            val margin = 20

                var i=position*3
                val tag = tags[i]
                Glide.with(cntxt)
                    .load(tag)
                    .centerCrop()
                    .circleCrop()
                    .into(holder.poster)
                i++
                holder.title.text=tags[i]
                i++
                holder.content.text=tags[i]


            holder.poster.setOnClickListener {
                Toast.makeText(cntxt, "blabla", Toast.LENGTH_SHORT).show()
               // onClickListener.onClicked(tag)
            }
        }

        override fun getItemCount(): Int {
            return tags.size/3
        }

    }
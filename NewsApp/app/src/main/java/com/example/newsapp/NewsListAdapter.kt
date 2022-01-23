package com.example.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListAdapter(private val listener:NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
  private var  items : ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return  viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.imgUrl).into(holder.image)
        holder.author.text = currentItem.author
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(updatedItems : ArrayList<News>){
        items.clear()
        items.addAll(updatedItems)

        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView : TextView = itemView.title
    val image: ImageView = itemView.image
    val author: TextView = itemView.author

}

interface NewsItemClicked {
    fun onItemClicked(item:News)
}
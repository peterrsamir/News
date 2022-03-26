package com.example.news.ui.Favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import de.hdodenhof.circleimageview.CircleImageView

class FavoriteAdapter(
    var context: Context,
    var articles: List<CachedArticles>,
) : RecyclerView.Adapter<FavoriteAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(R.id.txtVNewsFav)
        var img: ImageView = itemView.findViewById(R.id.imgVNewsFav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_fav_row, null, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv.setText(articles.get(position).title)
        holder.itemView.setOnClickListener {
//            onHomeRvClickListener.onItemClick(articles.get(position))
//            onItemclick(articles.get(position))
        }
        Glide.with(context).load(articles.get(position).urlToImage).into(holder.img)
        if (holder.img.drawable == null)
            holder.img.setImageResource(R.drawable.ic_news)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setList(articles: List<CachedArticles>) {
        this.articles = articles
        notifyDataSetChanged()
    }

}
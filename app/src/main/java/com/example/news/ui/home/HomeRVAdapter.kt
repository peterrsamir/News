package com.example.news.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Articles
import com.example.news.R
import de.hdodenhof.circleimageview.CircleImageView

class HomeRVAdapter(
    var context: Context,
    var articles: ArrayList<Articles>,
    var onItemclick: (Articles)-> Unit,var onFavoClick: (Articles)-> Unit
) : RecyclerView.Adapter<HomeRVAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(R.id.custom_home_row_tv)
        var img: CircleImageView = itemView.findViewById(R.id.custom_home_row_img)
        var favoImg: ImageView = itemView.findViewById(R.id.custom_home_row_img_favo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_home_row, null, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv.setText(articles.get(position).title)
        holder.favoImg.setOnClickListener {
            onFavoClick(articles.get(position))
//            onHomeRvClickListener.onFavoriteClick(it, articles.get(position))
            holder.favoImg.setImageResource(R.drawable.favo_icon2)
        }
        holder.itemView.setOnClickListener {
//            onHomeRvClickListener.onItemClick(articles.get(position))
            onItemclick(articles.get(position))

        }
        Glide.with(context).load(articles.get(position).urlToImage).into(holder.img)
        if( holder.img.drawable==null)
            holder.img.setImageResource(R.drawable.ic_news)

    }
    override fun getItemCount(): Int {
        return articles.size
    }

    fun setList(articles: ArrayList<Articles>) {
        this.articles = articles
        notifyDataSetChanged()
    }

}
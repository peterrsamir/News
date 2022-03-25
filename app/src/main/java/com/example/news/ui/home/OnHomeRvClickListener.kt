package com.example.news.ui.home

import android.view.View
import com.example.example.Articles
import com.example.example.News

interface OnHomeRvClickListener {
    fun onClick(v:View,articles: Articles)
}
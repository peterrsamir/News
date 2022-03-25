package com.example.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Articles
import com.example.example.News
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.repository.Repo

class HomeFragment : Fragment(),OnHomeRvClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var repo= Repo()
        var factory=HomeViewModelFactory(repo)
        val homeViewModel =
            ViewModelProvider(this,factory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var articles:ArrayList<Articles> = ArrayList()
        var adapter= HomeRVAdapter(requireContext(),articles,this)
        binding.homeRv.setHasFixedSize(true)
        binding.homeRv.layoutManager=LinearLayoutManager(requireContext())
        binding.homeRv.adapter=adapter

        homeViewModel.getData()

        homeViewModel.newsLiveData.observe(requireActivity(), Observer {
            adapter.setList(it.articles)
        })




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View,articles: Articles) {
        showPopupMenu(v,articles)
    }
    fun showPopupMenu(v: View, articles: Articles ) {
        val popup = PopupMenu(v.context, v)
        popup.getMenuInflater().inflate(R.menu.add_to_favorite, popup.getMenu())
        popup.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(p0: MenuItem?): Boolean {
                Toast.makeText(
                    context, "Saved", Toast.LENGTH_SHORT
                ).show()
                return true
            }
        })
        popup.show()
    }
}
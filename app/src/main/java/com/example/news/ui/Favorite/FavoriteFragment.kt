package com.example.news.ui.Favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.database.DataBaseBuilder
import com.example.news.databinding.FragmentFavoriteBinding
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import com.example.news.network.NewsApi
import com.example.news.repository.Repo
import com.example.news.ui.home.HomeRVAdapter
import com.example.news.ui.home.HomeViewModel
import com.example.news.ui.home.HomeViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    lateinit var favArticles: List<CachedArticles>
    var list = emptyList<CachedArticles>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dao = DataBaseBuilder.getInstance(requireContext()).getDao()
        val retro = NewsApi.getInstance()
        val repo = Repo(retro, dao)
        val factory = FavoriteViewModelFactory(repo)
        val favoriteViewModel =
            ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favArticles = ArrayList()
        var adapter = FavoriteAdapter(requireContext(), favArticles)
        binding.favRecycler.setHasFixedSize(true)
        binding.favRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favRecycler.adapter = adapter
        favoriteViewModel.getAllFavorites()
        favoriteViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                adapter.setList(it)
                Log.i("TAG", "onCreateView: "+it.get(0).url)
                adapter.notifyDataSetChanged()
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
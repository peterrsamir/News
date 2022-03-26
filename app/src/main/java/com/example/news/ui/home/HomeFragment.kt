package com.example.news.ui.home

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Articles
import com.example.example.News
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.repository.Repo

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var articles:ArrayList<Articles>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var navController:NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        var repo= Repo()
        var factory=HomeViewModelFactory(repo)
        val homeViewModel =
            ViewModelProvider(this,factory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

         articles= ArrayList()
        var adapter= HomeRVAdapter(requireContext(),articles,::onItemClick, ::onFavoriteClick)
        binding.homeRv.setHasFixedSize(true)
        binding.homeRv.layoutManager=LinearLayoutManager(requireContext())
        binding.homeRv.adapter=adapter

        homeViewModel.getData()

        homeViewModel.newsLiveData.observe(requireActivity(), Observer {
            adapter.setList(it.articles)
            articles=it.articles
        })




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController=Navigation.findNavController(view)
    }

     fun onFavoriteClick(articles: Articles) {
    }

     fun onItemClick(articles: Articles) {
     var action=HomeFragmentDirections.actionNavigationHomeToDetailsFragment(articles.url)
        navController.navigate(action)
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.search_menu,menu)
        var searchView: SearchView =menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.isSubmitButtonEnabled=true
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Toast.makeText(requireActivity(), ""+p0, Toast.LENGTH_SHORT).show()
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
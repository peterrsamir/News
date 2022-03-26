package com.example.news.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.database.LocalDatabase
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.model.CachedArticles
import com.example.news.network.NewsApi
import com.example.news.network.NewsState
import com.example.news.repository.Repo

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var articles: List<CachedArticles>
    lateinit var searchList: List<CachedArticles>
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    lateinit var homeViewModel: HomeViewModel
    lateinit var adapter: HomeRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        var repo =
            Repo(NewsApi.getInstance(), LocalDatabase.getInstance(requireContext()).newsDao!!)
        var factory = HomeViewModelFactory(repo)

        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        articles = ArrayList()
        searchList = ArrayList()

        adapter = HomeRVAdapter(requireContext(), articles, ::onItemClick, ::onFavoriteClick)
        binding.homeRv.setHasFixedSize(true)
        binding.homeRv.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRv.adapter = adapter

        homeViewModel.getData()
        homeViewModel.startListening()

        homeViewModel.allNewsLiveData.observe(requireActivity(), Observer {
            adapter.setList(it)
        })

        homeViewModel.newsLiveData.observe(requireActivity(), Observer {
            when (it) {
                is NewsState.isLoading -> {
                    binding.homeRv.visibility = View.GONE
                    binding.homeTvFail.visibility=View.GONE
                }
                is NewsState.success -> {
                    binding.homeProgress.visibility = View.GONE
                    binding.homeTvFail.visibility=View.GONE
                    binding.homeRv.visibility = View.VISIBLE
                    articles = it.news.articles
                    homeViewModel.insertNews(it.news.articles)
                }
                is NewsState.fail->{
                    binding.homeProgress.visibility = View.GONE
                    binding.homeRv.visibility = View.GONE
                    binding.homeTvFail.visibility=View.VISIBLE
                }
            }
        })

        homeViewModel.searchLiveData.observe(requireActivity(), Observer {
            searchList = it
        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    fun onFavoriteClick(articles: CachedArticles) {
        if (articles.isFavorite == 0)
            articles.isFavorite = 1
        else
            articles.isFavorite = 0

        homeViewModel.insertFavorite(articles)
        homeViewModel.allNewsLiveData.removeObservers(requireActivity())

    }

    fun onItemClick(articles: CachedArticles) {
        var action = HomeFragmentDirections.actionNavigationHomeToDetailsFragment(articles.url)
        navController.navigate(action)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.search_menu, menu)
        var searchView: SearchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                homeViewModel.search(p0!!)
                adapter.setList(searchList)
                if (p0.equals(""))
                    adapter.setList(articles)
                return true
            }
        })
        searchView.setOnCloseListener(SearchView.OnCloseListener {
            adapter.setList(articles)
            false
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
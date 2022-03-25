package com.example.news.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.database.LocalDatabase
import com.example.news.databinding.FragmentDashboardBinding
import com.example.news.model.CachedArticles
import com.example.news.model.Source
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var articles:CachedArticles
    lateinit var localDatabase: LocalDatabase
    var articlesList =  mutableListOf<CachedArticles>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        articles= CachedArticles(
            Source("", ""), "peter", "title", "sdsdf",
            "da", "sd", "time", "ds")

        articlesList.add(articles)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        localDatabase = LocalDatabase.getInstance(requireContext())
//        articlesList.add(articles)
        binding.button.setOnClickListener(View.OnClickListener {
            GlobalScope.launch {
                localDatabase.insertNews(articlesList)
            }
        })
    }
}
package com.example.news.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.news.R

class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()

    companion object {
        fun newInstance() = DetailsFragment()
    }


    private lateinit var viewModel: DetailsViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        navController=Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        var url=args.url
        if (url!=null)
            Toast.makeText(requireContext(), "not null"+url, Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(requireContext(), "null"+url, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.navigate(R.id.navigation_home)
        return super.onOptionsItemSelected(item)
    }

}



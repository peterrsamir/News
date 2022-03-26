package com.example.news.ui.registration.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.news.R
import com.example.news.ui.registration.viewmodel.RegistrationViewModel
import com.example.news.ui.registration.viewmodel.RegistrationViewModelFactory
import com.example.news.utils.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class RegistrationFragment : Fragment() {

    // xml parameters
    lateinit var et_name: EditText
    lateinit var et_password: EditText
    lateinit var et_email: EditText
    lateinit var et_phone: EditText
    lateinit var progress_bar: ProgressBar
    lateinit var btn_register: Button
    lateinit var tv_navigate_to_login: TextView
    lateinit var nav_bar: BottomNavigationView

    //user data
    lateinit var name: String
    lateinit var phone: String
    lateinit var email: String
    lateinit var password: String
    var validateDataFlag: Boolean = false
    var emptyDataFlag: Boolean = true

    //view model parameters
    lateinit var viewModel: RegistrationViewModel
    lateinit var viewModelFactory: RegistrationViewModelFactory

    //shared
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_registration, container, false)
        setupViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSharedPreferences()
        createUI(view)
        btn_register.setOnClickListener {
            register()
        }
        tv_navigate_to_login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideNavBar()
    }

    private fun setupViewModel() {
        viewModelFactory =
            RegistrationViewModelFactory((requireActivity().application!! as MyApplication).usersRepository)
        viewModel =
           ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)
    }

    private fun setupSharedPreferences(){
        sharedPreferences = requireActivity().getSharedPreferences(Constants.sharedName, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    private fun createUI(view: View) {
        et_email = view.findViewById(R.id.et_email)
        et_name = view.findViewById(R.id.ed_name)
        et_password = view.findViewById(R.id.et_password)
        et_phone = view.findViewById(R.id.et_phone)
        progress_bar = view.findViewById(R.id.progress_bar)
        btn_register = view.findViewById(R.id.btn_register)
        tv_navigate_to_login = view.findViewById(R.id.tv_navigate_to_login)
    }

    private fun storeData() {
        name = et_name.text.toString().trim()
        email = et_email.text.toString().trim()
        phone = et_phone.text.toString().trim()
        password = et_password.text.toString().trim()
    }

    private fun register() {
        progress_bar.visibility = View.VISIBLE
        storeData()
        if (!isDataEmpty()) {
            if (isDataValid()) {
                var result = viewModel.register(name, phone, email, password)
                if(result != null){
                    editor.putBoolean(Constants.loginFlag, true)
                    editor.commit()
                    progress_bar.visibility = View.GONE
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.navigation_home)

                } else{
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireActivity(),"you already have an account", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun isDataValid(): Boolean {
        if (!validateEmail(email)) {
            showError(getString(R.string.invalidEmailError), et_email)
        } else if (!validatePhone(phone)) {
            showError(getString(R.string.invalidPhoneError), et_phone)
        } else if (!validatePassword(password)) {
            showError(getString(R.string.shortPasswordError), et_password)
        } else {
            validateDataFlag = true
        }
        return validateDataFlag
    }

    private fun isDataEmpty(): Boolean {
        if (name.isNullOrEmpty()) {
            showError(getString(R.string.emptyNameError), et_name)
        } else if (email.isNullOrEmpty()) {
            showError(getString(R.string.emptyEmailError), et_email)
        } else if (phone.isNullOrEmpty()) {
            showError(getString(R.string.emptyPhoneError), et_phone)
        } else if (password.isNullOrEmpty()) {
            showError(getString(R.string.emptyPasswordError), et_password)
        } else {
            emptyDataFlag = false
        }
        return emptyDataFlag
    }

    private fun showError(errorMessage: String, editText: EditText){
        progress_bar.visibility = View.GONE
        editText.error = errorMessage
    }

    private fun hideNavBar(){
        nav_bar = requireActivity().findViewById(R.id.nav_view)
        nav_bar.visibility = View.GONE
    }
}
package com.example.news.ui.registration.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.ui.registration.viewmodel.RegistrationViewModel
import com.example.news.ui.registration.viewmodel.RegistrationViewModelFactory
import com.example.news.utils.Constants
import com.example.news.utils.validateEmail
import com.example.news.utils.validatePassword
import com.example.news.utils.validatePhone


class RegistrationFragment : Fragment() {

    // xml parameters
    lateinit var et_name: EditText
    lateinit var et_password: EditText
    lateinit var et_email: EditText
    lateinit var et_phone: EditText
    lateinit var progress_bar: ProgressBar
    lateinit var btn_register: Button

    //user data
    lateinit var name: String
    lateinit var phone: String
    lateinit var email: String
    lateinit var password: String
    var validateDataFlag: Boolean = false
    var emptyDataFlag: Boolean = false

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
        setupSharedPreferences()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createUI(view)
        btn_register.setOnClickListener {
            register()
        }

    }

    private fun setupViewModel() {
      //  viewModelFactory =
      //      RegistrationViewModelFactory((requireActivity().application!! as MyApplication).repository)
     //   viewModel =
       //     ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)
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
    }

    private fun storeData() {
        name = et_name.text.toString().trim()
        email = et_email.text.toString().trim()
        phone = et_phone.text.toString().trim()
        password = et_password.text.toString().trim()
    }

    private fun register() {
        storeData()
        if (!isDataEmpty()) {
            if (!isDataValid()) {
                viewModel.register(name, phone, email, password)
                editor.putBoolean(Constants.loginFlag, true)
                editor.commit()
            }
        }
    }

    private fun isDataValid(): Boolean {
        if (!validateEmail(email)) {
            et_email.error = getString(R.string.invalidEmailError)
        } else if (!validatePhone(phone)) {
            et_phone.error = getString(R.string.invalidPhoneError)
        } else if (!validatePassword(password)) {
            et_password.error = getString(R.string.shortPasswordError)
        } else {
            validateDataFlag = true
        }
        return validateDataFlag
    }

    private fun isDataEmpty(): Boolean {
        if (name.isNullOrEmpty()) {
            et_name.error = getString(R.string.emptyNameError)
        } else if (email.isNullOrEmpty()) {
            et_email.error = getString(R.string.emptyEmailError)
        } else if (phone.isNullOrEmpty()) {
            et_phone.error = getString(R.string.emptyPhoneError)
        } else if (password.isNullOrEmpty()) {
            et_password.error = getString(R.string.emptyPasswordError)
        } else {
            emptyDataFlag = true
        }
        return emptyDataFlag
    }

}
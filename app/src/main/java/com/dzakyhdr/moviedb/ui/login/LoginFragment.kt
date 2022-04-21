package com.dzakyhdr.moviedb.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dzakyhdr.moviedb.R
import com.dzakyhdr.moviedb.databinding.FragmentLoginBinding
import com.dzakyhdr.moviedb.resource.Status
import com.dzakyhdr.moviedb.ui.ViewModelFactory
import com.dzakyhdr.moviedb.ui.register.RegisterViewModel
import com.dzakyhdr.moviedb.utils.SharedPreference
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    private var sharedPref: SharedPreference? = null
    private var status = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory(view.context)
        viewModel = ViewModelProvider(requireActivity(), factory)[LoginViewModel::class.java]
        sharedPref = SharedPreference(view.context)

        status = sharedPref?.getPrefKeyStatus("login_status") == true

        if (status) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        binding.btnLogin.setOnClickListener {

                viewModel.login(
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString()
                )


            viewModel.loginStatus.observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            sharedPref?.saveKey(it.data)
                            sharedPref?.saveKeyState(true)
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            Snackbar.make(
                                binding.root,
                                "User Tidak Ditemukan",
                                Snackbar.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }

        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
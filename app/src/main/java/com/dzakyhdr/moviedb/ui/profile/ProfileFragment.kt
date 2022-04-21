package com.dzakyhdr.moviedb.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dzakyhdr.moviedb.R
import com.dzakyhdr.moviedb.databinding.FragmentProfileBinding
import com.dzakyhdr.moviedb.databinding.FragmentUpdateProfileBinding
import com.dzakyhdr.moviedb.utils.SharedPreference


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shared = SharedPreference(view.context)
        val factory = ProfileViewModelProvider(shared)
        viewModel = ViewModelProvider(requireActivity(), factory)[ProfileViewModel::class.java]

        viewModel.apply {
            getUserData()
            fullname.observe(viewLifecycleOwner){
                binding.edtFullname.setText(it)
            }
            username.observe(viewLifecycleOwner){
                binding.edtUsername.setText(it)
            }

            date.observe(viewLifecycleOwner){
                binding.edtLahir.setText(it)
            }

            address.observe(viewLifecycleOwner){
                binding.edtAddress.setText(it)
            }

            email.observe(viewLifecycleOwner){
                binding.edtEmail.setText(it)
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLogout.setOnClickListener {
            shared.clearUsername()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
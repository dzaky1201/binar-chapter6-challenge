package com.dzakyhdr.moviedb.ui.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dzakyhdr.moviedb.R
import com.dzakyhdr.moviedb.data.local.auth.User
import com.dzakyhdr.moviedb.databinding.FragmentProfileBinding
import com.dzakyhdr.moviedb.utils.UserDataStoreManager


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
        val pref = UserDataStoreManager(view.context)
        val factory = ProfileViewModelProvider(pref)
        viewModel = ViewModelProvider(requireActivity(), factory)[ProfileViewModel::class.java]

        val user = User()

        viewModel.apply {
            getFullnameUsername().observe(viewLifecycleOwner) {
                user.fullname = it
                binding.edtFullname.setText(it)
            }
            getUsernameUsername().observe(viewLifecycleOwner) {
                user.username = it
                binding.edtUsername.setText(it)
            }

            getDateUsername().observe(viewLifecycleOwner) {
                user.ttl = it
                binding.edtLahir.setText(it)
            }

            getAddressUsername().observe(viewLifecycleOwner) {
                user.address = it
                binding.edtAddress.setText(it)
            }

            getEmailUsername().observe(viewLifecycleOwner) {
                user.email = it
                binding.edtEmail.setText(it)
            }

            getIdUsername().observe(viewLifecycleOwner) {
                user.id = it
            }

            getPassword().observe(viewLifecycleOwner) {
                user.password = it
            }

            getImage().observe(viewLifecycleOwner) {
                user.image = it
                val uriImage = Uri.parse(it)
                binding.imgProfile.setImageURI(uriImage)
                Glide.with(binding.root).load(it)
                    .circleCrop()
                    .into(binding.imgProfile)

            }
        }



        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.btnLogout.setOnClickListener {

            val dialog = AlertDialog.Builder(view.context)
            dialog.setTitle("Logout")
            dialog.setMessage("Apakah Anda Yakin Ingin Keluar ?")
            dialog.setPositiveButton("Keluar") { _, _ ->
                viewModel.clearStatusUser()
                viewModel.clearDataUser()
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }

            dialog.setNegativeButton("Batal") { listener, _ ->
                listener.dismiss()
            }


            dialog.show()

        }
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToUpdateProfileFragment(
                    user
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
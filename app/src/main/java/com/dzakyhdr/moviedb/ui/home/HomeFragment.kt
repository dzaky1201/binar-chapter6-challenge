package com.dzakyhdr.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyhdr.moviedb.R
import com.dzakyhdr.moviedb.databinding.FragmentHomeBinding
import com.dzakyhdr.moviedb.resource.Status
import com.dzakyhdr.moviedb.utils.UserDataStoreManager
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            HomeViewModelFactory.getInstance(
                view.context,
                UserDataStoreManager(view.context)
            )
        )[HomeViewModel::class.java]

        viewModel.getIdUser().observe(viewLifecycleOwner) {
            viewModel.userData(it)
        }

        val adapter = HomeAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.homeToolbar.inflateMenu(R.menu.home_menu)

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            when (user.status) {
                Status.SUCCESS -> {
                    if (user.data != null) {
                        binding.txtUsername.text = getString(R.string.username, user?.data.username)
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
                    Toast.makeText(requireContext(), user.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }

        viewModel.popular.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.d("HomeFragment", it.toString())
        }

        viewModel.errorStatus.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG).show()
                viewModel.onSnackbarShown()
            }

        }

        binding.homeToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.account -> {
                    findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                    true
                }
                R.id.action_favorite -> {
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                    true
                }
                else -> false
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
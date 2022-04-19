package com.dzakyhdr.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyhdr.moviedb.R
import com.dzakyhdr.moviedb.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

       binding.homeToolbar.inflateMenu(R.menu.home_menu)

        viewModel.loading.observe(viewLifecycleOwner){
            if (it){
                binding.loading.visibility = View.VISIBLE
            }else{
                binding.loading.visibility = View.GONE
            }
        }

        viewModel.popular.observe(viewLifecycleOwner){
            adapter.submitList(it)
            Log.d("HomeFragment", it.toString())
        }

        viewModel.errorStatus.observe(viewLifecycleOwner){
            if (it){
                Snackbar.make(binding.root, "Load Data Gagal", Snackbar.LENGTH_LONG).show()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
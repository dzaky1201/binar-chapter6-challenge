package com.dzakyhdr.moviedb.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyhdr.moviedb.databinding.FragmentFavoriteBinding
import com.dzakyhdr.moviedb.di.Injector
import com.dzakyhdr.moviedb.ui.viewmodelfactory.FavoriteViewModelFactory
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        favoriteViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as Injector).createFavoriteSubComponent().inject(this)

        viewModel.getUser()

        val adapter = FavoriteAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.getListFavorite.observe(viewLifecycleOwner) { favoriteList ->
            adapter.submitList(favoriteList)
        }

        binding.ivBack.setOnClickListener {
            it.findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.dzakyhdr.moviedb.ui.register

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dzakyhdr.moviedb.R
import com.dzakyhdr.moviedb.databinding.FragmentRegisterBinding
import com.dzakyhdr.moviedb.ui.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegisterViewModel
    private lateinit var dateListener: DatePickerDialog.OnDateSetListener
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory(view.context)
        viewModel = ViewModelProvider(requireActivity(), factory)[RegisterViewModel::class.java]

        dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        binding.edtLahir.setOnClickListener {
            DatePickerDialog(
                view.context,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        updateDateInView()

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val username = binding.edtUsername.text.toString()
            val fullname = binding.edtFullname.text.toString()
            val ttl = binding.edtLahir.text.toString()
            val address = binding.edtAddress.text.toString()
            val password = binding.edtPassword.text.toString()
            viewModel.save(email, username, fullname, ttl, address, password)
        }

        viewModel.saved.observe(viewLifecycleOwner) {
            val check = it.getContentIfNotHandled() ?: return@observe
            if (check) {
                Snackbar.make(binding.root, "User Berhasil Dibuat", Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Snackbar.make(binding.root, "User Gagal Dibuat", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.edtLahir.setText(sdf.format(calendar.time).toString())
    }

}
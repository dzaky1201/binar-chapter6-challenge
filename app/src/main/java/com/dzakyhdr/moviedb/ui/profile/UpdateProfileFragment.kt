package com.dzakyhdr.moviedb.ui.profile

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dzakyhdr.moviedb.R
import com.dzakyhdr.moviedb.data.local.auth.User
import com.dzakyhdr.moviedb.databinding.FragmentUpdateProfileBinding
import com.dzakyhdr.moviedb.ui.ViewModelFactory
import com.dzakyhdr.moviedb.utils.SharedPreference
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class UpdateProfileFragment : Fragment() {

    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UpdateProfileViewModel
    private lateinit var dateListener: DatePickerDialog.OnDateSetListener
    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shared = SharedPreference(view.context)
        val factory = ViewModelFactory(view.context)
        viewModel =
            ViewModelProvider(requireActivity(), factory)[UpdateProfileViewModel::class.java]

        val userData = arguments?.getParcelable<User>("user")

        if (userData != null) {
            binding.apply {
                edtEmail.setText(userData.email)
                edtUsername.setText(userData.username)
                edtLahir.setText(userData.ttl)
                edtAddress.setText(userData.address)
                edtFullname.setText(userData.fullname)
            }
        }



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

        binding.btnUpdate.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val username = binding.edtUsername.text.toString()
            val fullname = binding.edtFullname.text.toString()
            val ttl = binding.edtLahir.text.toString()
            val address = binding.edtAddress.text.toString()
            val password = userData?.password
            val id = userData?.id

            val user = User(
                id = id!!,
                email = email,
                username = username,
                fullname = fullname,
                ttl = ttl,
                address = address,
                password = password!!
            )
            viewModel.update(id.toInt(), email, username, fullname, ttl, address, password)
            shared.saveKey(user)
        }

        viewModel.saved.observe(viewLifecycleOwner) {
            val check = it.getContentIfNotHandled() ?: return@observe
            if (check) {
                Snackbar.make(binding.root, "User Berhasil Diupdate", Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateProfileFragment_to_profileFragment)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
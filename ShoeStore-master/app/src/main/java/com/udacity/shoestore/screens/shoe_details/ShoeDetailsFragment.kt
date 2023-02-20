package com.udacity.shoestore.screens.shoe_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.FragmentShoesListBindingImpl
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailsBinding
    val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        // fill fields
        binding.shoe = Shoe("PlaceHolder", 4.0, "PlaceHolder", "PlaceHolder")
        binding.btnAddList.setOnClickListener {
            Timber.i("${binding.shoe}")
            viewModel.addShoe(binding.shoe as Shoe)
            findNavController().navigateUp()
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

}
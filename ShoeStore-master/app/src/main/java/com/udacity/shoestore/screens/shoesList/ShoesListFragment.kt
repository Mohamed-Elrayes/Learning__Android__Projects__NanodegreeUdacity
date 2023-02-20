package com.udacity.shoestore.screens.shoesList

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ShoeLayoutBinding
import com.udacity.shoestore.screens.login.LoginFragmentDirections
import com.udacity.shoestore.screens.shoe_details.ShoeDetailsFragmentDirections
import timber.log.Timber


class ShoesListFragment : Fragment() {
    private lateinit var binding: FragmentShoesListBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]

        viewModel.shoeListItem.observe(viewLifecycleOwner, Observer { shoes ->
            if (shoes.isEmpty()) {
                Timber.i("${shoes.size}")
            }
            shoes.forEach { shoe ->
                val shoeLayout: ShoeLayoutBinding =
                    DataBindingUtil.inflate(inflater, R.layout.shoe_layout, container, false)
                shoeLayout.shoe = shoe
                binding.shoeItemsList.addView(shoeLayout.root)
            }
        })
        binding.btnNewShoeItem.setOnClickListener {
            findNavController().navigate(ShoesListFragmentDirections.actionShoesFragmentToShoeDetailsFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Timber.i("onCreateOptionsMenu call")
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menue, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.i("onOptionsItemSelected call")
       return when (item.itemId) {
            R.id.loginFragment -> {
                requireView().findNavController()
                    .navigate(ShoesListFragmentDirections.actionShoesToLogin())
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }
}
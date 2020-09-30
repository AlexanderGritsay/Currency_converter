package com.example.converter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.converter.R
import com.example.converter.databinding.FragmentConverterBinding
import com.example.converter.viewmodels.ConverterViewModel

class ConverterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentConverterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_converter, container, false)
        binding.lifecycleOwner = this
        val arguments = ConverterFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = ConverterViewModel.Factory(arguments.currency)

        //creating and reconnecting to viewModel
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ConverterViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.convertClicked.observe(viewLifecycleOwner, Observer { convertIsClicked ->
            if (convertIsClicked) {
                val rubSum = binding.rubSumEdit.text
                viewModel.convert(rubSum)
                viewModel.onConvertAlreadyClicked()
            }
        })

        return binding.root
    }
}
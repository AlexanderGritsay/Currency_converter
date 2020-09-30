package com.example.converter.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.converter.R
import com.example.converter.databinding.FragmentCurrencyListBinding
import com.example.converter.viewmodels.CurrenciesApiStatus
import com.example.converter.viewmodels.CurrencyListViewModel

class CurrencyListFragment : Fragment() {
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCurrencyListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_currency_list, container, false)

        val viewModelFactory = CurrencyListViewModel.Factory(this.requireActivity().application)

        //creating and reconnecting to viewModel
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(CurrencyListViewModel::class.java)

        binding.lifecycleOwner = this

        //bind viewModel to xml
        binding.currencyListViewModel = viewModel

        val adapter = CurrencyListAdapter(ExchangeClick {
            viewModel.onCurrencyConverClicked(it)
        })

        viewModel.navigateToConverter.observe(viewLifecycleOwner, Observer {currency ->
             currency?.let{
                findNavController().navigate(
                    CurrencyListFragmentDirections
                        .actionCurrencyListFragmentToConverterFragment(it)
                )
                 viewModel.onNavigationDone()
             }
        })

        binding.recyclerView.adapter = adapter

        binding.refresher.apply {
            setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorPrimary
                )
            )
            setColorSchemeColors(Color.WHITE)
            setOnRefreshListener {
                viewModel.refreshCurrencies()
            }
        }

        viewModel.isRefreshed.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.refresher.isRefreshing = false
                viewModel.onRefreshed()
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == CurrenciesApiStatus.ERROR) {
                Toast.makeText(
                    this.context,
                    "Some problems with connection to server. Check your internet connection or try again later!",
                    Toast.LENGTH_LONG
                ).show()
            }

        })


        return binding.root
    }
}

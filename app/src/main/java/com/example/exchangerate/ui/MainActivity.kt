package com.example.exchangerate.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exchangerate.databinding.ActivityMainBinding
import com.example.exchangerate.ui.adapter.ExchangeResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModels()

    private val exchangeResultAdapter by lazy { ExchangeResultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            etAmount.doOnTextChanged { text, _, _, _ ->
                vm.onAmountChanged(
                    if (text.isNullOrEmpty()) 0f else text.toString().toFloat()
                )
            }
            setupExchangeResultRv()
            observeVm()
        }
    }

    private fun ActivityMainBinding.observeVm() {
        lifecycle.coroutineScope.launch {
            vm.currencyList.collect {
                ddCurrencyList.apply {
                    setAdapter(
                        ArrayAdapter(
                            this@MainActivity,
                            android.R.layout.simple_list_item_1,
                            it.map { it.id })
                    )
                    setOnClickListener { showDropDown() }
                    doAfterTextChanged {
                        vm.onBaseCurrencyChanged(it.toString())
                    }
                }
            }

        }
        lifecycle.coroutineScope.launch {
            vm.currencyExchangeResult.collect {
                exchangeResultAdapter.submitList(it)
            }
        }
    }

    private fun ActivityMainBinding.setupExchangeResultRv() {
        rvExchangeRates.apply {
            adapter = exchangeResultAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }
    }
}
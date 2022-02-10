package com.application.hilttest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.application.hilttest.state.CounterViewModel
import com.application.hilttest.R
import com.application.hilttest.databinding.FragmentCounterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CounterFragment : Fragment(R.layout.fragment_counter) {

    private var currentBinding: FragmentCounterBinding? = null
    val binding get() = currentBinding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val viewModel: CounterViewModel by viewModels(
        factoryProducer = { factory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentBinding = FragmentCounterBinding.bind(view)
        afterViews()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCount().collect { counterValue ->
                binding.counterLabel.text = counterValue
            }
        }
    }

    private fun afterViews() = with(binding) {
        increaseButton.setOnClickListener {
            viewModel.increase()
        }

        decreaseButton.setOnClickListener {
            viewModel.decrease()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}
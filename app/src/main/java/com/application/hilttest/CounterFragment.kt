package com.application.hilttest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.application.hilttest.databinding.FragmentCounterBinding

class CounterFragment : Fragment(R.layout.fragment_counter) {

    private var currentBinding: FragmentCounterBinding? = null
    private val binding get() = currentBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentBinding = FragmentCounterBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}
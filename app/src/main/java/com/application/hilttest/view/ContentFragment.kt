package com.application.hilttest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.hilttest.R
import com.application.hilttest.databinding.FragmentContentBinding

class ContentFragment : Fragment(R.layout.fragment_content) {

    private var currentBinding: FragmentContentBinding? = null
    private val binding get() = currentBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentBinding = FragmentContentBinding.bind(view)

        binding.counterButton.setOnClickListener {
            findNavController().navigate(R.id.action_contentFragment_to_counterFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}
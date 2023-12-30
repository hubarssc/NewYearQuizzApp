package com.example.quizzapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentHomePageBinding
import com.example.quizzapp.model.QuestionDataSource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomePageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        setupViews()
        setupObservers()
    }

    private fun init() {
        viewModel.loadCategories()
    }

    private fun setupObservers() {
        viewModel.categories.observe(viewLifecycleOwner) { dataset ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, dataset
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.categorySpinner.adapter = adapter

            binding.categorySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        pos: Int,
                        id: Long
                    ) {
                        val num = parent.getItemAtPosition(pos)
                        QuestionDataSource.category =
                        QuestionDataSource.categories.stream()
                            .filter { item -> item.categoryTitle == num }.findFirst().get()


                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }
    }

    private fun setupViews() {
        binding.startButton.setOnClickListener {


            findNavController().navigate(R.id.action_homePageFragment_to_questionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
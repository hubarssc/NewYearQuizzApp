package com.example.quizzapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.data.QuizResult
import com.example.quizzapp.databinding.FragmentQuizResultBinding


class QuizResultFragment : Fragment() {

    private var _binding: FragmentQuizResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (parentFragmentManager.fragments.find { it is QuizResultFragment } != null) {
                    findNavController().navigate(R.id.action_quizResultFragment_to_homePageFragment)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, onBackPressedCallback
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quizResult = arguments?.getParcelable<QuizResult>("quiz_result")

        setupUi(quizResult)

        binding.shareButtin.setOnClickListener {
            findNavController().navigate(R.id.action_quizResultFragment_to_homePageFragment)
        }
    }

    private fun setupUi(quizResult: QuizResult?) {
        Log.d("TAG", "Data: $quizResult")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



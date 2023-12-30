package com.example.quizzapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.data.QuizResult
import com.example.quizzapp.data.orZero
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
    ): View {
        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quizResult = arguments?.getParcelable<QuizResult>("quiz_result")

        setupUi(quizResult)
    }

    private fun setupUi(quizResult: QuizResult?) {
        "${quizResult?.correctAnswers.orZero()} / ${quizResult?.allAnswers.orZero()}".also { binding.quizTextResult.text = it } //TODO
        binding.quizPercentageImageView.setImageResource(createAppropriateIconResource(quizResult))
        binding.retryButton.setOnClickListener {
            findNavController().navigate(R.id.action_quizResultFragment_to_homePageFragment)
        }

        binding.shareButtin.setOnClickListener {

        }
    }

    private fun createAppropriateIconResource(quizResult: QuizResult?): Int {
        val percentageResult =
            quizResult?.correctAnswers.orZero() / quizResult?.allAnswers.orZero().toDouble() //TODO: refactor to avoid possibility to division by zero

        return if (percentageResult > 0.8) {
            R.drawable.result_1
        } else if (percentageResult > 0.5) {
            R.drawable.result_2
        } else {
            R.drawable.result_3
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



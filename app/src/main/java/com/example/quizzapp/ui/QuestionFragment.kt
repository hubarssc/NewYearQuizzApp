package com.example.quizzapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.data.Constants.NO_ANSWER_SELECTED
import com.example.quizzapp.data.Question
import com.example.quizzapp.databinding.FragmentQuestionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.questionAnswers.setOnCheckedChangeListener { rg, checkedId ->
            viewModel.updateQuizState(checkedId)
        }
        setupObservers(view)
        viewModel.getNextQuestion()
    }

    private fun setupObservers(view: View) {
        viewModel.activeQuestion.observe(this) { question ->
            question?.let {
                setUpUi(question)
            }
        }
        viewModel.isLastQuestion.observe(this) { lastQuestion ->
            if (lastQuestion) {
                with(binding.nextButton) {
                    text = getString(R.string.finish_text)
                    setOnClickListener {
                        val bundle = bundleOf("quiz_result" to viewModel.createQuizResult())
                        findNavController().navigate(R.id.action_questionFragment_to_quizResultFragment, bundle)
                    }
                }
            } else {
                with(binding.nextButton) {
                    text = getString(R.string.next_text)
                    setOnClickListener {
                        viewModel.getNextQuestion()
                    }
                }
            }
        }
        viewModel.isFirstQuestion.observe(this) { firstQuestion ->
            if (firstQuestion) {
                binding.prevButton.setOnClickListener {
                    findNavController().popBackStack()
                }
            } else {
                binding.prevButton.setOnClickListener {
                    viewModel.getPrevQuestion()
                }
            }
        }
        viewModel.selectedAnswer.observe(this) { selectedRadioButtonId ->
            if (selectedRadioButtonId != NO_ANSWER_SELECTED) {
                binding.questionAnswers.check(selectedRadioButtonId)
            } else {
                binding.questionAnswers.clearCheck()
            }
        }
    }

    private fun setUpUi(question: Question) {
        with(binding) {
            questionText.text = question.questionText
            firstRadioButton.text = question.option1
            secondRadioButton.text = question.option2
            thurdRadioButton.text = question.option3
            fouthRadioButton.text = question.option4
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
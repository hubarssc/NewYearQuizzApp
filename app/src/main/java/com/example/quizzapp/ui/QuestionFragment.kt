package com.example.quizzapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.questionAnswers.setOnCheckedChangeListener { rg, checkedId ->
            val radioButton: View? = binding.questionAnswers.findViewById(checkedId)
            radioButton?.let {
                viewModel.updateQuizState(binding.questionAnswers.indexOfChild(radioButton))
            }
        }
        setupObservers()

        viewModel.startQuizSession()
    }

    private fun setupObservers() {
        viewModel.activeQuestion.observe(viewLifecycleOwner) { question ->
            question?.let {
                setUpUi(question)
            }
        }
        viewModel.isLastQuestion.observe(viewLifecycleOwner) { lastQuestion ->
            if (lastQuestion) {
                with(binding.nextButton) {
                    text = getString(R.string.finish_text)
                    setOnClickListener {
                        val bundle = bundleOf("quiz_result" to viewModel.createQuizResult())
                        findNavController().navigate(
                            R.id.action_questionFragment_to_quizResultFragment,
                            bundle
                        )
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
        viewModel.isFirstQuestion.observe(viewLifecycleOwner) { firstQuestion ->
            binding.prevButton.setOnClickListener {
                val navController = findNavController()
                if (firstQuestion) {
                    // Если это первый вопрос, навигируем на стартовый экран
                    navController.navigate(R.id.homePageFragment)
                } else {
                    // Иначе возвращаемся на предыдущий вопрос
                    viewModel.getPrevQuestion()
                }
            }
        }


        viewModel.selectedAnswer.observe(viewLifecycleOwner) { selectedRadioButtonId ->
            if (selectedRadioButtonId != NO_ANSWER_SELECTED) {
                (binding.questionAnswers.getChildAt(selectedRadioButtonId) as RadioButton).isChecked =
                    true
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
            thirdRadioButton.text = question.option3
            fourthRadioButton.text = question.option4
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.project.databinding.FragmentQuestionBinding
import java.util.*
import kotlin.collections.ArrayList

private var questions: ArrayList<Question> = ArrayList()
private lateinit var binding : FragmentQuestionBinding
private var data=""

class QuestionFragment : Fragment() {

    var currentQuestionIndex=0
    var number=0
    val viewmodel:Model by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentQuestionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.number=0
        random()
        changeTextData()

        binding.radiogroup.setOnCheckedChangeListener { radioGroup, i ->
            if(i==R.id.answer1){
                if(binding.answer1.text.equals(data)){
                    viewmodel.plus()
                }

            }
            if(i==R.id.answer2){
                if(binding.answer2.text.equals(data)){
                    viewmodel.plus()
                }

            }
            if(i==R.id.answer3){
                if(binding.answer3.text.equals(data)){
                    viewmodel.plus()
                }

            }
            if(i==R.id.answer4){
                if(binding.answer4.text.equals(data)){
                    viewmodel.plus()
                }

            }


        }

        binding.start.setOnClickListener() {
            if (number >= viewmodel.result.size-1) {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentView, ProfileFragment.newInstance())
                    ?.commit()

                return@setOnClickListener
            }

            currentQuestionIndex+=1
            number=number+1
            binding.progressBar.setProgress(number)
            binding.progressBar.setMax(viewmodel.numberQuestion)
            binding.max.setText(""+number+"/"+viewmodel.numberQuestion)



            changeTextData()
        }
        binding.back.setOnClickListener()
        {
            if (currentQuestionIndex <= 0) {
                Toast.makeText(context, "You can't go back!", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }

            number-=1
            binding.progressBar.setProgress(number)
            binding.max.setText(""+number+"/"+viewmodel.numberQuestion)
            currentQuestionIndex-=1
            changeTextData()

        }

    }

    fun random() {
        //questions.shuffle()
        viewmodel.result.shuffle()
    }

    private fun changeTextData() {
        if (viewmodel.result.size == 0) {
            Log.d("", "changeTextData: empty result size")
            return
        }

        val question: Question = viewmodel.result.get(currentQuestionIndex)
        data = question.trueAnswer
        binding.questionText.text = question.question

        if (question.questionList.isEmpty()) {
            Log.d("", "changeTextData: empty list")
            return
        }

        binding.answer1.setText(question.questionList[0])
        binding.answer2.setText(question.questionList[1])
        binding.answer3.setText(question.questionList[2])
        binding.answer4.setText(question.questionList[3])

            //viewmodel.result.remove(question)
    }


    companion object {
        @JvmStatic
        fun newInstance() = QuestionFragment()

    }

}



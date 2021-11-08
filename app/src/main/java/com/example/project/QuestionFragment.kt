package com.example.project

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.FragmentQuestionBinding
import java.io.FileNotFoundException
import java.util.*
import kotlin.collections.ArrayList

private var questions: ArrayList<Question> = ArrayList()
private lateinit var binding : FragmentQuestionBinding
private var data=""

class QuestionFragment : Fragment() {
    var a=0
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
            changeTextData()

        }
        binding.back.setOnClickListener()
        {
            if(a!=0){
                a-=2
                changeTextData()
            }
            else{
                changeTextData()
            }

        }

    }

    fun random() {
        //questions.shuffle()
        viewmodel.result.shuffle()
    }

    private fun changeTextData() {

        if (viewmodel.result.size >=a+1) {
            val question: Question = viewmodel.result.get(a)
            data = question.trueAnswer
            binding.questionText.text = question.question
            val answerText: ArrayList<String> = ArrayList()
            for (answer in question.questionList) {
                answerText.add(answer)
            }
            binding.answer1.setText(answerText[0].toString())
            binding.answer2.setText(answerText[1].toString())
            binding.answer3.setText(answerText[2].toString())
            binding.answer4.setText(answerText[3].toString())
            a+=1

            //viewmodel.result.remove(question)
        }


            else {

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentView, ProfileFragment.newInstance())
                    ?.commit()

            }
        }


    companion object {
        @JvmStatic
        fun newInstance() = QuestionFragment()

    }

}



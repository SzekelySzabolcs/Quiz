package com.example.project

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.project.databinding.FragmentAddQuestionsBinding
import kotlin.collections.ArrayList

private lateinit var binding: FragmentAddQuestionsBinding
class AddQuestions : Fragment() {

    val data:Model by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAddQuestionsBinding.inflate(layoutInflater)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var categ=""
        val string = data.category.distinct()
        //data.category.distinct()
        binding.spinner.adapter =
            context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,string) }
        val save:ArrayList<String> = ArrayList()
        binding.spinner.onItemSelectedListener = object
            :AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                categ=(string.get(p2))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context,"Please Select an Option",Toast.LENGTH_SHORT).show()
            }

        }
        binding.answer1.setTextColor(Color.GREEN)

             binding.add.setOnClickListener(){

                if(binding.questionText.text.isEmpty() or binding.answer1.text.isEmpty() or binding.answer2.text.isEmpty()
                    or binding.answer3.text.isEmpty() or binding.answer4.text.isEmpty()){
                    Toast.makeText(context,"empty field",Toast.LENGTH_SHORT).show()
                }
                 else{
                     save.add(categ)
                    save.add(binding.questionText.text.toString())
                    save.add(binding.answer1.text.toString())
                    save.add(binding.answer2.text.toString())
                    save.add(binding.answer3.text.toString())
                    save.add(binding.answer4.text.toString())

                    val answer: ArrayList<String> = ArrayList()
                    for (i in 2 until save.size){
                        answer.add(save[i])
                    }
                    val addQuestion= Question(save[1], answer, save[2],save[0])
                    Toast.makeText(context,""+save[0],Toast.LENGTH_LONG).show()

                    data.numberQuestion+=1
                    data.result.add(addQuestion)
                    save.removeAll(save)

                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragmentView,QuizStartFragment.newInstacne())
                        //  ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
                }

            }
    }

    companion object {
        fun newInstance() =AddQuestions()
    }
}
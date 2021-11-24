package com.example.project

import android.graphics.Color
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.databinding.FragmentQuestionDetailBinding

private lateinit var binding: FragmentQuestionDetailBinding
class QuestionDetailFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var array:ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("123")
            param2 = it.getString("asd")
            array= it.getStringArrayList("dasda") as ArrayList<String>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentQuestionDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textquestion.text=param1.toString()
      //  binding.Single.text="Single answer"

            binding.answer1.text=array[0]
            binding.answer2.text=array[1]
            binding.answer3.text=array[2]
            binding.answer4.text=array[3]

        if (binding.answer1.text.equals(param2)){
            binding.answer1.setTextColor(Color.GREEN)
        }
        if (binding.answer2.text.equals(param2)){
            binding.answer2.setTextColor(Color.GREEN)
        }
        if (binding.answer3.text.equals(param2)){
            binding.answer3.setTextColor(Color.GREEN)
        }
        if (binding.answer4.text.equals(param2)){
            binding.answer4.setTextColor(Color.GREEN)
        }

    }
    companion object {

        fun newInstance(param1: String, param2: String,list: ArrayList<String>) =
            QuestionDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("123", param1)
                    putString("asd", param2)
                    putStringArrayList("dasda",list)

                }
            }
    }
}
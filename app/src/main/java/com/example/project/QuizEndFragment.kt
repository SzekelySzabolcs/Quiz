package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.FragmentQuestionBinding
import com.example.project.databinding.FragmentQuizEndBinding

private lateinit var binding:FragmentQuizEndBinding

class QuizEndFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentQuizEndBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val point:Model by activityViewModels()
        binding.points.setText("${point.number.toString()}/${point.numberQuestion}")
    }

    companion object {

        fun newInstance() =QuizEndFragment()

                    }

}


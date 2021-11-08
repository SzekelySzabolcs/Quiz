package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.project.databinding.ActivityMainBinding
import com.example.project.databinding.FragmentHomeBinding

private lateinit var binding: FragmentHomeBinding
class HomeFragment : Fragment() {

    val data:Model by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val string= arrayOf("Movie","Music",)
        for (i in string){
            data.category.add(i)
        }


        binding.test.setOnClickListener(){
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentView,QuestionFragment.newInstance())
                ?.commit()
        }
        binding.create.setOnClickListener(){
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentView,AddQuestions.newInstance())
                ?.commit()
        }

    }

    companion object{

        fun newInstance()=HomeFragment()

    }

}
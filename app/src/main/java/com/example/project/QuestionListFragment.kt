package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.FragmentAddQuestionsBinding
import com.example.project.databinding.FragmentQuestionListBinding
import retrofit2.Retrofit

private lateinit var binding:FragmentQuestionListBinding
class QuestionListFragment : Fragment() {

    private lateinit var adapter: RecyclerAdapter
    val set:Model by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentQuestionListBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView= view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter= RecyclerAdapter(set.result,requireContext()){index -> deleteitem(index)}
        recyclerView.adapter=adapter
        Retrofit.Builder()
       binding.spinner

    }

    private fun deleteitem(index: Int) {
        val question: Question = set.result.get(index)
       // Toast.makeText(context,""+set.result.get(index),Toast.LENGTH_LONG).show()
        set.result.remove(question)
        adapter.setItem(set.result)
    }



    companion object {

        fun newInstance() =QuestionListFragment()

    }
}
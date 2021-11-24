package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
    val arrayQuestion:ArrayList<Question> = ArrayList()

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

        var categ:String
        val string = set.category.distinct()
        binding.spinner.adapter=context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,string) }
        binding.spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                arrayQuestion.removeAll(arrayQuestion)
                categ=string.get(p2)
                val recyclerView= view.findViewById<RecyclerView>(R.id.recycler)
                recyclerView.layoutManager=LinearLayoutManager(context)
                recyclerView.setHasFixedSize(true)


                for (index in set.result){
                    if(index.categ.equals(categ)){
                        arrayQuestion.add(index)
                    }
                }

                adapter= RecyclerAdapter(arrayQuestion,requireContext()){index -> deleteitem(index)}
                recyclerView.adapter=adapter
                Retrofit.Builder()


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }



    }

    private fun deleteitem(index: Int) {
        val question: Question = arrayQuestion.get(index)
        set.result.remove(question)
       // Toast.makeText(context,""+set.result.get(index),Toast.LENGTH_LONG).show()
        arrayQuestion.remove(question)
        adapter.setItem(arrayQuestion)
        set.numberQuestion-=1
    }



    companion object {

        fun newInstance() =QuestionListFragment()

    }
}
package com.example.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val data:ArrayList<Question>,val context: Context ,val onClickDelete:(Int)->Unit):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var listdata=data

    lateinit var details:TextView
    lateinit var question:TextView
    lateinit var single:TextView
    lateinit var answer:TextView
    lateinit var delete:TextView


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       val v= LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)

    }
   inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

       fun bind(dat: Question, index:Int){
           question=itemView.findViewById(R.id.question_text)
           single=itemView.findViewById(R.id.textView3)
           answer=itemView.findViewById(R.id.textView4)
           details=itemView.findViewById(R.id.details)
           delete=itemView.findViewById(R.id.delete)

           question.text=dat.question
           single.text="Single answer"
           answer.text=dat.trueAnswer
           details.setOnClickListener {

               (context as MainActivity).supportFragmentManager
                   ?.beginTransaction()
                   ?.replace(R.id.fragmentView,QuestionDetailFragment.newInstance(dat.question.toString(),dat.trueAnswer.toString(),
                       dat.questionList as ArrayList<String>
                   ))
                   ?.commit()
           }
           delete.setOnClickListener(){
               onClickDelete(index)
           }
       }

    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(listdata[position],position)

    }
    fun setItem(items:ArrayList<Question>){
        listdata=items
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
     return listdata.size
    }


}
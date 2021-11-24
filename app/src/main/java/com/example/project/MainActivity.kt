package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.RetroService
import com.example.retrofit.ServiceBuilder
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.FileNotFoundException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    val baseUrl="https://pure-gorge-51703.herokuapp.com"
    lateinit var viewmodel:Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel= ViewModelProvider(this).get(Model::class.java)
        readFile()
        getCurrentData()

        var btn:BottomNavigationView = findViewById(R.id.bottom_nav)
       // var frag:FrameLayout=findViewById(R.id.fragmentView)
        supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentView,HomeFragment.newInstance())
            // ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()

        btn.setOnNavigationItemReselectedListener { item->
            when(item.itemId){
                R.id.nav_profile->{
                    supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragmentView,ProfileFragment.newInstance())
                        //?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
                    }
                R.id.nav_question->{
                    supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragmentView,QuizStartFragment.newInstacne())
                      //  ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
                }
                R.id.nav_home ->{
                    supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragmentView,HomeFragment.newInstance())
                      //  ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
                }
                R.id.nav_list->{
                    supportFragmentManager
                    ?.beginTransaction()
                        ?.replace(R.id.fragmentView,QuestionListFragment.newInstance())
                        //  ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
                }
            }
        }

    }
    private fun readFile() {
        try {
            val scanner = Scanner(this?.assets?.open("answer.txt"))
            while (scanner.hasNext()) {
                //felosztja a sort "," szerint
                val array = scanner.nextLine().split(",")
                val answer: ArrayList<String> = ArrayList()
                //valaszok hozza adasa
                for (i in 3 until array.size) {
                    answer.add(array[i])
                }
                val question = Question(array[0], answer, array[1],array[2])
                viewmodel.result.add(question)
                viewmodel.category.add(array[2])
                //questions.add(question)
            }

        } catch (e: FileNotFoundException) {
            print(e)
        }
        viewmodel.numberQuestion= viewmodel.result.size
    }

    internal fun getCurrentData(){
        val request=ServiceBuilder.buildService(RetroService::class.java)
        val map = HashMap<String, String>()
        var szam: Int = 10
        map.put("amount", szam.toString())
        map.put("type", "multiple")
        val call = request.getData(map)

        call.enqueue(object:Callback<QuestionResponse>{
            override fun onResponse(
                call: Call<QuestionResponse>,
                response: Response<QuestionResponse>
            ) {
                if (response.isSuccessful){

                    val questionResponse = response.body()
                    val answer= mutableListOf<String>()
                    val categoryList = ArrayList<String>()

                    for (result in questionResponse?.getResults()!!){

                        categoryList.add(result?.getCategory().toString())

                        for(item in result?.getIncorrectAnswers()!!){
                            answer.add(item.toString())
                        }

                        answer.add(result.getCorrectAnswer().toString())
                        val question = Question(result.getQuestion().toString(), answer.toList(),
                            result.getCorrectAnswer().toString(), result.getCategory().toString())
                        viewmodel.result.add(question)

                        answer.removeAll(answer)

                    }
                    viewmodel.category += categoryList
                    viewmodel.numberQuestion+=categoryList.size

                    Log.d("Retrofit", "onResponse: Success")
                }
            }
            override fun onFailure(call: Call<QuestionResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}




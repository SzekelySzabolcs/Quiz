package com.example.project

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.set
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.FileNotFoundException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var viewmodel:Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel= ViewModelProvider(this).get(Model::class.java)



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
        readFile()
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


}




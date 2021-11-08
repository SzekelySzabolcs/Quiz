package com.example.project

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.io.FileNotFoundException
import java.util.*
import kotlin.collections.ArrayList

class QuizController : AppCompatActivity() {

    private var questions: ArrayList<Question> = ArrayList()
    private lateinit var questionName: TextView
    private lateinit var sendButton: Button
    private lateinit var inputEditText: EditText
    private lateinit var answerView: TextView

    // az onCreate letrehozz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_controller)
/*
        questionName = findViewById(R.id.question_text)
        sendButton = findViewById(R.id.send_button)
        inputEditText = findViewById(R.id.answer_edit_text)
        answerView = findViewById(R.id.answer_text)

        readFile()
        random()
        createSnacbar()
        changeTextData()

        sendButton.setOnClickListener {
            if (inputEditText.text.isNotEmpty()){
                changeTextData()
            }

        }
    }

    private fun createSnacbar() {
        sendButton.setOnClickListener{
            Snackbar.make(it,"Button clicked ",Snackbar.LENGTH_LONG)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .setBackgroundTint(Color.parseColor("006400"))
                .setAction("cancel"){

                }.show()

        }
    }

    private fun changeTextData () {
        if (questions.size != 0) {
            val question: Question = questions.get(0)
            questionName.text = question.question
            var answerText = ""
            for (answer in question.questionList) {
                answerText += answer+"  "

            }
            answerView.setText(answerText)
            //truOrFalse()
            questions.remove(question)
        }
    }




    private fun readFile(){
        try {
            val scanner = Scanner (this.assets.open("answer.txt"))
            while (scanner.hasNext()) {
                //felosztja a sort "," szerint
                val array=scanner.nextLine().split(",")
                val answer : ArrayList<String> = ArrayList()
                //valaszok hozza adasa
                for (i in 3 until array.size){
                    answer.add(array[i])
                }
                val question=Question(array[0],answer, array[1])
                questions.add(question)
            }

        } catch (e: FileNotFoundException) {
            print(e)
        }



    }
    private fun random(){
        questions.shuffle()
    }/*
    private fun truOrFalse(){
        val question:Question=questions.get(1)
        if(inputEditText.equals(question.trueAnswer)){
            Toast.makeText(this,"good",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"false",Toast.LENGTH_SHORT).show()
        }
    }*/
*/
    }
}
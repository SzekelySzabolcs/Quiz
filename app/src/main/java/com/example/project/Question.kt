package com.example.project

data class Question( var question: String, var questionList: List<String>, val trueAnswer:String,var categ:String) {

    override fun toString(): String {
        var text = "";
        text += question + " ";

        for (answer in questionList) {
            text += answer + " ";
        }

        return text;
    }
}

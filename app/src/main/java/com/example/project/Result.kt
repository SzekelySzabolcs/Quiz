package com.example.project

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


class Result {

    @SerializedName("category")
    @Expose
    private var category: String? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("difficulty")
    @Expose
    private var difficulty: String? = null

    @SerializedName("question")
    @Expose
    private var question: String? = null

    @SerializedName("correct_answer")
    @Expose
    private var correctAnswer: String? = null

    @SerializedName("incorrect_answers")
    @Expose
    private var incorrectAnswers: List<String?>? = null

    fun getCategory(): String? {
        return category
    }

    open fun setCategory(category: String?) {
        this.category = category
    }

    open fun getType(): String? {
        return type
    }

    open fun setType(type: String?) {
        this.type = type
    }

    open fun getDifficulty(): String? {
        return difficulty
    }

    open fun setDifficulty(difficulty: String?) {
        this.difficulty = difficulty
    }

    open fun getQuestion(): String? {
        return question
    }

    open fun setQuestion(question: String?) {
        this.question = question
    }

    open fun getCorrectAnswer(): String? {
        return correctAnswer
    }

    open fun setCorrectAnswer(correctAnswer: String?) {
        this.correctAnswer = correctAnswer
    }

    open fun getIncorrectAnswers(): List<String?>? {
        return incorrectAnswers
    }

    open fun setIncorrectAnswers(incorrectAnswers: List<String?>?) {
        this.incorrectAnswers = incorrectAnswers
    }


}
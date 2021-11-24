package com.example.project

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionResponse (){

    @SerializedName("response_code")
    @Expose
    private var responseCode: Int? = null;

    @SerializedName("results")
    @Expose
    private var results : List<Result?>? = null;

    open fun getResponseCode(): Int? {
        return responseCode
    }

    open fun setResponseCode(responseCode: Int?) {
        this.responseCode = responseCode!!
    }

    open fun getResults(): List<Result?>? {
        return results
    }

    open fun setResults(results: List<Result?>?) {
        this.results = results
    }
}
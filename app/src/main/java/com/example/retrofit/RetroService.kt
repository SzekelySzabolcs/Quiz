package com.example.retrofit

import com.example.project.QuestionResponse
import retrofit2.http.*

public interface RetroService {
    @GET("api.php")
    fun getData(@QueryMap map: HashMap<String, String>):retrofit2.Call<QuestionResponse>
}
package com.example.lokflix.ApiConsumer


import com.example.lokflix.Model.Filmes
import retrofit2.Call
import retrofit2.http.GET

interface Api {

        @GET("filmes.json?alt=media&token=8435b463-eec0-49de-9142-d83140e1f9bc")
        fun filmes(): Call<ArrayList<Filmes>>
    }



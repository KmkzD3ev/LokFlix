package com.example.lokflix.Views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lokflix.Adapter.adapter_categoria

import com.example.lokflix.ApiConsumer.Api
import com.example.lokflix.Model.Categoria

import com.example.lokflix.Model.Filmes
import com.example.lokflix.Model.GrupoFilmes
import com.example.lokflix.R
import com.example.lokflix.databinding.ActivityTelaCadastroBinding
import com.example.lokflix.databinding.ActivityTelaHomeBinding
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Tela_Home : AppCompatActivity() {
    private val db = FirebaseAuth.getInstance()
    lateinit var binding: ActivityTelaHomeBinding
    private lateinit var adapterCategoria: adapter_categoria
    val listGrupos: MutableList<GrupoFilmes> = mutableListOf()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewFilmes =binding.recycleCategorias
        recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
         adapterCategoria =  adapter_categoria(this,listGrupos)
        recyclerViewFilmes.setHasFixedSize(true)
        recyclerViewFilmes.adapter = adapterCategoria



        binding.txtSair.setOnClickListener { view ->
            db.signOut()
            val intent = Intent(this, Tela_Login::class.java)
            startActivity(intent)
        }




        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(" https://firebasestorage.googleapis.com/v0/b/app-delivery-97d5b.appspot.com/o/")
            .build()
            .create(Api::class.java)

        retrofit.filmes().enqueue(object : Callback<ArrayList<Filmes>> {
            override fun onResponse(call: Call<ArrayList<Filmes>>, response: Response<ArrayList<Filmes>>) {
                if (response.code() == 200) {
                    response.body()?.let { filmes ->
                        Log.d("Home-imgs", "URL da imagem: ${listGrupos}")
                        // Exemplo de agrupamento por faixa de IDs
                        val grupo1a5 = GrupoFilmes("Recomendados",
                            filmes.filter { it.id in 1..3 }.toMutableList()
                        )
                        val grupo6a10 = GrupoFilmes("Açao",
                            filmes.filter { it.id in 3..6 }.toMutableList()
                        )

                        val grupo75 = GrupoFilmes("Açao",
                            filmes.filter { it.id in 6..10 }.toMutableList()
                        )


                        listGrupos.addAll(listOf(grupo1a5, grupo6a10,grupo75))


                        adapterCategoria.notifyDataSetChanged()
                        binding.load.visibility = View.GONE
                        binding.progressbarr.visibility = View.GONE
                        binding.txtxload.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Filmes>>, t: Throwable) {
                Toast.makeText(applicationContext, "Erro ao recuperar dados", Toast.LENGTH_SHORT).show()
            }
        })


    }


}
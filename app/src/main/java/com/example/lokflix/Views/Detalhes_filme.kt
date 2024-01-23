package com.example.lokflix.Views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.lokflix.R
import com.example.lokflix.databinding.ActivityDetalhesFilmeBinding
import com.example.lokflix.databinding.FilmeItemBinding

class Detalhes_filme : AppCompatActivity() {

    lateinit var binding: ActivityDetalhesFilmeBinding

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)


     val capa = intent.extras?.getString("capa")
     val nome = intent.extras?.getString("nome")
     val descricao = intent.extras?.getString("descricao")
     val elenco = intent.extras?.getString("elenco")
        val video= intent.extras?.getString("video")


        val stvideo = video



        Glide.with(this).load(capa).centerCrop().into(binding.capaFilme)
        binding.nomeFilme.setText(nome)
        binding.descricao.setText("Descrição: $descricao")
        binding.elenco.setText("Elenco: $elenco")



       binding.btPlay.setOnClickListener {
           val intent = Intent(this,VideoPlayer::class.java)
           intent.putExtra("video",stvideo)
           startActivity(intent)
       }



    }
}
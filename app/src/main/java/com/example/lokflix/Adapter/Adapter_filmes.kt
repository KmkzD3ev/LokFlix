package com.example.lokflix.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lokflix.Model.Filmes
import com.example.lokflix.R
import com.example.lokflix.Views.Detalhes_filme
import com.example.lokflix.databinding.FilmeItemBinding

class Adapter_filmes(private val context: Context,private val listafilems:MutableList<Filmes>):
    RecyclerView.Adapter<Adapter_filmes.FilmeViewHolder>() {



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
     val item_lista = FilmeItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return FilmeViewHolder(item_lista)
    }

    override fun getItemCount() = listafilems.size


    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
       Log.d("Home-imgs", "URL da imagem: ${listafilems}")
       // Log.d("Adapter_filmes", "URL da imagem: ${listafilems[position].capa}")


        Glide.with(context).load(listafilems[position].capa).centerCrop().error(R.drawable.ic_launcher_background).into(holder.capa)
        holder.capa.setOnClickListener {
            val intent = Intent(context,Detalhes_filme::class.java)
            intent.putExtra("capa",listafilems[position].capa)
            intent.putExtra("nome",listafilems[position].nome)
            intent.putExtra("descricao",listafilems[position].descricao)
            intent.putExtra("elenco",listafilems[position].elenco)
            intent.putExtra("video",listafilems[position].video)
            context.startActivity(intent)
            Log.d("Adapter_filmes", "URL da imagem: ${listafilems[position].capa}")

            Log.d("Adapter_filmes", "Número de filmes: ${listafilems.size}")


        }
    }
    inner class FilmeViewHolder(binding: FilmeItemBinding):RecyclerView.ViewHolder(binding.root){
        val capa = binding.filmecapa


    }



}
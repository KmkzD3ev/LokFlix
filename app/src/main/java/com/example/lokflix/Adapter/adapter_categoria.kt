package com.example.lokflix.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokflix.Model.GrupoFilmes
import com.example.lokflix.databinding.CategoriaItemBinding

class adapter_categoria(private val context: Context, private val listaGrupos: MutableList<GrupoFilmes>) :
    RecyclerView.Adapter<adapter_categoria.GrupoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrupoViewHolder {
        val itemLista = CategoriaItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return GrupoViewHolder(itemLista)
    }

    override fun getItemCount() = listaGrupos.size

    override fun onBindViewHolder(holder: GrupoViewHolder, position: Int) {
        holder.bind(listaGrupos[position])
    }

    inner class GrupoViewHolder(binding: CategoriaItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val titulo = binding.titulos
        private val recycleLista = binding.recyclelista

        fun bind(grupo: GrupoFilmes) {
            titulo.text = grupo.titulo
            recycleLista.adapter = Adapter_filmes(context, grupo.listaFilmes)
            recycleLista.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}

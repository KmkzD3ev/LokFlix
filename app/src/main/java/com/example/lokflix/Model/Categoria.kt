package com.example.lokflix.Model

import android.provider.MediaStore.Video
import com.google.gson.annotations.SerializedName
data class Categoria(
    @SerializedName("titulo") val titulo: String? = null,
    @SerializedName("capas") val listaFilmes: MutableList<Filmes> = mutableListOf()
) {
    fun addFilme(filme: Filmes) {
        listaFilmes.add(filme)
    }
}

data class Filmes(
    @SerializedName("capa") val capa: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("titulo") val titulo: String? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val elenco: String? = null,
    val video: String?= null,
)



data class GrupoFilmes(
    val titulo: String,
    @SerializedName("capas") val listaFilmes: MutableList<Filmes>
)


// Não é mais necessário, você pode remover a classe Categorias
// data class Categorias(
//     @SerializedName("categoria") val categorias: MutableList<Categoria> = mutableListOf()
// )


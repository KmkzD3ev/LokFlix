package com.example.lokflix.Views

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lokflix.R
import com.example.lokflix.databinding.ActivityTelaLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
import com.google.firebase.auth.FirebaseAuth

class Tela_Login : AppCompatActivity() {
    lateinit var binding:ActivityTelaLoginBinding
    private val db = FirebaseAuth.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTelaLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)







        binding.txtCriar.setOnClickListener {view->
            val intent = Intent(this,Tela_Cadastro::class.java)
            startActivity(intent)

        }

        binding.btlogar.setOnClickListener { view ->

                val email = binding.editEmail.text.toString()
                val senha = binding.editSenha.text.toString()

                if (email.isEmpty() && senha.isEmpty() ) {
                    binding.contEmal.boxStrokeColor= Color.parseColor("#FF0000")
                    binding.contSenha.boxStrokeColor=Color.parseColor("#FF0000")
                    binding.contEmal.helperText = "DIGITE UM email"


                }else if (senha.isEmpty()){

                    binding.contEmal.helperText = ""
                    binding.contEmal.boxStrokeColor= Color.parseColor("#FFFFFF")
                    binding.contSenha.boxStrokeColor = Color.parseColor("#FF0000")
                    binding.contSenha.helperText="Diigte umA SENHA"

                }
            else {
                binding.contSenha.boxStrokeColor = Color.parseColor("#FFFFFF")
                    binding.contSenha.helperText = " "
                    autenticar(email,senha)



            }


    }

    }

    private fun autenticar(email:String,senha:String){
        db.signInWithEmailAndPassword(email,senha)
            .addOnCompleteListener {autenticaçao->
                if(autenticaçao.isSuccessful){

                    Toast.makeText(this, "Seja Bem Vindo", Toast.LENGTH_SHORT).show()
                    go_home()
                }else{
                    Toast.makeText(this, "NAO FOI POSSIVEL LOGAR", Toast.LENGTH_SHORT).show()

                }

            }



    }
    fun go_home(){
        val intent = Intent(this,Tela_Home::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {
            val navegarCadastro = Intent(this, Tela_Home::class.java)
            startActivity(navegarCadastro)

        }


    }


}

package com.example.lokflix.Views

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.lokflix.R
import com.example.lokflix.databinding.ActivityTelaCadastroBinding
import com.example.lokflix.databinding.ActivityTelaLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class Tela_Cadastro : AppCompatActivity() {
    lateinit var binding: ActivityTelaCadastroBinding
    private val db = FirebaseAuth.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nomeENTRAR.setOnClickListener {
            val intent = Intent(this,Tela_Login::class.java)
            startActivity(intent)
        }



        binding.btvamos.setOnClickListener { view->
            val email = binding.emailcad.text.toString()

            if (!email.isEmpty()){
                binding.containerSenha.visibility = View.VISIBLE
                binding.btvamos.visibility = View.GONE
                binding.txtTitulo.setText("Um mundo de filmes e series\n ilimitados aguarda por vc")
                binding.txtdescricao.setText("Crie uma conta para saber mais\n sobre o lok flix")
                binding.btcadastrar.visibility = View.VISIBLE
                binding.containerEmail.boxStrokeColor = Color.parseColor("#00FFFF")
                binding.containerEmail.helperText = " "
                binding.contAjuda.visibility = View.VISIBLE

            }else{
                binding.containerEmail.boxStrokeColor= Color.parseColor("#FF6347")
                binding.containerEmail.helperText="O email e obrigatorio"
            }
        }
        binding.btcadastrar.setOnClickListener { view->
            val email = binding.emailcad.text.toString()
            val senha = binding.editSenha.text.toString()

            if (!email.isEmpty() && !senha.isEmpty()){
             cadastrarUser(email,senha)



            }else if(senha.isEmpty()){
                binding.containerEmail.boxStrokeColor= Color.parseColor("#00FFFF")
                binding.containerSenha.helperText="A senha e obrigatoria"
                binding.containerSenha.boxStrokeColor = Color.parseColor("#00FFFF")
            }
            else if(email.isEmpty()){
                binding.containerEmail.boxStrokeColor= Color.parseColor("#FF6347")
                binding.containerEmail.helperText="O email e obrigatorio"
            }
            else if(email.isNotEmpty() && senha.isNotEmpty()) {
                binding.containerEmail.boxStrokeColor= Color.parseColor("#00FFFF")
                binding.containerSenha.boxStrokeColor= Color.parseColor("#00FFFF")

            }



        }


    }

  private  fun cadastrarUser(email:String,senha:String){
        db.createUserWithEmailAndPassword(email,senha)
            .addOnCompleteListener {tarefa->
            if(tarefa.isSuccessful){
                Toast.makeText(this, "seja bem vindo", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Tela_Home::class.java)
                startActivity(intent)

            }

            }.addOnFailureListener { exception ->
                val mensagemErro = when (exception) {
                    is FirebaseAuthWeakPasswordException -> "Digite uma senha com pelo menos 6 caracteres !"
                    is FirebaseAuthInvalidCredentialsException -> " Digite um email Valido !"
                    is FirebaseAuthUserCollisionException -> "Esse email ja foi Cadastrado !"
                    is FirebaseNetworkException -> "Sem Conexao com a Internet !"

                    else -> "Erro ao Cadastrar Usuario"

                }
                Toast.makeText(this, "Error ao cadastrar user ${mensagemErro} ", Toast.LENGTH_SHORT).show()
            }



    }



}
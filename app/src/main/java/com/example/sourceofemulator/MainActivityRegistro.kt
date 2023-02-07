package com.example.sourceofemulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isNotEmpty
import com.example.sourceofemulator.databinding.ActivityMainRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivityRegistro : AppCompatActivity() {
    private lateinit var binding: ActivityMainRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        binding= ActivityMainRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
            setupCreatUser()
        binding.backActivity.setOnClickListener{onBackPressed()}
    }

    private fun setupCreatUser() {
        binding.ButtonHome.setOnClickListener {
        if (binding.EtOneEmail.text.isNotEmpty() && binding.EtPassword.text.isNotEmpty()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.EtOneEmail.text.toString(),binding.EtPassword.text.toString()).addOnCompleteListener{
                if (it.isSuccessful){
                    val intentDialogs=Intent(this,MainActivityDialogs::class.java)
                    startActivity(intentDialogs)
                }else{
                    ShowAlert()
                    Toast.makeText(this,"Llene los campos",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    }
    private fun ShowAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
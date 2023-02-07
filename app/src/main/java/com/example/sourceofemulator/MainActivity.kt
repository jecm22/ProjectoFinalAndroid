


package com.example.sourceofemulator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isNotEmpty
import com.example.sourceofemulator.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        this.supportActionBar?.hide()
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firebaseAnalytics=FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integracion de firebas Completada")
        firebaseAnalytics.logEvent("initScreen",bundle)
        setup()

        binding.btnWindolwReistro.setOnClickListener {
            val intentRegistro=Intent(this,MainActivityRegistro::class.java)
            startActivity(intentRegistro)
        }
        binding.BtnSessionGoogle.setOnClickListener(View.OnClickListener {
            val new= Uri.parse("https://accounts.google.com/v3/signin/identifier?dsh=S-586164834%3A1674980103082626&authuser=0&continue=https%3A%2F%2Fwww.google.com%2Fsearch%3Fq%3Diniciar%2Bsesion%2Ben%2Bgoogle%26rlz%3D1C1CHZN_enMX1042MX1042%26sxsrf%3DAJOqlzWI5vkn53MkGy0bfOFqPeH9Y4J1VQ%253A1674979974566%26ei%3DhirWY5CPIpWjqtsP5OKWyAE%26oq%3Diniciar%2Bsesion%2Ben%2Bgoo%26gs_lcp%3DCgxnd3Mtd2l6LXNlcnAQARgAMggIABCABBDLATIICAAQgAQQywEyCAgAEIAEEMsBMggIABCABBDLATIICAAQgAQQywEyCAgAEIAEEMsBMggIABCABBDLATIICAAQgAQQywEyCAgAEIAEEMsBMggIABCABBDLAToKCAAQRxDWBBCwAzoHCAAQsAMQQ0oECEEYAEoECEYYAFDgAViCDmDxFmgBcAF4AIABcogB3gWSAQMyLjWYAQCgAQHIAQnAAQE%26sclient%3Dgws-wiz-serp&ec=GAlAAQ&hl=en&flowName=GlifWebSignIn&flowEntry=AddSession")
            val i=Intent(Intent.ACTION_VIEW,new)
            startActivity(i)
        })
    }

    private fun setup() {
        binding.ButtonSession.setOnClickListener {
            if (binding.Email.text!!.isNotEmpty() && binding.editPasswprd.text!!.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.Email.text.toString(),
                    binding.editPasswprd.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        newIntentDialogs()
                    } else {
                        ShowAlert()
                        Toast.makeText(this,"No registrado",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun newIntentDialogs(){
        val intentDialogs = Intent(this,MainActivityDialogs::class.java).apply {

        }
        startActivity(intentDialogs)
    }
    private fun ShowAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

}
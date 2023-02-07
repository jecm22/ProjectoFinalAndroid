package com.example.sourceofemulator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.sourceofemulator.databinding.ActivityMainDialogsBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivityDialogs : AppCompatActivity() {
    private lateinit var binding: ActivityMainDialogsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainDialogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backActivity.setOnClickListener{
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Exit -> newBtnSet()
            R.id.herramientas -> herramientasPh()
            R.id.salud -> saludWindow()
            R.id.AcercFunda -> acercaFunda()
        }
        return super.onOptionsItemSelected(item)
    }
   private fun newBtnSet(){
        val new = Intent(this,MainActivity::class.java)
        startActivity(new)
    }
    private fun saludWindow(){
        val cuadroSaludIntent=Intent(this,ActivitySalud::class.java)
        startActivity(cuadroSaludIntent)
    }
    private fun acercaFunda(){
        val acercaContac=Intent(this,ActivityContacAcercaFunda::class.java)
        startActivity(acercaContac)
    }
    private fun herramientasPh(){
        val herramientasIntent=Intent(this,ActivityHerramientas::class.java)
        startActivity(herramientasIntent)
    }

}
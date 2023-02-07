package com.example.sourceofemulator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sourceofemulator.databinding.ActivityContacAcercaFundaBinding

class ActivityContacAcercaFunda : AppCompatActivity() {
    private lateinit var binding:ActivityContacAcercaFundaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContacAcercaFundaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backActivity.setOnClickListener{onBackPressed()}
        binding.btnFacebook.setOnClickListener(View.OnClickListener {
            val new= Uri.parse("https://es-la.facebook.com/FUNDACIONSOFFYUnaManoAmigaAC/")
            val i= Intent(Intent.ACTION_VIEW,new)
            startActivity(i)
        })
        binding.btnInstagram.setOnClickListener(View.OnClickListener {
            val instagram= Uri.parse("https://www.instagram.com/fundacionsoffy/?hl=en")
            val insta= Intent(Intent.ACTION_VIEW,instagram)
            startActivity(insta)
        })
        binding.btnTwiter.setOnClickListener(View.OnClickListener {
            val twiter= Uri.parse("https://twitter.com/fundacionsoffy?lang=en")
            val twi= Intent(Intent.ACTION_VIEW,twiter)
            startActivity(twi)
        })
        binding.urlSetOnclick.setOnClickListener {
            val paginaWeb= Uri.parse("https://fundacionsoffy.org/")
            val pagSoffy = Intent(Intent.ACTION_VIEW,paginaWeb)
            startActivity(pagSoffy)
        }
        binding.fundacion.setOnClickListener {
            val googleMaps = Uri.parse("https://www.google.com/maps/place/Fundaci%C3%B3n+Soffy,+una+mano+amiga+A.C./@13.6604267,-100.348952,4z/data=!4m6!3m5!1s0x85d35a4fc142e2b9:0xf310a124c1700fef!8m2!3d20.6483617!4d-100.4040386!16s%2Fg%2F11c6lx3j6g")
            val localizacion =Intent(Intent.ACTION_VIEW,googleMaps)
            startActivity(localizacion)
        }
    }
}
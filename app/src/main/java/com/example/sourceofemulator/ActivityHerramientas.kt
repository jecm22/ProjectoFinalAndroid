package com.example.sourceofemulator

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sourceofemulator.databinding.ActivityHerramientasBinding
import com.example.sourceofemulator.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices

class ActivityHerramientas : AppCompatActivity() {

    private lateinit var binding: ActivityHerramientasBinding
    val requesPermissionCamera = 100
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val CODIGO_PERMISOS_UBICACION_SEGUNDO_PLANO = 2106
    private val LOG_TAG = "EnviarUbicacion"
    private var haConcedidoPermisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHerramientasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backActivity.setOnClickListener{
            onBackPressed()
        }

        binding.btnTwoHerramientasDos.setOnClickListener{
            solicitarPermiso()
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        verificarPermisos()






        binding.btnNewHerramienta.setOnClickListener {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    val sendIntent = Intent(Intent.ACTION_SEND)
                    sendIntent.type = "text/plain"
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Mi ubicación: Latitud: ${location?.latitude}, Longitud: ${location?.longitude}"
                    )
                    startActivity(Intent.createChooser(sendIntent, "Enviar mensaje"))

                }


        }


    }


    //////


    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
        private const val TAG = "MainActivity"
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CODIGO_PERMISOS_UBICACION_SEGUNDO_PLANO) {
            val todosLosPermisosConcedidos =
                grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (grantResults.isNotEmpty() && todosLosPermisosConcedidos) {
                haConcedidoPermisos = true;


                Log.d(LOG_TAG, "El usuario concedió todos los permisos")
            } else {
                Log.d(LOG_TAG, "Uno o más permisos fueron denegados")
            }
        }
    }


    private fun solicitarPermisos(permisos: Array<String>) {
        Log.d(LOG_TAG, "Solicitando permisos...")
        requestPermissions(
            permisos,
            CODIGO_PERMISOS_UBICACION_SEGUNDO_PLANO
        )

    }

    private fun verificarPermisos() {
        val permisos = arrayListOf(
           android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
        // Segundo plano para Android Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permisos.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        val permisosComoArray = permisos.toTypedArray()
        if (tienePermisos(permisosComoArray)) {
            haConcedidoPermisos = true

            Log.d(LOG_TAG, "Los permisos ya fueron concedidos")
        } else {
            solicitarPermisos(permisosComoArray)
        }
    }

    private fun tienePermisos(permisos: Array<String>): Boolean {
        return permisos.all {
            return ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
    fun solicitarPermiso(){
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 0)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),requesPermissionCamera)
        }
    }

}
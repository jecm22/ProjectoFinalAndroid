package com.example.sourceofemulator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    val titles = arrayOf("Beber suficiente agua: Es esencial mantener un buen flujo de orina para ayudar a los riñones a eliminar las toxinas del cuerpo. Asegúrate de beber al menos 8 vasos de agua al día para mantenerte hidratado","Controlar el consumo de sal: Una dieta rica en sodio puede aumentar la presión arterial y poner una carga extra en los riñones. Trata de reducir tu ingesta de sal y evita alimentos procesados que son ricos en sodio.","Mantener un peso saludable: El sobrepeso y la obesidad pueden aumentar el riesgo de enfermedad renal. Mantener un peso saludable a través de una dieta equilibrada y ejercicio regular puede ayudar a prevenir problemas renales.")
    override fun onCreateViewHolder(viewGroup: ViewGroup, it: Int): ViewHolder {
        val inflate = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, it: Int) {
        viewHolder.text.text = titles[it]
    }

    override fun getItemCount(): Int {
        return titles.size
    }


    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var text :TextView
        init {
            text = itemView.findViewById(R.id.TvNew)
        }

    }
}
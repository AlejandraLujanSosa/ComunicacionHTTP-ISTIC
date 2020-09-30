package com.example.comunicacionhttp_istic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.comunicacionhttp_istic.ConsultaDatos.Companion.consultarDatos
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnVerificar = findViewById<Button>(R.id.btnVerificar)
        //val btnConsultaSimple=findViewById<Button>(  R.id.btnConsultaSimple)
        val btnIrListado = findViewById<Button>(R.id.btnIrListado)

        btnIrListado.setOnClickListener {
            val intento1 = Intent(this, ListadoPaises::class.java)
            startActivity(intento1)
        }
        btnVerificar.setOnClickListener {
            if (ControlDeConexion.hayConexion(this)) {
                Toast.makeText(this, "Estamos conectados", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Sin Conexión", Toast.LENGTH_SHORT).show()
            }
        }
        btnConsultaSimple.setOnClickListener {
            val datos = consultarDatos("https://restcountries.eu/rest/v2/all")
            // http://jsonviewer.stack.hu/
            /*
            https://restcountries.eu/rest/v2/
            https://restcountries.eu/rest/v2/region/europe
            https://restcountries.eu/rest/v2/name/aruba?fullText=true*/
            Log.d("ConsultaSimple", datos)

            val datosArrayJson = JSONArray(datos)

            for (i in 0..datosArrayJson.length() - 1) {
                var pais = datosArrayJson.getJSONObject(i)
                // Log.d(pais.getString("name") ,pais.toString(0) )
                Log.d("pais", pais.getString("first_name"))
            }

        }

    }


    override fun onStart() {
        super.onStart()
        if (ControlDeConexion.hayConexion(this)) {
            Toast.makeText(this, "Estamos conectados", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Sin Conexión", Toast.LENGTH_SHORT).show()
        }

        btnClima.setOnClickListener {

            val intent: Intent = Intent(this, Clima::class.java)
            startActivity(intent)
            finish()
        }
        btnPeli.setOnClickListener {

            val intent: Intent = Intent(this, Peliculas::class.java)
            startActivity(intent)
            finish()
        }
    }
}






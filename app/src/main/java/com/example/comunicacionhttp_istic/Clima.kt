@file:Suppress("DEPRECATION")

package com.example.comunicacionhttp_istic

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class Clima : AppCompatActivity() {
    var ciudadNombre: TextView? = null
    var searchButton: Button? = null
    var result: TextView? = null


    internal class Weather : AsyncTask<String?, Void?, String?>() {
        override fun doInBackground(vararg address: String?): String? {
            try {

                //TODO("Not yet implemented")
                val url = URL(address[0])
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()

                //recuperar datos de la URL
                val `is` = connection.inputStream
                val isr = InputStreamReader(`is`)
                var data = isr.read()
                var content = ""
                var ch: Char

                while (data != -1) {
                    ch = data.toChar()
                    content = content + ch
                    data = isr.read()
                }
                Log.i("Content", content)
                return content
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }
    }

    fun search(view: View?) {
        ciudadNombre = findViewById(R.id.ciudadNombre)
        searchButton = findViewById(R.id.searchButton)
        result = findViewById(R.id.resut)

        //if ciudadNombre != null &&
        //println(ciudadNombre?. null)
        val cName = ciudadNombre?.getText().toString()


        var content: String
        val weather = Weather()

        try {
            content = weather.execute("https://api.openweathermap.org/data/2.5/weather?q=$cName&APPID=aaff9e289109aae6450855e21019bcb6")
                    .get()!!
            Log.i("ContentData", content)


            //JSON
            val jsonObject = JSONObject(content)
            val weatherData = jsonObject.getString("weather")
            val mainTemperature = jsonObject.getString("main")
            var visibility: Double

            Log.i("WeatherData", weatherData)

            val array = JSONArray(weatherData)

            var main = ""
            var description = ""
            var temperature = ""

            for (i in 0 until array.length()) {
                val weatherPart = array.getJSONObject(i)
                main = weatherPart.getString("main")
                description = weatherPart.getString("description")

            }
            val mainPart = JSONObject(mainTemperature)
            temperature = mainPart.getString("temp")

            visibility = jsonObject.getString("visibility").toDouble()
            val visibilityInKilometer = visibility.toInt() / 1000

            Log.i("temperature", temperature)
            Log.i("main",main);
            Log.i("description",description);

            val resultTextView = """
                Main : $main 
                Description : $description 
                Temperature : ${temperature}C
                Visibility : ${visibilityInKilometer}K""".trimIndent()
            result?.setText(resultTextView)


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)
    }
}











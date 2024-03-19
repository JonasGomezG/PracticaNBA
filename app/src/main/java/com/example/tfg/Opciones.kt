package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Opciones : AppCompatActivity() {

    lateinit var btn_modificarUser : Button
    lateinit var btn_estadisticas : Button
    lateinit var btn_ranking : Button
    lateinit var btn_juego : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        btn_modificarUser = findViewById(R.id.btn_modificarUser)
        btn_estadisticas = findViewById(R.id.btn_estadisticas)
        btn_ranking = findViewById(R.id.btn_ranking)
        btn_juego = findViewById(R.id.btn_juego)

        btn_estadisticas.setOnClickListener {
            val intent = Intent(this, Estadisticas::class.java)
            startActivity(intent)
        }

        btn_ranking.setOnClickListener {
            val intent = Intent(this, Ranking::class.java)
            startActivity(intent)
        }

        btn_modificarUser.setOnClickListener {
            val intent = Intent(this, ViewUser::class.java)
            startActivity(intent)
        }

    }
}
package com.example.tfg

import android.R.id.home
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Registro : AppCompatActivity() {

    lateinit var newEmail : EditText
    lateinit var newPasswd : EditText
    lateinit var newPasswdAgain : EditText
    lateinit var crearAcc : Button
    lateinit var irAInicio : Button
    lateinit var reglasPass : Button
    lateinit var reglas : ConstraintLayout
    lateinit var cerrarReglas : Button
    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        newEmail = findViewById(R.id.et_newEmail)
        newPasswd = findViewById(R.id.et_newPasswd)
        newPasswdAgain = findViewById(R.id.et_newPasswdAgain)
        crearAcc = findViewById(R.id.btn_crearCuenta)
        irAInicio = findViewById(R.id.btn_IrAInicio)
        reglasPass = findViewById(R.id.btn_reglasPass)
        reglas = findViewById(R.id.tv_reglas)
        cerrarReglas = findViewById(R.id.btn_cerrarReglas)

        reglas.isVisible = false
        mAuth = FirebaseAuth.getInstance()
        
        reglasPass.setOnClickListener {
            reglas.isVisible = true
        }

        cerrarReglas.setOnClickListener {
            reglas.isVisible = false
        }

        irAInicio.setOnClickListener {
            inicioView()
        }


        crearAcc.setOnClickListener {
            val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).{6,}$")
            val regexEmail = Regex("^[a-z]+@[A-Za-z0-9]+\\.com\$")

            if ( !regex.matches( newPasswd.text.toString() ) ) {
                Toast.makeText(this@Registro,"La contraseña no cumple las reglas",Toast.LENGTH_SHORT).show()
            } else if ( !regexEmail.matches( newEmail.text.toString() ) ) {
                Toast.makeText(this@Registro,"El correo tiene un formato no valido, debe acabar en '.com'",Toast.LENGTH_SHORT).show()
            } else if (newPasswd.text.toString().equals(newPasswdAgain.text.toString())) {
                createAccount(newEmail.text.toString(), newPasswd.text.toString())
            } else {
                Toast.makeText(this@Registro, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful()) {
                Log.d(TAG, "createUserWithEmail:success")
                Toast.makeText(this@Registro, "Usuario creado correctamente.", Toast.LENGTH_SHORT)
                    .show()
                val user: FirebaseUser? = mAuth.currentUser
                user?.let { updateUI(it) }
            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(this@Registro, "Error al crear el usuario.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun inicioView (){
        val intent = Intent(this, InicioSesion::class.java)
        startActivity(intent)
    }

    private fun updateUI(user: FirebaseUser)
    {
        var user: FirebaseUser? = user
        user = mAuth.currentUser
        if (user != null) {
            inicioView()
        }
    }

}

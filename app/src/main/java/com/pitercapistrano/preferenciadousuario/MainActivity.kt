package com.pitercapistrano.preferenciadousuario

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.pitercapistrano.preferenciadousuario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var cor =""

    companion object{
        const val ARQUIVO_PREFERENCIAS = "ArquivoPreferencias"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.cor1.setOnClickListener {
            cor = "#ff33b5e5"
            salvar(cor)
        }

        binding.cor2.setOnClickListener {
            cor = "#ff99cc00"
            salvar(cor)
        }

        binding.cor3.setOnClickListener {
            cor = "#ffcc0000"
            salvar(cor)
        }

        binding.cor4.setOnClickListener {
            cor = "#ffff8800"
            salvar(cor)
        }

        binding.cor5.setOnClickListener {
            cor = "#ffaa66cc"
            salvar(cor)
        }

    }

    private fun salvar(cor: String){
        binding.layoutPrincipal.setBackgroundColor(Color.parseColor(cor))

        binding.btTrocarCor.setOnClickListener {view ->
            val preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
            val editor = preferencias.edit()
            editor.putString("cor", cor)
            editor.apply()

            snackbar(view)
        }
    }

    private fun snackbar(view: View){
        val snackbar = Snackbar.make(view, "Cor de fundo alterada com sucesso!", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK"){

        }
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setBackgroundTint(Color.WHITE)
        snackbar.setTextColor(Color.BLACK)
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()

        val preferecias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
        val cor = preferecias.getString("cor", "")

        if (cor!!.isNotEmpty()){
            binding.layoutPrincipal.setBackgroundColor(Color.parseColor(cor))
        }
    }
}
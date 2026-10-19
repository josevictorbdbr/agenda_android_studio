package com.example.upcampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetalheEvento : AppCompatActivity() {
    private var eventoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_evento)

        eventoId = intent.getIntExtra("evento_id", -1)

        findViewById<Button>(R.id.btnExcluir).setOnClickListener {
            BancoHelper(this).excluir(eventoId)
            Toast.makeText(this, "Evento excluído", Toast.LENGTH_SHORT).show()
            finish()
        }

        findViewById<Button>(R.id.btnEditar).setOnClickListener {
            val intent = Intent(this, CadastroEvento::class.java)
            intent.putExtra("evento_id", eventoId)
            startActivity(intent)
        }

        val banco = BancoHelper(this)
        var evento = banco.buscarPorId(eventoId)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarDetalhe)
        btnVoltar.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        val evento = BancoHelper(this).buscarPorId(eventoId)
        if (evento != null) {
            findViewById<TextView>(R.id.tvDetalheNome).text = evento.nome
            findViewById<TextView>(R.id.tvDetalheInfo).text = "${evento.data} • ${evento.local}"
            findViewById<TextView>(R.id.tvDetalheDescricao).text = evento.descricao
        }
    }
}
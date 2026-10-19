package com.example.upcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CadastroEvento : AppCompatActivity() {
    private var eventoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_evento)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etData = findViewById<EditText>(R.id.etData)
        val etLocal = findViewById<EditText>(R.id.etLocal)
        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        eventoId = intent.getIntExtra("evento_id", -1)
        if (eventoId != -1) {
            val evento = BancoHelper(this).buscarPorId(eventoId)
            if (evento != null) {
                etNome.setText(evento.nome)
                etData.setText(evento.data)
                etLocal.setText(evento.local)
                etDescricao.setText(evento.descricao)
                btnSalvar.text = "ATUALIZAR"
            }
        }

        btnSalvar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            if (nome.isEmpty()) {
                etNome.error = "Informe o nome do evento"
                return@setOnClickListener
            }

            val banco = BancoHelper(this)

            if (eventoId == -1) {
                val evento = Evento(
                    nome = nome,
                    data = etData.text.toString().trim(),
                    local = etLocal.text.toString().trim(),
                    descricao = etDescricao.text.toString().trim()
                )
                banco.inserir(evento)
                Toast.makeText(this, "Evento salvo", Toast.LENGTH_SHORT).show()
            } else {
                val evento = Evento(
                    id = eventoId,
                    nome = nome,
                    data = etData.text.toString().trim(),
                    local = etLocal.text.toString().trim(),
                    descricao = etDescricao.text.toString().trim()
                )
                banco.atualizar(evento)
                Toast.makeText(this, "Evento atualizado", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
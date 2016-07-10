package com.example.thiago.app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastraCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_cliente);
    }
    
    //IDENTIFICAÇÃO BANCO
    final String NomeBanco = "Cliente";
    SQLiteDatabase BancoDados = null;

    public void CriaBanco () {


        try {
            //ABRE OU CRIA BANCO DE DADOS
            BancoDados = openOrCreateDatabase(NomeBanco, MODE_PRIVATE, null);

            //INSTRUÇÃO SQL DA ESTRUTURA TABELA
            //CAMPO ID PRIMARIO
            //CAMPO NOME PARA NOME DO CLIENTE
            String sQuery = "CREATE TABLE IF NOT EXISTS cliente (_id integer PRIMARY KEY, nome TEXT)";

            //EXECUTA COMANDO
            BancoDados.execSQL(sQuery);

        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"Erro ao criar banco de dados",Toast.LENGTH_SHORT).show();

        } finally {
            BancoDados.close();
        }

    }

    public void cadastrar(View v){

        EditText strNome = (EditText) findViewById(R.id.entradaNomeCliente);
        Cliente cliente = new Cliente(strNome.getText().toString());

        if (cliente.getNome().equals("")) {
            Toast.makeText(getApplicationContext(),"Preencher nome",Toast.LENGTH_SHORT).show();

        } else {
            CriaBanco();

            //ABRE BANCO
            BancoDados = openOrCreateDatabase(NomeBanco, MODE_PRIVATE, null);

            //CRIA OBJETO PARA ARMAZENAR VALORES DE ENTRADA
            ContentValues campos = new ContentValues();

            //CRIA CAMPOS E ASSOCIA COM CONTEUDO
            campos.put("nome",cliente.getNome());

            //INSERE NO BANCO DE DADOS
            BancoDados.insert("cliente", null, campos);

            //MOSTRA MENSAGEM
            Toast.makeText(getApplicationContext(),"Cliente "+cliente.getNome() +" Inserido",Toast.LENGTH_SHORT).show();

            BancoDados.close();
        }

    }

}

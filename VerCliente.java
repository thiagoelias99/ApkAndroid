package com.example.thiago.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VerCliente extends Activity {

    int id = 0;
    //ArrayList<HashMap<String,String>> clientes = new ArrayList<HashMap<String,String>>();


    //IDENTIFICAÇÃO BANCO
    final String NomeBanco = "Cliente";
    SQLiteDatabase BancoDados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente);

        //LISTA DE STRING
        //String[] testes = new String[]{"Teste 1","Teste 3", "Teste 4", "Teste 5"};

        //LISTA HASH
        //ArrayList<HashMap<String,String>> clientes = new ArrayList<HashMap<String,String>>();

        /*
        for (int i = 0;i < 10;i++){
            HashMap<String,String> cliente = new HashMap<String, String>();
            cliente.put("id","ID "+(i+1));
            cliente.put("nome","Nome "+(i+1));

            clientes.add(cliente);
        }


        //CHAVES HASH
        String[] arrayChaves = new String[]{"id","nome"};

        //LAYOUT
        int layout = android.R.layout.two_line_list_item;

        //REFERENCIAS
        int[] arrayRef = new int[]{android.R.id.text1, android.R.id.text2};

        //ADAPTADOR DA HASH
        SimpleAdapter adapter = new SimpleAdapter(this, clientes, layout, arrayChaves, arrayRef);
        */

        //ADAPTADOR DA STRING
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, testes);

        // CONSULTAR
        BancoDados = openOrCreateDatabase(NomeBanco, MODE_PRIVATE, null);

        String[] colunas = new String[]{"_id","nome"};
        Cursor cursor = BancoDados.query("cliente", colunas, null, null,null,null,null);

        //Cursor c = BancoDados.rawQuery("select * from aluno where nome like '"+nome+"%'", null);

        if (cursor.getCount()>0){ //VERIFICA SE ENCONTROU RESULTADOS

            ArrayList<String> clientes = new ArrayList<String>();

            cursor.moveToFirst(); //POSICIONA NO PRIMEIRO

            do {

                clientes.add(cursor.getString(1));

            }while (cursor.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item,clientes);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // Obtém item
                    String nome = ((TextView) view).getText().toString();
                    // mostra item e parâmetros
                    Toast.makeText(getApplication(), "Selecionado: "+ nome, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Registro não encontrado!", Toast.LENGTH_SHORT).show();
            BancoDados.close();
        }


    }
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
}


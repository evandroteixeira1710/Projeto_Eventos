package com.senai.projeto_eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.projeto_eventos.R;
import com.senai.projeto_eventos.modelo.Eventos;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;

    private boolean edicao = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");

        carregarEvento();

    }
    private void carregarEvento(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null){
            Eventos eventos = (Eventos) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_date);
            EditText editTextLocal = findViewById(R.id.editTextTextPostalAddress);
            editTextNome.setText(eventos.getNome());
            editTextData.setText(String.valueOf(eventos.getData()));
            editTextLocal.setText(eventos.getLocal());
            edicao = true;
            id = eventos.getId();
        }

    }
    public void onClickVoltar(View v){
        finish();
    }
    public void onClickExcluir(View v){
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_date);
        EditText editTextLocal = findViewById(R.id.editTextTextPostalAddress);
        editTextData.setText(String.valueOf(" "));
        editTextNome.setText(String.valueOf(" "));
        editTextLocal.setText(String.valueOf(" "));

        Eventos eventos = new Eventos(id,"Evento Excluido","", "");
        Intent intent = new Intent();
        intent.putExtra("eventoEditado", eventos);
        setResult(RESULT_CODE_PRODUTO_EDITADO, intent);
        finish();

    }
    public void onClickSalvar(View v){
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_date);
        EditText editTextLocal = findViewById(R.id.editTextTextPostalAddress);


        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();


        Eventos eventos = new Eventos(id,nome, data, local);
        Intent intent = new Intent();

        if (edicao){
            intent.putExtra("eventoEditado", eventos);
            setResult(RESULT_CODE_PRODUTO_EDITADO, intent);

        }else {
            intent.putExtra("novoEvento", eventos);
            setResult(RESULT_CODE_NOVO_PRODUTO, intent);
            }
        if(nome.equals("") || data.equals("") || local.equals("")) {
            Toast.makeText(CadastroEventoActivity.this, "Existem campos Vazios", Toast.LENGTH_LONG).show();
        }else{
            finish();
        }


    }
}
package com.example.aluno.aula2803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class FormularioTimes extends AppCompatActivity {
    EditText editNome;
    EditText editCidade;
    Time time = new Time();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_times);
        editNome = (EditText) findViewById(R.id.editNome);
        editCidade = (EditText) findViewById(R.id.editCidade);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null && bundle.containsKey("id")){
            time = new
                    DAOTimes(this).buscarId(bundle.getLong("id"));
            editNome.setText(time.getNome());
            editCidade.setText(time.getCidade());
        }
    }

    public void salvar(View view){

        time.setNome(editNome.getText().toString());
        time.setCidade(editCidade.getText().toString());
        DAOTimes dao = new DAOTimes(this);
        if(time.getId()==0){
            dao.inserir(time);
        }else{
            dao.alterar(time);
        }

        Log.i("Formulario","Qtd"+dao.buscarTodos().size());
        finish();
    }
}

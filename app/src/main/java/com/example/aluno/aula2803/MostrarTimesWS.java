package com.example.aluno.aula2803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MostrarTimesWS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_times_ws);
        BuscarTimesWS buscarTimesWS = new BuscarTimesWS(MostrarTimesWS.this);
        buscarTimesWS.execute("http://200.17.98.122:8080/hellows/rest/service/listaTimesSP","","");
    }
}

package com.example.aluno.aula2803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ApresentarImagem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentar_imagem);

        BuscarImagem buscarImagem = new BuscarImagem(ApresentarImagem.this);
        buscarImagem.execute("https://www.torcedores.com/content/uploads/2016/06/palmeiras1.jpg");
    }
}

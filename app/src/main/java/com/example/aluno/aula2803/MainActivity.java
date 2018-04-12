package com.example.aluno.aula2803;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private ListView listViewTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTimes = (ListView) findViewById(R.id.listTimes);

        //String times[] = {"Flamengo", "ACP", "Internacional", "Avai"};

        //ArrayAdapter<String> adapterTimes =
        //        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,times);


        //listViewTimes.setAdapter(adapterTimes);

        listViewTimes.setOnItemClickListener(this);
        listViewTimes.setOnItemLongClickListener(this);
    }

    public void chamarTelaImagem(View view){
        Intent intent = new Intent(this, ApresentarImagem.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        preencherLista();
    }

    public void preencherLista(){
        List<Time> listaTimes = new ArrayList<>();
        listaTimes  = new DAOTimes(this).buscarTodos();
        AdapterTimes adapter = new AdapterTimes(this, listaTimes);
        listViewTimes.setAdapter(adapter);
    }

    public void chamarFormulario(View view){
        Intent intent = new Intent(this,FormularioTimes.class);
        startActivity(intent);
    }

    public void timesWS(View view){
        Intent intent = new Intent(this,MostrarTimesWS.class);
        startActivity(intent);
    }

    public void gravarSP(View view){
        //Buscar as preferencias desta Activity (MainActivity)
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);

        //Buscar as preferências de desta/outras Activity - Passar o nome
        //SharedPreferences sp =
         //       this.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)

        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();

        List<Time> times = new ArrayList<>();
        times.add(new Time(1L,"ACP","Paranavaí",0));
        times.add(new Time(2L,"Galo","Maringá",0));

        String timesJson = gson.toJson(times);
        Log.i("MainActivity",timesJson);

        editor.putString("times",timesJson);
        editor.putString("carro","Fiesta");
        //editor.pu
        editor.commit();
    }

    public void recuperarSP(View view){
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        String carro = sp.getString("carro","não tem fiesta");

        String timesJson = sp.getString("times","sem times");
        Gson gson = new Gson();
        List<Time> listaTimes = gson.fromJson(timesJson,
                new TypeToken<List<Time>>(){}.getType());
        Log.i("MainActivity",String.valueOf(listaTimes.size()));

        Log.i("MainActivity",carro);
    }

    public void chamarSegundaTela(View view){
        Intent intent = new
                Intent(this, SegundaTela.class);
        intent.putExtra("timeCampeao","Palmeiras");
        intent.putExtra("titulosBrasileiro",10);
        startActivity(intent);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("MainActivity", parent.getItemAtPosition(position).toString());

        Time time = (Time) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, FormularioTimes.class);
        intent.putExtra("id",time.getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("MainActivity", parent.getItemAtPosition(position).toString());
        final Time time = (Time) parent.getItemAtPosition(position);
        final DAOTimes dao = new DAOTimes(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja Excluir o Time??")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dao.remover(time);
                        preencherLista();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        builder.show();
        return true;
    }
}

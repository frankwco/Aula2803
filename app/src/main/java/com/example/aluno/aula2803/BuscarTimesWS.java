package com.example.aluno.aula2803;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by aluno on 11/04/18.
 */

public class BuscarTimesWS extends AsyncTask<String,Integer,String> {

    Activity activity;
    ProgressBar progressBar;

    public BuscarTimesWS(Activity activity){
        this.activity = activity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar = (ProgressBar) activity.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson = new Gson();
        List<Time> times = gson.fromJson(s, new TypeToken<List<Time>>(){}.getType());
        AdapterTimes adapterTimes = new AdapterTimes(activity, times);
        ListView listView = (ListView) activity.findViewById(R.id.listViewTimesWS);
        listView.setAdapter(adapterTimes);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(String... strings) {

        String jsonRetorno=null;
        try {
            URL url = new URL(strings[0]);
            InputStream stream = url.openStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            onProgressUpdate(10);
            String linha;
            while ((linha = bufferedReader.readLine())!=null){
                builder.append(linha);
            }
            jsonRetorno = builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonRetorno;
    }
}

package com.example.aluno.aula2803;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 09/04/18.
 */

public class DAOTimes {
    SQLiteDatabase db;

    public DAOTimes(Context context){
        db = new BDCore(context).getWritableDatabase();
    }

    public void inserir(Time time){
        ContentValues values = new ContentValues();
        values.put("nome", time.getNome());
        values.put("cidade", time.getCidade());
        db.insert("times", null, values);
    }
    public void alterar(Time time){
        ContentValues values = new ContentValues();
        values.put("nome", time.getNome());
        values.put("cidade", time.getCidade());
        //db.update("times", values,"id=?",
        //        new String[]{String.valueOf(time.getId())});
        db.update("times", values,"id="+time.getId(),null);
    }

    public void remover(Time time){
        db.delete("times","id="+time.getId(),null);
    }

    public List<Time> buscarTodos(){
        List<Time> times = new ArrayList<>();
        String[] colunas = {"id","nome","cidade"};
        Cursor cursor = db.query("times", colunas,null,
                null, null, null, null);
        cursor.moveToFirst();
        for(int x=0; x<cursor.getCount();x++){
            Time time = new Time();
            time.setId(cursor.getLong(0));
            time.setNome(cursor.getString(1));
            time.setCidade(cursor.getString(2));
            times.add(time);
            cursor.moveToNext();
        }

        return times;
    }

    public Time buscarId(Long id){
        String[] colunas = {"id","nome","cidade"};
        Cursor cursor = db.query("times", colunas,"id="+id,
                null, null, null, null);
        cursor.moveToFirst();
        Time time = new Time();
        if(cursor.getCount()>0) {
            time.setId(cursor.getLong(0));
            time.setNome(cursor.getString(1));
            time.setCidade(cursor.getString(2));
        }
        return time;
    }


}

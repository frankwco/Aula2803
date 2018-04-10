package com.example.aluno.aula2803;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aluno on 09/04/18.
 */

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME="aula";
    private static final int VERSAO=1;

    public BDCore(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table times(id integer " +
                "primary key autoincrement, nome varchar(100), " +
                "cidade varchar(100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table times;");
        onCreate(db);
    }
}

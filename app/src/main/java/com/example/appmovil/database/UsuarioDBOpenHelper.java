package com.example.appmovil.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDBOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "usuarios.db";
    public static int VERSION = 1;
    public static final String TAG = "OpenHelper";

    //NO ES BUENA IDEA GUARDAR LAS CONTRASEÑAS COMO PLAIN TEXT
    //PERO PARA ESTE EJEMPLO DE PRUEBA SIRVE PARA SIMPLIFICAR
    String CREATE_TABLE_USUARIOS = "CREATE TABLE IF NOT EXISTS usuarios (email TEXT PRIMARY KEY, password TEXT )";

    public UsuarioDBOpenHelper(@Nullable Context context) {

        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USUARIOS);
        Log.i(TAG, "Se creo la tabla de usuarios");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {

    }

    public void registrarUsuario(SQLiteDatabase sqLiteDatabase, String email, String password) {
        //QUE ES UNA INYECCION SQL?
        String INSERT_USUARIO = String.format("INSERT INTO usuarios (email, password) VALUES ('%s', '%s')", email, password);
        sqLiteDatabase.execSQL(INSERT_USUARIO);
        Log.i(TAG, "Se inserto un usuario a la DB");
        return;
    }

    public List<String> getUsuarioEmail(SQLiteDatabase sqLiteDatabase, String email) {
        String table = "usuarios";
        String[] columns = {"email", "password"};
        String selection = "email =?";
        String[] selectionArgs =  {email};
        Cursor cursor = sqLiteDatabase.query(table,columns, selection, selectionArgs, null, null, null);

        List<String> res = new ArrayList<String>();

        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0 ) {

            String resEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String resPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));

            res.add(0, resEmail);
            res.add(1, resPassword);

            return res;
        }

        res.add(0, null);
        res.add(1, null);

        return res;
    }
}

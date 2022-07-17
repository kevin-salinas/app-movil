package com.example.appmovil.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class UsuarioDataSource {
    public static final String TAG = "DataSource";
    UsuarioDBOpenHelper dbHelper;
    SQLiteDatabase database;

    public UsuarioDataSource(Context context) {
        dbHelper = new UsuarioDBOpenHelper(context);
    }

    public void openDB() {
        database = dbHelper.getReadableDatabase();
        Log.i(TAG, "Database abierta");
    }

    public void closeDB() {
        dbHelper.close();
        Log.i(TAG, "Database cerrada");
    }

    public Boolean esEmailValido(String email) {
        List<String> usuario = dbHelper.getUsuarioEmail(database, email);

        if (usuario.get(0) != null) {
            return true;
        }

        return false;
    }

    //NO ES BUENA IDEA GUARDAR LAS CONTRASEÑAS COMO PLAIN TEXT
    //PERO PARA ESTE EJEMPLO DE PRUEBA SIRVE PARA SIMPLIFICAR
    public Boolean esLoginValido(String email, String password) {
        List<String> usuario = dbHelper.getUsuarioEmail(database, email);

        //Log.i(TAG, String.format("USUARIO: %s, %s", email, password));
        //Log.i(TAG, String.format("RES: %s, %s", usuario.get(0), usuario.get(1)));

        if ((email.equals(usuario.get(0))) && (password.equals(usuario.get(1)))) {
            return true;
        }
        return false;
    }

    public void registrarUsuario(String email, String password) {
        dbHelper.registrarUsuario(database, email, password);
        return;
    }
}

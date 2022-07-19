package com.example.appmovil.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

public class MascotasDataSource {

    public static final String TAG = "DataSource";
    MascotasDBOpenHelper dbHelper;
    SQLiteDatabase database;

    public MascotasDataSource(Context context) {
        dbHelper = new MascotasDBOpenHelper(context);
    }

    public void openDB() {
        database = dbHelper.getReadableDatabase();
        Log.i(TAG, "Database abierta");
    }

    public void closeDB() {
        dbHelper.close();
        Log.i(TAG, "Database cerrada");
    }

    public void registrarMascota(String nombre, Integer edad, String animal, String alimento) {
        dbHelper.registrarMascota(database, nombre, edad, animal, alimento);
        return;
    }

    public List<String> getMascotaByNombre(String nombre) {
        return dbHelper.getMascotaByNombre(database, nombre);
    }
}

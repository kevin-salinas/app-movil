package com.example.appmovil.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MascotasDBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mascotas.db";
    public static int VERSION = 1;
    public static final String TAG = "OpenHelper";

    String CREATE_TABLE_MASCOTAS = "CREATE TABLE IF NOT EXISTS mascotas (id INTEGER PRIMARY KEY, nombre TEXT, edad INT, animal TEXT, alimento TEXT )";

    public MascotasDBOpenHelper(@Nullable Context context) {

        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MASCOTAS);
        Log.i(TAG, "Se creo la tabla de mascotas");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {

    }

    public void registrarMascota(SQLiteDatabase sqLiteDatabase, String nombre, Integer edad, String animal, String alimento) {
        //QUE ES UNA INYECCION SQL?
        String INSERT_MASCOTA = String.format("INSERT INTO mascotas (nombre, edad, animal, alimento) VALUES ('%s', '%d', '%s', '%s')", nombre, edad, animal, alimento);
        sqLiteDatabase.execSQL(INSERT_MASCOTA);
        Log.i(TAG, "Se inserto un mascota a la DB");
        return;
    }

    public List<String> getMascotaByNombre(SQLiteDatabase sqLiteDatabase, String nombre) {
        String table = "mascotas";
        String[] columns = {"nombre", "edad", "animal", "alimento"};
        String selection = "nombre =?";
        String[] selectionArgs =  {nombre};
        Cursor cursor = sqLiteDatabase.query(table,columns, selection, selectionArgs, null, null, null);

        List<String> res = new ArrayList<String>();

        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0 ) {

            String resNombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String resEdad = cursor.getString(cursor.getColumnIndexOrThrow("edad"));
            String resAnimal = cursor.getString(cursor.getColumnIndexOrThrow("animal"));
            String resAlimento = cursor.getString(cursor.getColumnIndexOrThrow("alimento"));

            res.add(0, resNombre);
            res.add(1, resEdad);
            res.add(2, resAnimal);
            res.add(3, resAlimento);

            return res;
        }

        res.add(0, null);
        res.add(1, null);
        res.add(2, null);
        res.add(3, null);

        return res;
    }
}

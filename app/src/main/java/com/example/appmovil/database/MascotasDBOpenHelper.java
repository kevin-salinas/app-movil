package com.example.appmovil.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appmovil.Mascota;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MascotasDBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mascotas.db";
    public static int VERSION = 1;
    public static final String TAG = "OpenHelper";

    String CREATE_TABLE_MASCOTAS = "CREATE TABLE IF NOT EXISTS mascotas (id INTEGER PRIMARY KEY, nombre TEXT, edad INT, animal TEXT, alimento TEXT, foto BLOB )";

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

    public void registrarMascota(SQLiteDatabase sqLiteDatabase, Mascota mascota) {
       SQLiteStatement statement = sqLiteDatabase.compileStatement(
                "INSERT INTO mascotas (nombre, edad, animal, alimento, foto) VALUES (?, ?, ?, ?, ?)");

       statement.bindString(1, mascota.nombre);
       statement.bindLong(2, mascota.edad);
       statement.bindString(3, mascota.animal);
       statement.bindString(4, mascota.alimento);
       statement.bindBlob(5, mascota.foto);
       statement.execute();

       Log.i(TAG, "Se inserto un mascota a la DB");
    }

    private Mascota getMascotaFromCursor(Cursor cursor) {
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
        int edad = (int) cursor.getLong(cursor.getColumnIndexOrThrow("edad"));
        String animal = cursor.getString(cursor.getColumnIndexOrThrow("animal"));
        String alimento = cursor.getString(cursor.getColumnIndexOrThrow("alimento"));
        byte[] foto = cursor.getBlob(cursor.getColumnIndexOrThrow("foto"));

        return new Mascota(nombre, edad, animal, alimento, foto);
    }

    public Mascota getMascotaByNombre(SQLiteDatabase sqLiteDatabase, String nombre) {
        String table = "mascotas";
        String[] columns = {"nombre", "edad", "animal", "alimento", "foto"};
        String selection = "nombre =?";
        String[] selectionArgs = {nombre};
        Cursor cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return getMascotaFromCursor(cursor);
        }

        Objects.requireNonNull(cursor).close();
        return null;
    }

    public List<Mascota> getAllMascotas(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM mascotas",null);
        List<Mascota> mascotas = new ArrayList<Mascota>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                mascotas.add(getMascotaFromCursor(cursor));
                cursor.moveToNext();
            }
        }

        cursor.close();
        return mascotas;
    }
}

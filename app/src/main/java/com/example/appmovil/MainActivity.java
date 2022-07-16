package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true; //
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.uno:
                Toast.makeText(MainActivity.this,"Agenda",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.dos:
                Toast.makeText(MainActivity.this,"video",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.tres:
                Toast.makeText(MainActivity.this,"ajustes",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.cuatro:
                Toast.makeText(MainActivity.this,"Perfil",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.cinco:
                Toast.makeText(MainActivity.this,"Fotos",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.seis:
                Toast.makeText(MainActivity.this,"Salir",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Siguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Siguiente = (Button)findViewById(R.id.Siguiente);

        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
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

            case R.id.cuatro:
                Toast.makeText(MainActivity.this,"Perfil",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.cinco:
                Toast.makeText(MainActivity.this,"Fotos",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
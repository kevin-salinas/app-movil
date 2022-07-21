package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarMascota extends AppCompatActivity {

    Button Irmascota;
    Button Aceptar;
    EditText nombre, edad, animal, alimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);

        nombre = (EditText)findViewById(R.id.editNombre);
        edad = (EditText)findViewById(R.id.editEdad);
        animal = (EditText)findViewById(R.id.editAnimal);
        alimento  = (EditText)findViewById(R.id.editAlimento);
        Irmascota = (Button)findViewById(R.id.regresar);
        Aceptar = (Button)findViewById(R.id.btnAceptar);

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nombre.getText().toString();
                String age = edad.getText().toString();
                String anima = animal.getText().toString();
                String food = alimento.getText().toString();
                Intent i = new Intent(RegistrarMascota.this, perfil.class);
                i.putExtra("name", name);
                i.putExtra("age", age);
                i.putExtra("anima", anima);
                i.putExtra("food", food);
                startActivity(i);
            }
        });

        Irmascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrarMascota.this, MainActivity.class);
                startActivity(i);
            }

        });
    }
}
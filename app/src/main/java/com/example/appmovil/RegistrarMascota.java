package com.example.appmovil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.appmovil.database.MascotasDataSource;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class RegistrarMascota extends AppCompatActivity {
    public static final int TOMA_FOTO = 1;

    Button Aceptar, TomarFoto;
    EditText nombre, edad, animal, alimento;
    ImageView fotoMascotaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        nombre = (EditText) findViewById(R.id.editNombre);
        edad = (EditText) findViewById(R.id.editEdad);
        animal = (EditText) findViewById(R.id.editAnimal);
        alimento = (EditText) findViewById(R.id.editAlimento);
        Aceptar = (Button) findViewById(R.id.btnAceptar);
        TomarFoto = (Button) findViewById(R.id.btnTomarFoto);
        fotoMascotaImageView = findViewById(R.id.fotoMascotaImageView);

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

        TomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TOMA_FOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TOMA_FOTO && resultCode == RESULT_OK) {
            Bundle extras = Objects.requireNonNull(data).getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            fotoMascotaImageView.setImageBitmap(bitmap);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] byteArray = bos.toByteArray();

            // TODO: incorporar en formulario.
            MascotasDataSource mascotasDataSource = new MascotasDataSource(this);
            mascotasDataSource.openDB();
            mascotasDataSource.registrarMascota(new Mascota("Bobby", 2, "Perro", "Dog Chow", byteArray));
        }
    }
}
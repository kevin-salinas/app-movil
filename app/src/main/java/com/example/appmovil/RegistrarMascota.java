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

    Button aceptarButton, tomarFotoButton;
    EditText nombreEditText, edadEditText, animalEditText, alimentoEditText;
    ImageView fotoMascotaImageView;
    byte[] foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        nombreEditText = findViewById(R.id.editNombre);
        edadEditText = findViewById(R.id.editEdad);
        animalEditText = findViewById(R.id.editAnimal);
        alimentoEditText = findViewById(R.id.editAlimento);
        aceptarButton = findViewById(R.id.btnAceptar);
        tomarFotoButton = findViewById(R.id.btnTomarFoto);
        fotoMascotaImageView = findViewById(R.id.fotoMascotaImageView);

        aceptarButton.setOnClickListener(this::onAceptar);
        tomarFotoButton.setOnClickListener(this::onTomarFoto);
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
            foto = bos.toByteArray();
        }
    }

    public void onAceptar(View view) {
        String nombre = nombreEditText.getText().toString();
        int edad = Integer.parseInt(edadEditText.getText().toString());
        String animal = animalEditText.getText().toString();
        String alimento = alimentoEditText.getText().toString();

        MascotasDataSource mascotasDataSource = new MascotasDataSource(this);
        mascotasDataSource.openDB();
        mascotasDataSource.registrarMascota(new Mascota(nombre, edad, animal, alimento, foto));

        Intent i = new Intent(RegistrarMascota.this, perfil.class);
        i.putExtra("nombre", nombre);
        i.putExtra("edad", edad);
        i.putExtra("animal", animal);
        i.putExtra("alimento", alimento);
        i.putExtra("foto", foto);

        startActivity(i);
    }

    public void onTomarFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TOMA_FOTO);
    }
}
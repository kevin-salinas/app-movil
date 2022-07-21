package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class perfil extends AppCompatActivity {
    TextView tvNombre, tvEdad, tvAnimal, tvAlimento;
    ImageView ivFoto;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        tvNombre = (TextView)findViewById(R.id.tvNombre);
        tvEdad = (TextView)findViewById(R.id.tvEdad);
        tvAnimal = (TextView)findViewById(R.id.tvAnimal);
        tvAlimento = (TextView)findViewById(R.id.tvAlimento);
        ivFoto = (ImageView)findViewById(R.id.ivFoto);
        btnOk = (Button)findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(perfil.this, RegistrarMascota.class);
                startActivity(i);
            }
        });
        MostrarRegistros();
    }

    private void MostrarRegistros(){
        Bundle datos = this.getIntent().getExtras();
        String nombre = datos.getString("nombre");
        int edad = datos.getInt("edad");
        String animal = datos.getString("animal");
        String alimento = datos.getString("alimento");
        byte[] foto = datos.getByteArray("foto");
        Bitmap fotoBitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);

        tvNombre.setText(nombre);
        tvEdad.setText(String.valueOf(edad));
        tvAnimal.setText(animal);
        tvAlimento.setText(alimento);
        ivFoto.setImageBitmap(fotoBitmap);
    }
}
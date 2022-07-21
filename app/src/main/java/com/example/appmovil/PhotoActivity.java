package com.example.appmovil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmovil.database.MascotasDataSource;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class PhotoActivity extends AppCompatActivity {
    public static final int TAKES_PHOTO = 1;
    ImageView petPhotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        petPhotoImageView = findViewById(R.id.petPhotoImageView);
    }

    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKES_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKES_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = Objects.requireNonNull(data).getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            petPhotoImageView.setImageBitmap(bitmap);

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
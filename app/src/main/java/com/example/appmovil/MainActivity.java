package com.example.appmovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    Button Siguiente;
    String[] pets;
    RecyclerView petsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Siguiente = (Button) findViewById(R.id.Siguiente);

        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });

        pets = new String[] {"Bobby", "Lucho"};  // TODO: obtener de la base de datos.
        petsRecyclerView = findViewById(R.id.petsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        petsRecyclerView.setLayoutManager(linearLayoutManager);
        petsRecyclerView.setAdapter(new PetsAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true; //
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.uno:
                Toast.makeText(MainActivity.this, "Agenda", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.dos:
                Toast.makeText(MainActivity.this, "video", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.cuatro:
                Toast.makeText(MainActivity.this, "Perfil", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.cinco:
                Intent i = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.PetsAdapterHolder> {
        @NonNull
        @Override
        public PetsAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PetsAdapterHolder(getLayoutInflater().inflate(R.layout.layout_pet, parent, false));
        }

        @Override
        public int getItemCount() {
            return pets.length;
        }

        @Override
        public void onBindViewHolder(@NonNull PetsAdapterHolder holder, int position) {
            holder.print(position);
        }

        class PetsAdapterHolder extends RecyclerView.ViewHolder {
            ImageView photoImageView;
            TextView nameTextView;
            TextView ageAndTypeTextView;
            TextView foodTextView;

            public PetsAdapterHolder(@NonNull View view) {
                super(view);
                photoImageView = view.findViewById(R.id.petPhotoCardImageView);
                nameTextView = view.findViewById(R.id.petNameTextView);
                ageAndTypeTextView = view.findViewById(R.id.petAgeAndTypeTextView);
                foodTextView = view.findViewById(R.id.petFoodTextView);
            }

            // TODO: usar datos de la mascota.
            public void print(int position) {
                photoImageView.setImageDrawable(getResources().getDrawable(R.drawable.perfil));
                nameTextView.setText(pets[position]);
                ageAndTypeTextView.setText("Perro, " + pets[position].length() + " años");
                foodTextView.setText("Alimento");
            }
        }
    }
}
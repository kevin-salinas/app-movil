package com.example.appmovil;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Locale;

public class Mascota {
    public final String nombre;
    public final int edad;
    public final String animal;
    public final String alimento;
    public final byte[] foto;

    public Mascota(String nombre, int edad, String animal, String alimento, byte[] foto) {
        this.nombre = nombre;
        this.edad = edad;
        this.animal = animal;
        this.alimento = alimento;
        this.foto = foto;
    }

    @NonNull
    public String toString() {
        return String.format(Locale.getDefault(), "Mascota(%s, %d, %s, %s, %s)",
                nombre, edad, animal, alimento, Arrays.toString(foto));
    }
}

package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmovil.database.UsuarioDataSource;

public class Login extends AppCompatActivity {

    UsuarioDataSource dataSource;

    EditText eEmail;
    EditText ePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new UsuarioDataSource(this);

        eEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        ePassword = (EditText) findViewById(R.id.editTextTextPassword);
    }

    public void BotonLogin(View view) {
        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        dataSource.openDB();

        if ( !( dataSource.esEmailValido(email)) ) {
            Toast.makeText(Login.this,"USUARIO Y CONTRASEÑA INCORRECTA",Toast.LENGTH_SHORT).show();
            dataSource.closeDB();
            return;
        }

        if( !( dataSource.esLoginValido(email, password)) ) {
            Toast.makeText(Login.this,"CONTRASEÑA INCORRECTA",Toast.LENGTH_SHORT).show();
            dataSource.closeDB();
            return;
        }

        //TODO: HACER ALGO SI EL LOGIN ESTA BIEN
        Toast.makeText(Login.this,"TA BIEN",Toast.LENGTH_SHORT).show();
        dataSource.closeDB();

        return;

    }

    public void BotonRegistrar(View view) {
        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        dataSource.openDB();

        if ( ( dataSource.esEmailValido(email)) ) {
            Toast.makeText(Login.this,"USUARIO YA EXISTENTE",Toast.LENGTH_SHORT).show();
            dataSource.closeDB();
            return;
        }

        dataSource.registrarUsuario(email, password);
        dataSource.closeDB();
        Toast.makeText(Login.this,"USUARIO REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
        return;
    }
}
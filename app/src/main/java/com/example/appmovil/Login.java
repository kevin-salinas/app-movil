package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmovil.database.UsuarioDataSource;

public class Login extends AppCompatActivity {

    UsuarioDataSource dataSource;

    EditText eEmail;
    EditText ePassword;
    Button Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new UsuarioDataSource(this);

        eEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        ePassword = (EditText) findViewById(R.id.editTextTextPassword);
        Ingresar = (Button)findViewById(R.id.buttonLogin);

    }

    public void BotonLogin(View view) {
        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        if (email.equals("")) {
            Toast.makeText(Login.this,"EL E-MAIL NO PUEDE ESTAR VACIO",Toast.LENGTH_SHORT).show();
            return;
        }

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        if(!(m.matches())) {
            Toast.makeText(Login.this,"EL E-MAIL ES INVALIDO",Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals("") || password.length() < 5) {
            Toast.makeText(Login.this,"LA CONTRASEÑA DEBE TENER UN MINIMO DE 5 CARACTERES",Toast.LENGTH_SHORT).show();
            return;
        }

        dataSource.openDB();

        if ( !( dataSource.esEmailValido(email)) ) {
            Toast.makeText(Login.this,"E-MAIL Y CONTRASEÑA INCORRECTA",Toast.LENGTH_SHORT).show();
            dataSource.closeDB();
            return;
        }

        if( !( dataSource.esLoginValido(email, password)) ) {
            Toast.makeText(Login.this,"CONTRASEÑA INCORRECTA",Toast.LENGTH_SHORT).show();
            dataSource.closeDB();
            return;
        }

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });

        //TODO: HACER ALGO SI EL LOGIN ESTA BIEN
        Toast.makeText(Login.this,"INGRESO CORRECTO",Toast.LENGTH_SHORT).show();
        dataSource.closeDB();
        return;

    }

    public void BotonRegistrar(View view) {
        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        if (email.equals("")) {
            Toast.makeText(Login.this,"EL E-MAIL NO PUEDE ESTAR VACIO",Toast.LENGTH_SHORT).show();
            return;
        }

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        if(!(m.matches())) {
            Toast.makeText(Login.this,"EL E-MAIL ES INVALIDO",Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals("") || password.length() < 5) {
            Toast.makeText(Login.this,"LA CONTRASEÑA DEBE TENER UN MINIMO DE 5 CARACTERES",Toast.LENGTH_SHORT).show();
            return;
        }

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
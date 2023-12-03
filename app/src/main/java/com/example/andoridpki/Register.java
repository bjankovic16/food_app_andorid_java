package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerUser(View view) {
        String ime = String.valueOf(((EditText) findViewById(R.id.ime)).getText());
        String prezime = String.valueOf(((EditText) findViewById(R.id.prezime)).getText());
        String telefon = String.valueOf(((EditText) findViewById(R.id.telefon)).getText());
        String adresa = String.valueOf(((EditText) findViewById(R.id.adresa)).getText());
        String korisnickoIme = String.valueOf(((EditText) findViewById(R.id.korisnickoIme)).getText());
        String lozinka = String.valueOf(((EditText) findViewById(R.id.lozinka)).getText());

        if(ime.equals("") || prezime.equals("") || telefon.equals("") || adresa.equals("") ||
                korisnickoIme.equals("") || lozinka.equals("")){
            Toast.makeText(Register.this, "Nisu uneti svi podaci!", Toast.LENGTH_SHORT).show();
        }

        User user = new User(ime, prezime, telefon, adresa, korisnickoIme, lozinka);
    }
}
package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    public void Registration(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void loginUser(View view) {
        String korisnickoIme = String.valueOf(((EditText) findViewById(R.id.loginIme)).getText());
        String lozinka = String.valueOf(((EditText) findViewById(R.id.loginLozinka)).getText());
        if (korisnickoIme.equals("") || lozinka.equals("")) {
            Toast.makeText(Login.this, "Nisu uneti svi podaci!", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<User> users = Common.getAllUsers(this, "users.json");
            for (User u : users) {
                if (u.getKorisnickoIme().equals(korisnickoIme)) {
                    // to anotgher page ...
                    return;
                }
            }
        }
        Toast.makeText(this, "Nevalidni podaci!", Toast.LENGTH_SHORT).show();
    }
}
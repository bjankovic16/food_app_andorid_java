package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private void putInCache(User user){
        String json = Common.makeJsonFromObject(user);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", json);
        editor.apply();
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
                    Intent intent = new Intent(this, Index.class);
                    startActivity(intent);
                    putInCache(u);
                    return;
                }
            }
        }
        Toast.makeText(this, "Nevalidni podaci!", Toast.LENGTH_SHORT).show();
    }
}
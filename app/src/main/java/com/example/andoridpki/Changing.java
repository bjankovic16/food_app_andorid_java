package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Changing extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_changing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String json = preferences.getString("user", "");
        user = Common.makeUserFromJson(json);
        setText();
    }

    protected void onPause() {
        super.onPause();
        putInCache(user);
    }

    private void putInCache(User user){
        String json = Common.makeJsonFromObject(user);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", json);
        editor.apply();
    }
    private void setText(){
        EditText ime = findViewById(R.id.ime);
        ime.setText(user.getIme());
        EditText prezime = findViewById(R.id.prezime);
        prezime.setText(user.getPrezime());
        EditText adresa = findViewById(R.id.adresa);
        adresa.setText(user.getAdresa());
        EditText telefon = findViewById(R.id.telefon);
        telefon.setText(user.getTelefon());
        EditText korisnickoIme = findViewById(R.id.korisnickoIme);
        korisnickoIme.setText(user.getKorisnickoIme());
        EditText lozinka = findViewById(R.id.lozinka);
        lozinka.setText(user.getLozinka());
    }

    public void changeData(View view){
        String ime = String.valueOf(((EditText) findViewById(R.id.ime)).getText());
        String prezime = String.valueOf(((EditText) findViewById(R.id.prezime)).getText());
        String telefon = String.valueOf(((EditText) findViewById(R.id.telefon)).getText());
        String adresa = String.valueOf(((EditText) findViewById(R.id.adresa)).getText());
        String lozinka = String.valueOf(((EditText) findViewById(R.id.lozinka)).getText());
        String korisnickoIme = String.valueOf(((EditText) findViewById(R.id.korisnickoIme)).getText());

        if (ime.equals("") || prezime.equals("") || telefon.equals("") || adresa.equals("") || lozinka.equals("")){
            Toast.makeText(Changing.this, "Nisu uneti svi podaci!", Toast.LENGTH_SHORT).show();
        }else {
            ArrayList<User> users = Common.getAllUsers(this,"users.json");
            for(int i = 0; i < users.size(); i++){
                if (users.get(i).getKorisnickoIme().equals(korisnickoIme)) {
                    Toast.makeText(Changing.this, "Uspesno promenjeni podaci!", Toast.LENGTH_SHORT).show();
                    user = new User(ime, prezime, telefon, adresa, korisnickoIme, lozinka);
                    users.set(i, user);
                    Common.writeIntoFile(this, "users.json", users);
                    return;
                }
            }
        }
    }
}
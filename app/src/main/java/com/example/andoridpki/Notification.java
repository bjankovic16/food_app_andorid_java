package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        ArrayList<Integer> bills = retrieveBillsFromCache(user);
        setData(bills);
    }

    private void setData(ArrayList<Integer> bills) {
        LinearLayout billsContainer = findViewById(R.id.billsContainer);
        for (int i = 0; i < bills.size(); i++) {
            int bill = bills.get(i);
            View billView = LayoutInflater.from(this).inflate(R.layout.one_bill, null);

            TextView textProduct = billView.findViewById(R.id.textPorudzbina);
            textProduct.setText("Porudžbina broj: " + (i + 1));

            TextView textCena = billView.findViewById(R.id.textCena);
            textCena.setText("Cena: " + bill + " din");

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.setMargins(40, 30, 40, 30);
            billView.setLayoutParams(layoutParams);
            billsContainer.addView(billView);
        }
        if (bills.size() == 0){
            TextView textNema = findViewById(R.id.textNema);
            textNema.setText("Trenutno nema porudžbina.");
        }
    }

    private ArrayList<Integer> retrieveBillsFromCache(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsBills", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("bills for " + user.getKorisnickoIme(), "[]");
        return Common.makeBillsFromJson(json);
    }

}
package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initProducts();
    }

    public void Registration(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void Login(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void initProducts(){
        if(Common.doesFileExist(this, "cakes.json")){
            return;
        }
        ArrayList<Product> cookies = new ArrayList<>();
        ArrayList<Product> cakes = new ArrayList<>();

        Product product = new Product("Palačinke sa voćnim prelivom",
                "Palačinke sa voćnim prelivom savršeni izbor za sve ljubitelje palačinki.",
                "Jaja, brašno, med, mleko, jagode, borovnice, voćni preliv.",
                300, "palacinka_vocna.png");

        product.writeComment("Odličan ukus svima preporucujem. Pozdrav Maja!");
        product.writeComment("Cena pristupačna. Odličan ukus. Preporučujem.");
        product.writeComment("Mnoooogo slatko");
        cookies.add(product);

        product = new Product("King", "King palačinka samo za prave kraljeve.",
                "Jaja, brašno, med, mleko, jagode, šlag, borovnice, čokoladni preliv preliv.",
                500, "king.png");
        cookies.add(product);

        product = new Product("Medođija", "Palačinke medođija kao da su je pčele pravile.",
                "Jaja, brašno, med, mleko, voćni preliv.",
                600, "medodjija.png");
        cookies.add(product);

        product = new Product("Čoko bomba", "Samo za prave ljubitelje čokolade.",
                "Jaja, brašno, čokolada, malina, šećer, med.",
                750, "bombonice_kolac.png");
        cookies.add(product);

        product = new Product("Čokoooos", "Samo za prave ljubitelje istančanog ukusa.",
                "Jaja, brašno, čokolada, griz, šećer.",
                650, "cokoladni_kolac.png");
        cookies.add(product);

        product = new Product("Vanilice", "Samo za ljubitelje dobrih i proverenih stvari.",
                "Jaja, brašno, vanila, griz, šećer, jaja.",
                450, "vanilice_kolac.png");
        cookies.add(product);

        // cakes

        product = new Product("Rozalija", "Samo za ljubitelje novih ukusa.",
                "Jaja, brašno, vanila, griz, šećer, jaja, čokolada, šlag, piškote.",
                3000, "rozalija_torta.png");
        cakes.add(product);

        product = new Product("Čokolino", "Ovo morate probati, najnoviji ukus.",
                "Jaja, brašno, vanila, griz, šećer, jaja, čokolada, šlag, piškote, malina, nana, plazma.",
                5000, "cokolino_torta.png");
        cakes.add(product);

        product = new Product("Borovica", "Ukus borovnice sa kupinama na vrhu sigurno neće razočarati.",
                "Jaja, brašno, vanila, griz, šećer, jaja, čokolada, šlag, piškote, kupine, borovnice, plazma.",
                4000, "borovnica_torta.png");
        cakes.add(product);

        product = new Product("Voćni specijal", "Pravo osveženje u vrelim danima.",
                "Jaja, brašno, vanila šećer, jaja, čokolada, šlag, piškote, kupine, borovnice, jagode, maline, plazma.",
                4900, "vocni_torta.png");
        cakes.add(product);

        product = new Product("Čiz kejk", "Nešto novooooo.",
                "Jaja, brašno, vanila šećer, jaja, čokolada, šlag, piškote, plazma.",
                7000, "ciz_torta.png");
        cakes.add(product);

        product = new Product("Rođendanska", "Najbolje za rođendan.",
                "Jaja, brašno, vanila šećer, jaja, čokolada, šlag, piškote, plazma.",
                10000, "rodjendanska_torta.png");
        cakes.add(product);

        Common.writeIntoFile(this, "cakes.json", cakes);
        Common.writeIntoFile(this, "cookies.json", cookies);
    }
}
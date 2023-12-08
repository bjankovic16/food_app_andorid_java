package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Index extends AppCompatActivity {

    private ImageView imageView;
    private int[] imageArray = {R.drawable.promocija2, R.drawable.promocija3, R.drawable.promocija4, R.drawable.promocija1};
    private int currentIndex = 0;
    private Handler handler;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.promocija);
        handler = new Handler();
        startImageSwitching();
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String json = preferences.getString("user", "");
        user = Common.makeUserFromJson(json);
    }

    private void startImageSwitching() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showNextImage();
                handler.postDelayed(this, 2000);
            }
        }, 2000);
    }

    private void showNextImage() {
        imageView.setImageResource(imageArray[currentIndex]);
        currentIndex = (currentIndex + 1) % imageArray.length;
    }

    public void info(View view) {
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }

    public void change(View view) {
        Intent intent = new Intent(this, Changing.class);
        startActivity(intent);
    }

    public void clickCake(View view) {
        Intent intent = new Intent(this, Products_List.class);
        intent.putExtra("showing","cakes.json");
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void clickCookie(View view) {
        Intent intent = new Intent(this, Products_List.class);
        intent.putExtra("showing","cookies.json");
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
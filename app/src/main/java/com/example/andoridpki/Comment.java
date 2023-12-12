package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Comment extends AppCompatActivity {
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("selected");
        addComments(product);
    }

    private void addComments(Product product) {
        LinearLayout layout = findViewById(R.id.commentsContainer);

        TextView textView = new TextView(this);
        textView.setText(product.getNaziv());
        textView.setGravity(Gravity.CENTER);
        Typeface customFont = ResourcesCompat.getFont(this, R.font.island_moments);
        textView.setTypeface(customFont);
        textView.setTextSize(40);
        textView.setPadding(0, 30, 0, 0);
        layout.addView(textView);

        for (String comment : product.getKomentari()) { //komenatri iz fajla
            addComment(comment);
        }

        for (String comment : retrieveStringListFromCache()) {
            addComment(comment);
        }
    }

    public void komentarisi(View view) {
        String comment = ((EditText) findViewById(R.id.inputComment)).getText().toString();
        addComment(comment);
        // adding to cache
        ArrayList<String> comments = retrieveStringListFromCache();
        comments.add(comment);
        saveStringListToCache(comments);
    }

    private void addComment(String comment) {
        TextView textView = new TextView(this);
        textView.setText(comment);
        textView.setPadding(20, 30, 0, 0);
        textView.setTypeface(ResourcesCompat.getFont(this, R.font.island_moments));
        textView.setTextSize(30);
        LinearLayout layout = findViewById(R.id.commentsContainer);
        layout.addView(textView);
        ((EditText) findViewById(R.id.inputComment)).setText("");
    }

    private void saveStringListToCache(ArrayList<String> stringList) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsComments", Context.MODE_PRIVATE);
        String json = Common.makeJsonFromObject(stringList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("comments for " + product.getNaziv(), json);
        editor.apply();
    }

    private ArrayList<String> retrieveStringListFromCache() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsComments", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("comments for " + product.getNaziv(), "[]");
        return Common.makeCommentsFromJson(json);
    }
}
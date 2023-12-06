package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        textView.setPadding(0,30, 0,0);
        layout.addView(textView);

        for (String comment: product.getKomentari()) { //komenatri iz fajla
            addComment(comment);
        }


    }
    public void komentarisi(View view){
        String comment = ((EditText)findViewById(R.id.inputComment)).getText().toString();
        addComment(comment);
    }

    private void addComment(String comment){
        TextView textView = new TextView(this);
        textView.setText(comment);
        textView.setPadding(20,30,0,0);
        textView.setTypeface(ResourcesCompat.getFont(this, R.font.island_moments));
        textView.setTextSize(30);
        LinearLayout layout = findViewById(R.id.commentsContainer);
        layout.addView(textView);
        ((EditText)findViewById(R.id.inputComment)).setText("");
    }

}
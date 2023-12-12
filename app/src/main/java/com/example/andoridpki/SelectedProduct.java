package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectedProduct extends AppCompatActivity {

    private Product product;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_product);
        getSupportActionBar().hide();
        setData();
    }

    private void setData() {
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("selected");
        user = (User) intent.getSerializableExtra("user");
        ImageView image = findViewById(R.id.productId);
        image.setImageResource(getResources().getIdentifier(product.getSlika().substring(0, product.getSlika().length() - 4), "drawable", getPackageName()));
        TextView text = findViewById(R.id.multiLineTextView);
        text.setText("Opis:\n" + product.getOpis() + "\n" + "Sastojci:\n" + product.getSastojci() + "\n" + "Cena: " + product.getCena());
    }

    public void naruci(View v) {
        Intent intent = new Intent(this, Ordering.class);
        intent.putExtra("selected", product);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void komentarisi(View v) {
        Intent intent = new Intent(this, Comment.class);
        intent.putExtra("selected", product);
        startActivity(intent);
    }

}
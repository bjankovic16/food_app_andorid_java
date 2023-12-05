package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Products_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String displayString = intent.getStringExtra("showing");

        LinearLayout imageContainer = findViewById(R.id.imageContainer);

        /*int[] imageResources = {R.drawable.image1, R.drawable.image2, R.drawable.image3};

        for (int i = 0; i < imageResources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(imageResources[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageContainer.addView(imageView);
        }
*/
    }
}
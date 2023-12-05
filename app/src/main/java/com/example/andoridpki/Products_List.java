package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class Products_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        getSupportActionBar().hide();
        getContent();
    }

    private void getContent(){
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("showing");

        LinearLayout imageContainer = findViewById(R.id.imageContainer);
        ArrayList<Product> products = Common.getAllProducts(this, fileName);

        ArrayList<Integer> imageResources = new ArrayList<>();
        for(Product product: products){
            imageResources.add(getResources().getIdentifier(product.getSlika().substring(0,product.getSlika().length()-4), "drawable", getPackageName()));
        }

        for (int i = 0; i < imageResources.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(imageResources.get(i));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(20, 20, 20, 20);
            imageView.setAdjustViewBounds(true);
            imageContainer.addView(imageView);
        }
    }
}
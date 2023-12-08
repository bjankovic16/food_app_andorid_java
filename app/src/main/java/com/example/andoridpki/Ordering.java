package com.example.andoridpki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Ordering extends AppCompatActivity {

    private Product product;
    private User user;
    private ArrayList<Order> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("selected");
        user = (User) intent.getSerializableExtra("user");
        orders = retrieveStringListFromCache(user);
        orders.add(new Order(product,1));
        setData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveOrdersListToCache(orders, user);
    }

    private void setData(){
        LinearLayout ordersContainer = findViewById(R.id.ordersContainer);
        for (int i = 0; i < orders.size(); i++) {
            View orderView = LayoutInflater.from(this).inflate(R.layout.one_order, null);
            ImageView productPicture = orderView.findViewById(R.id.productPicture);
            int picLen = orders.get(i).getProduct().getSlika().length();
            String pictureName = orders.get(i).getProduct().getSlika().substring(0, picLen-4);
            productPicture.setImageResource(getResources().getIdentifier(pictureName, "drawable", getPackageName()));

            TextView textProduct = orderView.findViewById(R.id.textProduct);
            textProduct.setText(orders.get(i).getProduct().getNaziv());

            TextView textCena = orderView.findViewById(R.id.textCena);
            textCena.setText(String.valueOf(orders.get(i).getProduct().getCena()));

            EditText count = orderView.findViewById(R.id.count);
            count.setText(String.valueOf(orders.get(i).getAmount()));

            ordersContainer.addView(orderView);
        }
    }

    private void saveOrdersListToCache(ArrayList<Order> ordersList, User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsOrders", Context.MODE_PRIVATE);
        String json = Common.makeJsonFromObject(ordersList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("orders for "+user.getKorisnickoIme(), json);
        editor.apply();
    }

    private ArrayList<Order> retrieveStringListFromCache(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsOrders", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("orders for "+user.getKorisnickoIme(), "[]");
        return Common.makeOrdersFromJson(json);
    }

    public void order(View view) {

    }
}
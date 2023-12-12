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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ordering extends AppCompatActivity {

    private Product product;
    private User user;
    private ArrayList<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("selected");
        user = (User) intent.getSerializableExtra("user");
        orders = retrieveStringListFromCache(user);
        if (product != null) {
            orders.add(new Order(product, 1));
        }
        setData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveOrdersListToCache(orders, user);
    }

    private void setData() {
        LinearLayout ordersContainer = findViewById(R.id.ordersContainer);
        int bill = 0;
        for (int i = 0; i < orders.size(); i++) {
            View orderView = LayoutInflater.from(this).inflate(R.layout.one_order, null);
            ImageView productPicture = orderView.findViewById(R.id.productPicture);
            int picLen = orders.get(i).getProduct().getSlika().length();
            String pictureName = orders.get(i).getProduct().getSlika().substring(0, picLen - 4);
            productPicture.setImageResource(getResources().getIdentifier(pictureName, "drawable", getPackageName()));

            TextView textProduct = orderView.findViewById(R.id.textProduct);
            textProduct.setText(orders.get(i).getProduct().getNaziv());

            TextView textCena = orderView.findViewById(R.id.textCena);
            textCena.setText("Cena: " + orders.get(i).getProduct().getCena() + " din");

            EditText count = orderView.findViewById(R.id.count);
            count.setText(String.valueOf(orders.get(i).getAmount()));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10, 25, 10, 25);
            orderView.setLayoutParams(layoutParams);
            bill += orders.get(i).getAmount() * orders.get(i).getProduct().getCena();
            ordersContainer.addView(orderView);
        }
        TextView textCena = findViewById(R.id.textPrice);
        if (bill != 0) {
            textCena.setText("Cena ukupno: " + bill + " din");
        } else {
            textCena.setText("Nema ničega u korpi.");
        }
    }

    private int getPrice(String price) {
        String str = price.substring(13, price.length() - 4);
        return Integer.parseInt(str);
    }

    private void saveOrdersListToCache(ArrayList<Order> ordersList, User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsOrders", Context.MODE_PRIVATE);
        String json = Common.makeJsonFromObject(ordersList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("orders for " + user.getKorisnickoIme(), json);
        editor.apply();
    }

    private ArrayList<Order> retrieveStringListFromCache(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsOrders", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("orders for " + user.getKorisnickoIme(), "[]");
        return Common.makeOrdersFromJson(json);
    }

    private void saveUserBillsToCache(ArrayList<Integer> bills, User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsBills", Context.MODE_PRIVATE);
        String json = Common.makeJsonFromObject(bills);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bills for " + user.getKorisnickoIme(), json);
        editor.apply();
    }

    private ArrayList<Integer> retrieveBillsFromCache(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsBills", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("bills for " + user.getKorisnickoIme(), "[]");
        return Common.makeBillsFromJson(json);
    }

    public void plus(View view) {
        LinearLayout linearLayout = findViewById(R.id.ordersContainer);
        View parent1 = (View) view.getParent();
        View parent2 = (View) parent1.getParent();
        int index = linearLayout.indexOfChild((View) parent2.getParent());

        RelativeLayout child = (RelativeLayout) linearLayout.getChildAt(index);
        LinearLayout child1 = child.findViewById(R.id.par1);
        LinearLayout child2 = child1.findViewById(R.id.par2);

        EditText count = child2.findViewById(R.id.count);
        int curVal = Integer.parseInt(String.valueOf(count.getText()));
        count.setText(String.valueOf(curVal + 1));
        orders.get(index).setAmount(curVal + 1);

        TextView textCena = findViewById(R.id.textPrice);
        int bill = getPrice((String) textCena.getText());
        textCena.setText("Cena ukupno: " + (bill + orders.get(index).getProduct().getCena()) + " din");
    }

    public void minus(View view) {
        LinearLayout linearLayout = findViewById(R.id.ordersContainer);
        View parent1 = (View) view.getParent();
        View parent2 = (View) parent1.getParent();
        int index = linearLayout.indexOfChild((View) parent2.getParent());

        RelativeLayout child = (RelativeLayout) linearLayout.getChildAt(index);
        LinearLayout child1 = child.findViewById(R.id.par1);
        LinearLayout child2 = child1.findViewById(R.id.par2);

        EditText count = child2.findViewById(R.id.count);
        int curVal = Integer.parseInt(String.valueOf(count.getText()));
        if (curVal != 1) {
            count.setText(String.valueOf(curVal - 1));
            orders.get(index).setAmount(curVal - 1);

            TextView textCena = findViewById(R.id.textPrice);
            int bill = getPrice((String) textCena.getText());
            textCena.setText("Cena ukupno: " + (bill - orders.get(index).getProduct().getCena()) + " din");
        }
    }

    public void erase(View view) {
        LinearLayout linearLayout = findViewById(R.id.ordersContainer);
        View parent1 = (View) view.getParent();
        View parent2 = (View) parent1.getParent();
        int index = linearLayout.indexOfChild((View) parent2.getParent());
        RelativeLayout child = (RelativeLayout) linearLayout.getChildAt(index);
        LinearLayout child1 = child.findViewById(R.id.par1);
        LinearLayout child2 = child1.findViewById(R.id.par2);

        EditText count = child2.findViewById(R.id.count);
        int curVal = Integer.parseInt(String.valueOf(count.getText()));

        linearLayout.removeView(child);

        TextView textCena = findViewById(R.id.textPrice);
        int bill = getPrice((String) textCena.getText());

        if (bill - orders.get(index).getProduct().getCena() * curVal != 0) {
            textCena.setText("Cena ukupno: " + (bill - orders.get(index).getProduct().getCena() * curVal) + " din");
        } else {
            textCena.setText("Nema ničega u korpi.");
        }
        orders.remove(index);
    }

    public void order(View view) {
        TextView textCena = findViewById(R.id.textPrice);
        if (textCena.getText().equals("Nema ničega u korpi.")) {
            Toast.makeText(this, "Nemoguće poručiti.", Toast.LENGTH_SHORT).show();
        } else {
            int bill = getPrice((String) textCena.getText());
            ArrayList<Integer> bills = retrieveBillsFromCache(user);
            bills.add(bill);
            saveUserBillsToCache(bills, user);
            LinearLayout linearLayout = findViewById(R.id.ordersContainer);
            linearLayout.removeAllViews();
            orders.clear();
            textCena.setText("Nema ničega u korpi.");
        }
    }
}
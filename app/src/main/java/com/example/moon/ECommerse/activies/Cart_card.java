package com.example.moon.ECommerse.activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.adapter.cart_adapter;
import com.example.moon.ECommerse.db.SqlHelper;
import com.example.moon.ECommerse.models.ShopProduct;
import com.example.moon.ECommerse.payment.Payment;

import java.util.ArrayList;

public class Cart_card extends AppCompatActivity {

    Button appCompatButton;
    ListView listView;
    ArrayList<ShopProduct> arrayList;
    cart_adapter adapter;
    TextView textView;
    SqlHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_card);
        arrayList = new ArrayList<>();
        textView = (TextView)findViewById(R.id.total_cost);
        sqlHelper = new SqlHelper(getApplicationContext());
//        arrayList.add(new ShopProduct(R.drawable.image_1,"TITLE NAME 1 ","100","TITLE PRICE 1"));
//        arrayList.add(new ShopProduct(R.drawable.image_2,"TITLE NAME 2 ","200","TITLE PRICE 2"));
//        arrayList.add(new ShopProduct(R.drawable.image_3,"TITLE NAME 3","300","TITLE PRICE 3"));
//        arrayList.add(new ShopProduct(R.drawable.image_1,"TITLE NAME 1 ","400","TITLE PRICE 1"));
//        arrayList.add(new ShopProduct(R.drawable.image_2,"TITLE NAME 2 ","500","TITLE PRICE 2"));



        ArrayList<ShopProduct> shopProduct_list = new ArrayList<>();

        shopProduct_list = sqlHelper.getCartAddedData();


        appCompatButton = (Button) findViewById(R.id.btn_checkout);
        listView = (ListView)findViewById(R.id.lv_);
        adapter = new cart_adapter(getApplicationContext(),shopProduct_list,textView,sqlHelper,listView);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int TotalCost = Integer.parseInt(textView.getText().toString().trim());
                Intent intent = new Intent(Cart_card.this, Payment.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"CHECK OUT "+TotalCost,Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void resetdb(View view) {
       boolean b =  sqlHelper.resetAddCart();
       if(b){
           Toast.makeText(getApplicationContext(),"RESET",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(getApplicationContext(),"Failed to reset",Toast.LENGTH_SHORT).show();
       }

    }
}

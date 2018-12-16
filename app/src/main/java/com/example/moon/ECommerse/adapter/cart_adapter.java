package com.example.moon.ECommerse.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.db.SqlHelper;
import com.example.moon.ECommerse.models.ShopProduct;

import java.util.ArrayList;


public class cart_adapter extends BaseAdapter {
    Context context;
    ArrayList<ShopProduct> arrayList;
    LayoutInflater inflater;
    TextView textView;
    String TAG = "MyTag";
    SqlHelper sqlHelper;
    ListView listView;

    public cart_adapter(Context context, ArrayList<ShopProduct> arrayList, TextView textView, SqlHelper sqlHelper, ListView listView) {
        this.context = context;
        this.arrayList = arrayList;
        Log.i(TAG, "cart_adapter: "+arrayList.size());
        this.textView = textView;
        this.sqlHelper = sqlHelper;
        this.listView = listView;
        int totalCost = 0;
        for(int i=0;i<arrayList.size();i++){
           int price = Integer.parseInt( arrayList.get(i).getPrice().trim());
           int count = arrayList.get(i).getCount();
           totalCost+=(price*count);
        }
        textView.setText(String.valueOf(totalCost));

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount: "+arrayList.size());
        return arrayList.size();
    }

    @Override
    public ShopProduct getItem(int i) {
        Log.i(TAG, "getItem: "+i);
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        Log.i(TAG, "getItemId: "+i);
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        TextView productName;
        final TextView productPrice;
        ImageView ibtn_add,ibtn_remove,iv_delete;
        final TextView productNumOfProduct;
        final ImageView iv_product;



        view = inflater.inflate(R.layout.cart_adapter_des,null);
        productName = (TextView)view.findViewById(R.id.tv_productName);
        productPrice = (TextView)view.findViewById(R.id.tv_productPrice);
        productNumOfProduct = (TextView)view.findViewById(R.id.tv_product_count);
        ibtn_add = (ImageView) view.findViewById(R.id.ibtn_add_product);
        ibtn_remove = (ImageView) view.findViewById(R.id.ibtn_remove_product);
        iv_product = (ImageView)view.findViewById(R.id.iv_product_d);
        iv_delete = (ImageView)view.findViewById(R.id.iv_deleteCart);


        Log.i(TAG, "getView: "+arrayList.get(i).getTitle());

        iv_product.setImageResource(arrayList.get(i).getImage());
        productName.setText(arrayList.get(i).getTitle());
        productPrice.setText(arrayList.get(i).getPrice());
        productNumOfProduct.setText(String.valueOf(arrayList.get(i).getCount()));

        ibtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Added Product",Toast.LENGTH_SHORT).show();
                int product_count = Integer.parseInt(productNumOfProduct.getText().toString().trim());
                product_count++;
                productNumOfProduct.setText(String.valueOf(product_count));
                int cost = Integer.parseInt(textView.getText().toString().trim())+Integer.parseInt(productPrice.getText().toString().trim());
                textView.setText(String.valueOf(cost));
                boolean update = sqlHelper.update(arrayList.get(i).getId(), product_count);
                if(update){
                    Toast.makeText(context,"UPDATED",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show();
                }


            }
        });

        ibtn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Removed Product",Toast.LENGTH_SHORT).show();
                int product_count = Integer.parseInt(productNumOfProduct.getText().toString().trim());
                product_count--;
                Toast.makeText(context,""+product_count,Toast.LENGTH_SHORT).show();
                if(product_count>=0) {
                    productNumOfProduct.setText(String.valueOf(product_count));
                    int cost = Integer.parseInt(textView.getText().toString().trim()) - Integer.parseInt(productPrice.getText().toString().trim());
                    textView.setText(String.valueOf(cost));
                    boolean update = sqlHelper.update(arrayList.get(i).getId(), product_count);
                    if(update){
                        Toast.makeText(context,"UPDATED",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = sqlHelper.deleteById(arrayList.get(i).getId());
                if(b){
                    arrayList.remove(i);
                     updateList();
                    Toast.makeText(context,"Deleted Product From Cart",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Failed to delete from cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void updateList() {
        ArrayList<ShopProduct> arrayList = sqlHelper.getCartAddedData();
        cart_adapter cart_adapter = new cart_adapter(context,arrayList,textView,sqlHelper,listView);
        listView.setAdapter(cart_adapter);
        cart_adapter.notifyDataSetChanged();
    }
}

package com.example.moon.ECommerse.activies;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moon.ECommerse.cart.Cart_AddedClass;
import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.Tools.Tools;
import com.example.moon.ECommerse.db.SqlHelper;
import com.example.moon.ECommerse.models.ShopProduct;
import com.example.moon.ECommerse.utils.ViewAnimation;

public class ProductDetail extends AppCompatActivity {

    private View parent_view;

    private ImageButton bt_toggle_reviews, bt_toggle_warranty, bt_toggle_description;
    private View lyt_expand_reviews, lyt_expand_warranty, lyt_expand_description;
    private NestedScrollView nested_scroll_view;
    TextView title,price,details;
    ImageView imageView;
    ShopProduct shopProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        parent_view = findViewById(R.id.parent_view);

        title = (TextView)findViewById(R.id.title_);
        price = (TextView)findViewById(R.id.price_);
        details = (TextView)findViewById(R.id.details_);
        imageView = (ImageView)findViewById(R.id.image_);

        Intent intent = getIntent();
        int image = intent.getIntExtra("image",0);
        int id = intent.getIntExtra("id",0);
        String title_i = intent.getStringExtra("title");
        String price_i = intent.getStringExtra("price");
        String detail_i = intent.getStringExtra("detail");
        int count = intent.getIntExtra("count",0);

        shopProduct = new ShopProduct(id,image,title_i,price_i,detail_i,count);
        title.setText(title_i);
        price.setText(price_i);
        details.setText(detail_i);


        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fashion");
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        // nested scrollview
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);

        // section reviews
        bt_toggle_reviews = (ImageButton) findViewById(R.id.bt_toggle_reviews);
        lyt_expand_reviews = (View) findViewById(R.id.lyt_expand_reviews);
        bt_toggle_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_reviews);
            }
        });

        // section warranty
        bt_toggle_warranty = (ImageButton) findViewById(R.id.bt_toggle_warranty);
        lyt_expand_warranty = (View) findViewById(R.id.lyt_expand_warranty);
        bt_toggle_warranty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_warranty);
            }
        });

        // section description
        bt_toggle_description = (ImageButton) findViewById(R.id.bt_toggle_description);
        lyt_expand_description = (View) findViewById(R.id.lyt_expand_description);
        bt_toggle_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_description);
            }
        });

        // expand first description
        toggleArrow(bt_toggle_description);
        lyt_expand_description.setVisibility(View.VISIBLE);

        ((FloatingActionButton) findViewById(R.id.fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();

               // Snackbar.make(parent_view, "Add to Cart", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart() {
        Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_SHORT).show();
        Cart_AddedClass.setShopProduct(shopProduct);
        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
        boolean b = sqlHelper.addProductToCart(shopProduct);
        if(b){
            Toast.makeText(getApplicationContext(),"Product Added",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Failed to add",Toast.LENGTH_SHORT).show();
        }


    }

    private void toggleSection(View bt, final View lyt) {
        boolean show = toggleArrow(bt);
        if (show) {
            ViewAnimation.expand(lyt, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt);
                }
            });
        } else {
            ViewAnimation.collapse(lyt);
        }
    }

    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

}

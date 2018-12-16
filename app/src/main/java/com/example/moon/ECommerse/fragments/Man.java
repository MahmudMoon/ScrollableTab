package com.example.moon.ECommerse.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.Tools.Tools;
import com.example.moon.ECommerse.adapter.AdapterGridShopProductCard;
import com.example.moon.ECommerse.models.ShopProduct;
import com.example.moon.ECommerse.widget.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Man extends Fragment {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterGridShopProductCard mAdapter;
    List<ShopProduct> items;
    Context context;

    

    int[] ids = {
            1,2,3,4,5,
            6,7,8,9,10

    };
    int[] images = {
       R.drawable.image_1,
       R.drawable.image_2,
       R.drawable.image_3,
       R.drawable.image_4,
       R.drawable.image_5,
       R.drawable.image_1,
       R.drawable.image_2,
       R.drawable.image_3,
       R.drawable.image_4,
       R.drawable.image_5,

    };

    String[] titels = {
      "Title 1",
       "Title 2",
       "Title 3",
       "Title 4",
       "Title 5",
       "Title 1",
       "Title 2",
       "Title 3",
       "Title 4",
       "Title 5",

    };

    String[] details = {
            "Details 1",
            "Details 2",
            "Details 3",
            "Details 4",
            "Details 5",
            "Details 1",
            "Details 2",
            "Details 3",
            "Details 4",
            "Details 5",
    };

    String[] prices = {
            "100","200","300","400","500","100","200","300","400","500",
    };


    @SuppressLint("ValidFragment")
    public Man(Context context) {
      this.context = context;
      items = new ArrayList<>();
      for(int i=0;i<10;i++){
          items.add(new ShopProduct(ids[i],images[i],titels[i],prices[i],details[i],0));
      }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_man, container, false);
        parent_view = view.findViewById(R.id.parent_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(context, 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);


        mAdapter = new AdapterGridShopProductCard(context,items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridShopProductCard.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, int position) {
                Snackbar.make(parent_view, "Item " + obj.title + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnMoreButtonClickListener(new AdapterGridShopProductCard.OnMoreButtonClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, MenuItem item) {
                Snackbar.make(parent_view, obj.title + " (" + item.getTitle() + ") clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}

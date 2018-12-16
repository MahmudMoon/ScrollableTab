package com.example.moon.ECommerse.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.moon.ECommerse.fragments.AllTime;
import com.example.moon.ECommerse.fragments.Man;
import com.example.moon.ECommerse.fragments.NewBorn;
import com.example.moon.ECommerse.fragments.OldMan;
import com.example.moon.ECommerse.fragments.OldWoman;
import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.fragments.Woman;
import com.example.moon.ECommerse.fragments.YoungBoy;
import com.example.moon.ECommerse.fragments.YoungGirl;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int[] icons = {R.drawable.ic_call_black_24dp,R.drawable.ic_directions_bus_black_24dp,
            R.drawable.ic_call_end_black_24dp,R.drawable.ic_favorite_black_24dp,
            R.drawable.ic_call_black_24dp,R.drawable.ic_directions_bus_black_24dp,
            R.drawable.ic_call_end_black_24dp,R.drawable.ic_favorite_black_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setIcons();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void setIcons(){
       for(int i =0;i<8;i++){
           tabLayout.getTabAt(i).setIcon(icons[i]);
       }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Man(getApplicationContext()), "Man");
        adapter.addFragment(new Woman(), "Woman");
        adapter.addFragment(new YoungBoy(), "YoungBoy");
        adapter.addFragment(new YoungGirl(),"YoungGirl");
        adapter.addFragment(new NewBorn(),"NewBorn");
        adapter.addFragment(new OldMan(),"OldMan");
        adapter.addFragment(new OldWoman(),"OldWoman");
        adapter.addFragment(new AllTime(),"AllTime");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.cart_add){
            Intent intent = new Intent(MainActivity.this,Cart_card.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"CART PRINTED",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}

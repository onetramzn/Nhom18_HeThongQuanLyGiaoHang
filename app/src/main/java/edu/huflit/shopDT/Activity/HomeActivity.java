package edu.huflit.shopDT.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.huflit.shopDT.Phones;
import edu.huflit.shopDT.R;
import edu.huflit.shopDT.Adapter.ShopAdapter;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    private ViewPager bannerViewPager;
    private ShopAdapter shopAdapter;
    private DatabaseReference database;
    RecyclerView recyclerView1,recyclerView2;
    ArrayList<Phones> phoneList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_ac);

//        bannerViewPager = findViewById(R.id.bannerViewPager);
//        database = FirebaseDatabase.getInstance().getReference("Picture/Banner/link");
//
//        // Create a list of banner items (replace with your logic to fetch banner data)
//        ArrayList<Phones> bannerItems = new ArrayList<>();
//        // Add banner items to the list
//
//        shopAdapter = new ShopAdapter1(bannerItems, this);
//        bannerViewPager.setAdapter(shopAdapter);
//
//        // Add any other setup logic for your UI components here

        bnv = findViewById(R.id.bottom_menu);
        bnv.setSelectedItemId(R.id.mnhome);
        bnv.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.mnhome:
                    return true;
                case R.id.mnliked:
                    startActivity(new Intent(getApplicationContext(),FavoriteActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.mnsearch:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.logout:
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        database = FirebaseDatabase.getInstance().getReference("List_Phone");

        recyclerView1 = findViewById(R.id.categoryRecyclerView);
        recyclerView2 = findViewById(R.id.PhonesRecyclerView2);

        phoneList = new ArrayList<>();

        shopAdapter= new ShopAdapter(phoneList, HomeActivity.this);

        recyclerView1.setAdapter(shopAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager1);

        recyclerView2.setAdapter(shopAdapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Phones phones = dataSnapshot.getValue(Phones.class);
                    phoneList.add(phones);


                }
                shopAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logoutmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                logoutUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    private void logoutUser() {
        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Bạn đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
    }

}
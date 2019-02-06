package com.dreamfactory.firstfooddelivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.firstfooddelivery.R;
import com.dreamfactory.firstfooddelivery.adapter.GridAdapter;
import com.dreamfactory.firstfooddelivery.model.GridPojo;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialSearchView materialSearchView;

    private GridAdapter gridAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private List<GridPojo> gridPojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        toolbar = (Toolbar) findViewById(R.id.restaurantToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);    //Shows Display back button

        materialSearchView = (MaterialSearchView) findViewById(R.id.materialSearchView);

        //Start For setting gridView on recyclerview
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        if (getApplicationContext().getResources().getConfiguration().orientation == 1) {
            gridLayoutManager = new GridLayoutManager(RestaurantActivity.this, 2);
        } else if (getApplicationContext().getResources().getConfiguration().orientation == 2) {
            gridLayoutManager = new GridLayoutManager(RestaurantActivity.this, 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);

        gridAdapter = new GridAdapter(this, getData());
        recyclerView.setAdapter(gridAdapter);
        //End For setting gridView on recyclerview
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all, menu);

        implementCartMenuItem(menu);  //Implementation of actionbar cart menu item

        implementFavoriteMenuItem(menu); //Implementation of actionbar favorite menu item

        implementSearchMenuItem(menu);

        return true;
    }

    private void implementSearchMenuItem(Menu menu) {
        MenuItem searchMenuItem = menu.findItem(R.id.menuall_search_item_id);
        materialSearchView.setMenuItem(searchMenuItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //Display back button implementation
            case android.R.id.home:
                Toast.makeText(this, "Back to Display", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void implementFavoriteMenuItem(Menu menu) {
        MenuItem favoriteMenuItem = menu.findItem(R.id.menuall_favorite_item_id);
        MenuItemCompat.setActionView(favoriteMenuItem, R.layout.favorite_actionbar);
        RelativeLayout favoriteRelativeLayout = (RelativeLayout) MenuItemCompat.getActionView(favoriteMenuItem);

        TextView txtFavoriteCount = (TextView) favoriteRelativeLayout.findViewById(R.id.favorite_actionbar_textview);
        txtFavoriteCount.setText("12");
        txtFavoriteCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RestaurantActivity.this, "Clicked Favorite", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void implementCartMenuItem(Menu menu) {
        MenuItem cartMenuItem = menu.findItem(R.id.menuall_cart_item_id);
        MenuItemCompat.setActionView(cartMenuItem, R.layout.cart_actionbar);
        RelativeLayout cartRelativeLayout = (RelativeLayout) MenuItemCompat.getActionView(cartMenuItem);

        TextView txtCartCount = (TextView) cartRelativeLayout.findViewById(R.id.cart_actionbar_textview);
        txtCartCount.setText("12");
        txtCartCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RestaurantActivity.this, "Clicked Cart Simple", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Start Creating listitem
    private List<GridPojo> getData() {

        gridPojoList = new ArrayList<>();
        GridPojo pojo = new GridPojo(R.drawable.r1, "Hotel Castle Salam");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r2, "Kitchen Garden");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r3, "Food Pales");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r4, "Good Kitschen");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r5, "Dorbar Kitchen");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r5, "Green Village");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r6, "Food Maker");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r6, "Food Maker");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.r6, "Food Maker");
        gridPojoList.add(pojo);

        return gridPojoList;
    }
    //End Creating listitem
}

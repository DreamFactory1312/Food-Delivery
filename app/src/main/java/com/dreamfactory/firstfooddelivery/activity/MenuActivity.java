package com.dreamfactory.firstfooddelivery.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.dreamfactory.firstfooddelivery.R;
import com.dreamfactory.firstfooddelivery.adapter.GridAdapter;
import com.dreamfactory.firstfooddelivery.model.GridPojo;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private GridAdapter gridAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private List<GridPojo> gridPojoList;

    private Boolean isFabOpen = false;
    private FloatingActionButton fabClick, fabCart, fabFavorite;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Start For setting gridView on recyclerview
        recyclerView = findViewById(R.id.menuRecyclerView);
        if (getApplicationContext().getResources().getConfiguration().orientation == 1) {
            gridLayoutManager = new GridLayoutManager(MenuActivity.this, 2);
        } else if (getApplicationContext().getResources().getConfiguration().orientation == 2) {
            gridLayoutManager = new GridLayoutManager(MenuActivity.this, 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);

        gridAdapter = new GridAdapter(this, getData());
        recyclerView.setAdapter(gridAdapter);
        //End For setting gridView on recyclerview

        fabClick = (FloatingActionButton) findViewById(R.id.fabClick);
        fabCart = (FloatingActionButton) findViewById(R.id.fabCart);
        fabFavorite = (FloatingActionButton) findViewById(R.id.fabFavorite);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        fabClick.setOnClickListener(this);
        fabCart.setOnClickListener(this);
        fabFavorite.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabClick:
                animateFab();
                break;
            case R.id.fabCart:
                Toast.makeText(this, "Fab Cart Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabFavorite:
                Toast.makeText(this, "Fab Favorite Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void animateFab() {
        if (isFabOpen) {

            fabClick.startAnimation(rotate_backward);
            fabCart.startAnimation(fab_close);
            fabFavorite.startAnimation(fab_close);
            fabCart.setClickable(false);
            fabFavorite.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {
            fabClick.startAnimation(rotate_forward);
            fabCart.startAnimation(fab_open);
            fabFavorite.startAnimation(fab_open);
            fabCart.setClickable(true);
            fabFavorite.setClickable(true);
            isFabOpen = true;
            Log.d("Raj", "open");

        }
    }
    //End Creating listitem
}

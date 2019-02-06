package com.dreamfactory.firstfooddelivery;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamfactory.firstfooddelivery.adapter.GridAdapter;
import com.dreamfactory.firstfooddelivery.model.GridPojo;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private SliderLayout sliderLayout;

    private GridAdapter gridAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private List<GridPojo> gridPojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        //For toolbar
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //For toolbar

        //Navigation bar settings
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //Navigation bar settings


        //For Slider Image View
        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(3); //set scroll delay in seconds
        setSliderViews();
        //For Slider Image View

        //Start For setting gridView on recyclerview
        recyclerView = findViewById(R.id.gridRecyclerView);
        gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        gridAdapter = new GridAdapter(this, getData());
        recyclerView.setAdapter(gridAdapter);
        //End For setting gridView on recyclerview
    }

    //Start Inflate Menu Options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        implementCartMenuItem(menu);  //Implementation of actionbar cart menu item

        implementFavoriteMenuItem(menu); //Implementation of actionbar favorite menu item


        return true;
    }
    //End Inflate Menu Options

    //Start implementation of actionbar favorite menu item
    private void implementFavoriteMenuItem(Menu menu) {
        MenuItem favoriteMenuItem = menu.findItem(R.id.favorite_item_id);
        MenuItemCompat.setActionView(favoriteMenuItem, R.layout.favorite_actionbar);
        RelativeLayout favoriteRelativeLayout = (RelativeLayout) MenuItemCompat.getActionView(favoriteMenuItem);

        TextView txtFavoriteCount = (TextView) favoriteRelativeLayout.findViewById(R.id.favorite_actionbar_textview);
        txtFavoriteCount.setText("12");
        txtFavoriteCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Favorite", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //End implementation of actionbar favorite menu item

    //Start Implementation of actionbar cart menu item
    private void implementCartMenuItem(Menu menu) {
        MenuItem cartMenuItem = menu.findItem(R.id.cart_item_id);
        MenuItemCompat.setActionView(cartMenuItem, R.layout.cart_actionbar);
        RelativeLayout cartRelativeLayout = (RelativeLayout) MenuItemCompat.getActionView(cartMenuItem);

        TextView txtCartCount = (TextView) cartRelativeLayout.findViewById(R.id.cart_actionbar_textview);
        txtCartCount.setText("12");
        txtCartCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //End Implementation of actionbar cart menu item

    //Start Implement Menu Item Selected Listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.favorite_item_id:
                Toast.makeText(this, "Clicked Favorite Item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart_item_id:
                Toast.makeText(this, "Clicked Cart Item", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //End Implement Menu Item Selected Listener

    //Navigation Item Selected Listener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        Log.d("MainActivity", "onNavigationItemSelected: " + menuItem);

        switch (id) {
            case R.id.navigation_menu_itemid_restaurant:
                Toast.makeText(this, "Clicked Restaurant", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_menu:
                Toast.makeText(this, "Clicked Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_cart:
                Toast.makeText(this, "Clicked Cart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_favorite:
                Toast.makeText(this, "Clicked Favorite", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_gallery:
                Toast.makeText(this, "Clicked Gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_location:
                Toast.makeText(this, "Clicked Location", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_news:
                Toast.makeText(this, "Clicked News", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_social:
                Toast.makeText(this, "Clicked Social", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_menu_itemid_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //Navigation Item Selected Listener

    //Action on BackPressed button
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //Action on BackPressed button

    //Start Implement Slider ImageView
    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.f4);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.f1);
                    //sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.f2);
                    //sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.f3);
                    //sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("Hotel Castle Salam. " + (i + 1));
            final int finalI = i;

            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }
    //Start Implement Slider ImageView

    //Start Creating listitem
    private List<GridPojo> getData() {

        gridPojoList = new ArrayList<>();
        GridPojo pojo = new GridPojo(R.drawable.grid_restaurant_icon, "Restaurant");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_menu_icon, "Menu");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_cartmenu_icon, "Cart");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_favorite_icon, "Favorite");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_gallery_icon, "Gallery");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_news_icon, "News");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_location_icon, "Location");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_social_icon, "Social");
        gridPojoList.add(pojo);

        pojo = new GridPojo(R.drawable.grid_about_icon, "About");
        gridPojoList.add(pojo);

        return gridPojoList;
    }
    //End Creating listitem

}

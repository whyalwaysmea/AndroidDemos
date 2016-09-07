package com.whyalwaysmea.systembar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.whyalwaysmea.systembar.color.ColorActivity1;
import com.whyalwaysmea.systembar.color.ColorActivity2;
import com.whyalwaysmea.systembar.picture.PictureActivity1;
import com.whyalwaysmea.systembar.picture.PictureActivity2;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }


    private void openActivity(Class<?> clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.color1:
                openActivity(ColorActivity1.class);
                break;
            case R.id.color2:
                openActivity(ColorActivity2.class);
                break;
            case R.id.picture1:
                openActivity(PictureActivity1.class);
                break;
            case R.id.picture2:
                openActivity(PictureActivity2.class);
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.color1:
                openActivity(ColorActivity1.class);
                break;
            case R.id.color2:
                openActivity(ColorActivity2.class);
                break;
            case R.id.picture1:
                openActivity(PictureActivity1.class);
                break;
            case R.id.picture2:
                openActivity(PictureActivity2.class);
                break;
        }
        return true;
    }
}

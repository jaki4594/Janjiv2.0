package com.example.zaki.janji;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Home_page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public TextView txtEmail;
    public TextView txtUsername;
    public ImageView imageView;
    public String email;
    private AppCompatActivity mainActivity;
    //Fragment fragmentd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //txtEmail = (TextView) findViewById(R.id.user_email);

        //email got from g+
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");
        String username = intent.getStringExtra("Username");
        String photo = intent.getStringExtra("UrlPhoto");
        //Uri fileUri = Uri.parse(photo);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        TextView navEmail = (TextView) headerView.findViewById(R.id.user_email);
        TextView navUsername = (TextView) headerView.findViewById(R.id.username);
        navEmail.setText(email);
        navUsername.setText(username);
        imageView = (ImageView) headerView.findViewById(R.id.imageView);

        Glide
                .with(this)
                .load(photo)
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.drawable.ic_menu_manage)
                .into(imageView);
        //Glide.with(this).load(photo).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

        navigationView.setNavigationItemSelectedListener(this);

        android.app.FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new Create_janji());
        transaction.commit();
        //fragmentd = new Create_janji();
        //setFragment(fragmentd);

    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        android.app.Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            // Handle the camera action
            fragment = new Create_janji();
        } else if (id == R.id.nav_view) {
            fragment = new List_janji();

        }else if (id == R.id.nav_manage) {
            fragment = new List_friends();

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {
            android.app.FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            //ft.replace(R.id.content_frame, fragment);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

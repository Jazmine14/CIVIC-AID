package com.example.dell.civicaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public void ts(View v)
    {
        startActivity(new Intent(Main2Activity.this,travelsafe.class));
    }
    public void cmg(View v)
    {
        startActivity(new Intent(Main2Activity.this,Mygroup.class));
    }
    public void sos(View v)
    {
        startActivity(new Intent(Main2Activity.this,SOS.class));
    }
    public void rai(View v)
    {
        startActivity(new Intent(Main2Activity.this,ReportIncident.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_about) {
            // Handle the action
            startActivity(new Intent(Main2Activity.this,AboutCivicAid.class));
        } else if (id == R.id.nav_developer) {
            startActivity(new Intent(Main2Activity.this,Developer.class));
        } else if (id == R.id.nav_disclaimer) {
            startActivity(new Intent(Main2Activity.this,Disclaimer.class));
        } else if (id == R.id.nav_feedback) {
           startActivity(new Intent(Main2Activity.this,Feedback.class));
        } else if (id == R.id.nav_share) {

            Intent shareIntent;
            String shareBody="This is a great App, you should try it out !";
            shareIntent= new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/pain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Civic Aid");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(shareIntent,"Share via"));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

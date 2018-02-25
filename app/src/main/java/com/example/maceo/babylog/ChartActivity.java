package com.example.maceo.babylog;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ChartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        ActionBarDrawerToggle myToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(myToggle);
        myToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){
            case R.id.home:
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.feed:
                Intent h= new Intent(this,FeedingActivity.class);
                startActivity(h);
                break;
            case R.id.sleep:
                Intent i= new Intent(this,SleepActivity.class);
                startActivity(i);
                break;
            case R.id.diaper:
                Intent g= new Intent(this,DiaperChangeActivity.class);
                startActivity(g);
                break;
            case R.id.chart:
                /*Intent s= new Intent(this,ChartActivity.class);
                startActivity(s);*/
                break;
            case R.id.journal:
                /*Intent t= new Intent(this,.class);
                startActivity(t);*/
                break;
            case R.id.tt:
                /*Intent t= new Intent(this,.class);
                startActivity(t);*/
                break;
            case R.id.weightT:
                /*Intent t= new Intent(this,.class);
                startActivity(t);*/
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

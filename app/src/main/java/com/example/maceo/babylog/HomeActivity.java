package com.example.maceo.babylog;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    // TODO: Declare member variables here:
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        // Setup the ViewPager with the sections adapter
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout myDrawer = (DrawerLayout) findViewById(R.id.myDrawer);
        ActionBarDrawerToggle myToggle = new ActionBarDrawerToggle(this,myDrawer,toolbar,R.string.open,R.string.close);

        myDrawer.addDrawerListener(myToggle);
        myToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fab = (FloatingActionButton)findViewById(R.id.fab1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HomeActivity.this, "FAB Clicked", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1(), "Tab1");
        adapter.addFragment(new Tab2(), "Tab2");
        adapter.addFragment(new Tab3(), "Tab3");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home);
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
                /*Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);*/
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
                Intent s= new Intent(this,ChartActivity.class);
                startActivity(s);
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

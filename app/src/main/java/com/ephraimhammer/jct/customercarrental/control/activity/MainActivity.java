package com.ephraimhammer.jct.customercarrental.control.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.fragment.HomeFragment;
import com.ephraimhammer.jct.customercarrental.control.other.SEARCH_CAR_TYPE;
import com.ephraimhammer.jct.customercarrental.control.fragment.CarFreeListFragment;
import com.ephraimhammer.jct.customercarrental.control.fragment.branchListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;


    //To know the index of the nav Pressed.
    public static int navItemIndex = 0;


    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_BRANCH_LIST = "list_branch";

    //CurrentTag can change.
    public static String CURRENT_TAG = TAG_HOME;



    //Get the fragment that should be displayed in the Home context
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home (in the meantime--)
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;


            case 1:
                // branch List
                branchListFragment branchListFragment = new branchListFragment();
                return branchListFragment;

            default: return new HomeFragment();




        }
    }

    // load the fragment IN the Home context via beginTransaction...
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }


        // update the main content by replacing fragments
        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    // Need for graphic  effect , when the item is clicked the background of the item change.
    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // By default, the fragment to display is the Home fragment
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }



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
        getMenuInflater().inflate(R.menu.main, menu);
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
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            // Handle the camera action

        } else if (id == R.id.nav_list_branch) {

            navItemIndex = 1;
            CURRENT_TAG = TAG_BRANCH_LIST;

        }
        else if (id == R.id.nav_send) {

        }
        else if (id == R.id.nav_login)
        {

        }


        loadHomeFragment();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

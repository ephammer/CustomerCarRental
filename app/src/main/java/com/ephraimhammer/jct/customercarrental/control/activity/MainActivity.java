package com.ephraimhammer.jct.customercarrental.control.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TableRow;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.fragment.CarFreeListFragment;
import com.ephraimhammer.jct.customercarrental.control.fragment.DetailCarFragment;
import com.ephraimhammer.jct.customercarrental.control.fragment.HomeFragment;
import com.ephraimhammer.jct.customercarrental.control.fragment.BranchListFragment;
import com.ephraimhammer.jct.customercarrental.control.fragment.DetailBranchFragment;
import com.ephraimhammer.jct.customercarrental.control.other.COMUNICATE_BTWN_FRAG;
import com.ephraimhammer.jct.customercarrental.control.other.IsAbleToCommunicateFragment;
import com.ephraimhammer.jct.customercarrental.control.other.SEARCH_CAR_TYPE;
import com.ephraimhammer.jct.customercarrental.model.backend.Academy_Const;
import com.ephraimhammer.jct.customercarrental.model.entities.Branch;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;

public class MainActivity extends AppCompatActivity implements IsAbleToCommunicateFragment

{

    private NavigationView navigationView;
    private DrawerLayout drawer;

    private Branch branch;
    private long carId;
    private Car car;

    private SharedPreferences sharedPref;


    //To know the index of the nav Pressed.
    public static int navItemIndex = 0;



    private static final String BRANCH_DETAIL = "branch_detail";
    private static final String CAR_DETAIL = "car_detail";

    private static final String BRANCH_REDIRECT = "branch_redirect";
    private static final String CAR_REDIRECT = "car_redirect";


    private static  String DETAIL_ITEM_TO_DISPLAY = "";
    private static String REDIRECT_ITEM_TI_DISPLAY= "";

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_BRANCH_LIST = "list_branch";
    private static final String TAG_FREE_CARS_LIST = "list_free_cars";

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
                BranchListFragment branchListFragment = new BranchListFragment();
                return branchListFragment;
            case 2:
                CarFreeListFragment carFreeListFragment = new CarFreeListFragment();
                carFreeListFragment.setUsedForMainFrag(true);
                carFreeListFragment.setSearch_car_type(SEARCH_CAR_TYPE.FREE_CARS);
                return carFreeListFragment;

            default:
                return new HomeFragment();


        }
    }

    private Fragment getDetailFragment()
    {
     switch(DETAIL_ITEM_TO_DISPLAY)
     {

         case BRANCH_DETAIL:
             DetailBranchFragment branchdetailFragment = new DetailBranchFragment();
             branchdetailFragment.setBranch(branch.getBranchId());

             return branchdetailFragment;
         case CAR_DETAIL:
             DetailCarFragment detailCarFragment = new DetailCarFragment();
             detailCarFragment.setCar(car);
             return detailCarFragment;


             default: return null;
     }


    }

    private Fragment getRedirectFragment()
    {
        switch (REDIRECT_ITEM_TI_DISPLAY) {
            case CAR_REDIRECT:
                CarFreeListFragment carFreeListFragment = new CarFreeListFragment();
                carFreeListFragment.setBranchId(branch.getBranchId());
                carFreeListFragment.setSearch_car_type(SEARCH_CAR_TYPE.FREE_CARS_BY_BRANCH);
                return carFreeListFragment;
            case BRANCH_REDIRECT:
                DetailBranchFragment detailBranchFragment = new DetailBranchFragment();
                detailBranchFragment.setBranch(car.getBranchIdCarParked());
                return detailBranchFragment;

            default:return null;
        }
    }

    private void loadDetailFragment()
    {

        // update the main content by replacing fragments
        Fragment fragment = getDetailFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.detailFrame, fragment);
        fragmentTransaction.commitAllowingStateLoss();
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

    private void loadRedirectFragment()
    {
        Fragment fragment = getRedirectFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.redirectFrame, fragment);
        fragmentTransaction.commitAllowingStateLoss();
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

        // initializing navigation menu
        setUpNavigationView();

        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_login), Context.MODE_PRIVATE);


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


            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            resetDefaultConfiguration();
            loadHomeFragment();
        }
    }

    private void resetDefaultConfiguration() {
        FrameLayout frameLayoutdetail = (FrameLayout) findViewById(R.id.detailFrame);
        frameLayoutdetail.setVisibility(View.GONE);

        FrameLayout frameLayoutRedirect = (FrameLayout) findViewById(R.id.redirectFrame);
        frameLayoutRedirect.setVisibility(View.GONE);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.weight = 1f;
        params.height = 0;
        FrameLayout frameLayoutList = (FrameLayout) findViewById(R.id.frame);
        frameLayoutList.setLayoutParams(params);

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
        if (id == R.id.action_command_list) {
            startActivity(new Intent(this,CommandListActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                resetDefaultConfiguration();

                if (id == R.id.nav_home) {

                    navItemIndex = 0;
                    CURRENT_TAG = TAG_HOME;
                    // Handle the camera action

                } else if (id == R.id.nav_list_branch) {

                    navItemIndex = 1;
                    CURRENT_TAG = TAG_BRANCH_LIST;

                }
                else if(id == R.id.nav_display_free_car)
                {
                    navItemIndex = 2;
                    CURRENT_TAG = TAG_FREE_CARS_LIST;
                }
                else if (id == R.id.nav_send) {
                    composeEmail("contact@tapandgo.com");
                } else if (id == R.id.nav_login) {
                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                } else if (id == R.id.nav_webpage) {
                    openWebPage("http://www.thinkhodl.com");
                } else if (id == R.id.nav_phone) {
                    dialPhoneNumber("0584048820");
                } else if (id == R.id.nav_logout) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(getString(R.string.signedIn), false).apply();
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    finish();
                }
                //Checking if the item is in checked state or not, if not make it in checked state


                item.setChecked(item.isChecked() ? false : true);
                item.setChecked(true);


                loadHomeFragment();

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String addresse) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresse);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void displaydetail()
    {
        //Set the Frame to be Visible
        FrameLayout frameLayoutdetail = findViewById(R.id.detailFrame);
        frameLayoutdetail.setVisibility(View.VISIBLE);

        //reset the weight of the List layout.
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.weight = 0.4f;
        params.height = 0;
        FrameLayout frameLayoutList = findViewById(R.id.frame);
        frameLayoutList.setLayoutParams(params);
        loadDetailFragment();
    }

    private void displayRedirect() {
        //Set the Frame to be Visible
        FrameLayout frameLayoutRedirect = (FrameLayout) findViewById(R.id.redirectFrame);
        frameLayoutRedirect.setVisibility(View.VISIBLE);

        loadRedirectFragment();
    }

    @Override
    public void sendData(COMUNICATE_BTWN_FRAG com, Object... data) {

        switch (com)
        {
            case MAIN_BRANCH_LIST_TO_DETAIL_BRANCH_AND_REDIRECT_CAR:
                branch = (Branch) data[0];
                DETAIL_ITEM_TO_DISPLAY = BRANCH_DETAIL;
                REDIRECT_ITEM_TI_DISPLAY = CAR_REDIRECT;
                displaydetail();
                displayRedirect();
                break;
            case REDIRECT_CAR_LIST_TO_ADD_COMMAND:
                car = (Car)data[0];
                Intent addCommandIntent = new Intent(this , AddCommandActivity.class);
                addCommandIntent.putExtra(Academy_Const.BranchConst.ID , car.getBranchIdCarParked());
                addCommandIntent.putExtra(Academy_Const.CarConst.KILOMETRE ,car.getKilometre() );
                addCommandIntent.putExtra("carId" , car.getCarId());
                addCommandIntent.putExtra("carModelId" , car.getTypeModelID());
                startActivity(addCommandIntent);


                break;
            case MAIN_CAR_LIST_TO_DETAIL_CAR_AND_REDIRECT_BRANCH:
                DETAIL_ITEM_TO_DISPLAY = CAR_DETAIL;
                REDIRECT_ITEM_TI_DISPLAY = BRANCH_REDIRECT;
                car = (Car)data[0];
                displaydetail();
                displayRedirect();
                break;


        }


    }
}

package com.xaut.example.myapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xaut.example.myapi.dependent.ActivityModule;
import com.xaut.example.myapi.dependent.BaseActivity;
import com.xaut.example.myapi.main.DaggerMainComponent;
import com.xaut.example.myapi.main.MainComponent;
import com.xaut.example.myapi.main.MainFragment;
import com.xaut.example.myapi.main.MainModule;
import com.xaut.example.myapi.utils.ActivityUtils;
import com.xaut.example.myapi.utils.AppConstants;
import com.xaut.example.myapi.utils.EnumeUtil;
import com.xaut.example.myapi.webs.Main2Activity;
import com.xaut.example.myapi.webs.database.Items;

import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //百度
    public  static final String SDK_APP_KEY = "rGygF66DB7WucxyWzdLxWGDybRP2wmjM";
    private String SDK_BANNER_AD_ID = "sI6hqkhslxHLhwhVNkphIsMC";
    private String SDK_SPLASH_AD_ID	= "GnyUDXqyGYDETbhHS2Qc7XBm";
    private String SDK_INTERSTITIAL_AD_ID = "ntuHx5sTGGniFdR0eubEH76c";

    private MainComponent mainComponent;

    MainFragment mainFragment;
    public static EnumeUtil myenum = EnumeUtil.nav_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(new ActivityModule(this))
                .mainModule(new MainModule())
                .build();
        mainComponent.inject(this);


        if (mainFragment == null) {
            // Create the fragment
            mainFragment = new MainFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.main_fra);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public MainComponent getMainComponent() {
        return this.mainComponent;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{
            if (!mBackKeyPressed) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mBackKeyPressed = true;
                new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                    @Override
                    public void run() {
                        mBackKeyPressed = false;
                    }
                }, 2000);
            } else {//退出程序
                this.finish();
                System.exit(0);
            }
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

    MainFragment clothingFragment;
    MainFragment infantFragment = new MainFragment();
    MainFragment beautyFragment = new MainFragment();
    MainFragment standShoesFragment = new MainFragment();
    MainFragment boxToeFragment = new MainFragment();
    MainFragment eatFragment = new MainFragment();
    MainFragment writeFragment = new MainFragment();
    MainFragment electFragment = new MainFragment();
    MainFragment otherFragment = new MainFragment();

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_first) {
            if (!myenum.equals(EnumeUtil.nav_first)) {
                myenum = EnumeUtil.nav_first;
                switchFragment(mainFragment, 1);
            }
        } else if (id == R.id.nav_clothing) {
            myenum = EnumeUtil.nav_clothing;
            clothingFragment = new MainFragment();
            switchFragment(clothingFragment, 1);

        } else if (id == R.id.nav_infant) {
            if (!myenum.equals(EnumeUtil.nav_infant)) {
                myenum = EnumeUtil.nav_infant;
                switchFragment(infantFragment, 2);
            }
        } else if (id == R.id.nav_beauty) {
            if (!myenum.equals(EnumeUtil.nav_beauty)) {
                myenum = EnumeUtil.nav_beauty;
                switchFragment(beautyFragment, 2);
            }

        } else if (id == R.id.nav_stand_home) {
            if (!myenum.equals(EnumeUtil.nav_stand_home)) {
                myenum = EnumeUtil.nav_stand_home;
                switchFragment(standShoesFragment, 2);
            }
        } else if (id == R.id.nav_box_toe) {
            if (!myenum.equals(EnumeUtil.nav_box_toe)) {
                myenum = EnumeUtil.nav_box_toe;
                switchFragment(boxToeFragment, 2);
            }
        } else if (id == R.id.nav_eat) {
            if (!myenum.equals(EnumeUtil.nav_eat)) {
                myenum = EnumeUtil.nav_eat;
                switchFragment(eatFragment, 2);
            }
        } else if (id == R.id.nav_write) {
            if (!myenum.equals(EnumeUtil.nav_write)) {
                myenum = EnumeUtil.nav_write;
                switchFragment(writeFragment, 2);
            }
        } else if (id == R.id.nav_home_elect) {
            if (!myenum.equals(EnumeUtil.nav_home_elect)) {
                myenum = EnumeUtil.nav_home_elect;
                switchFragment(electFragment, 2);
            }
        } else if (id == R.id.nav_other) {
            if (!myenum.equals(EnumeUtil.nav_other)) {
                myenum = EnumeUtil.nav_other;
                switchFragment(otherFragment, 2);
            }
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchFragment(Fragment newFragment, int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putInt(AppConstants.POSITION, position);
        newFragment.setArguments(args);
        fragmentTransaction.replace(R.id.main_fra, newFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private static boolean mBackKeyPressed = false;//记录是否有首次按键


}

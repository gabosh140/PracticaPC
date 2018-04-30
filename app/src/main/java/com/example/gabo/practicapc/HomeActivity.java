package com.example.gabo.practicapc;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends FragmentActivity
implements DashboardFragment.OnFragmentInteractionListener,
HomeFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            updateFragment(item);
            return true;
        }
    };

    //Actualizar el fragment
    public void updateFragment(MenuItem item){
    Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();break;
            case R.id.navigation_dashboard:
                fragment = new DashboardFragment();break;
            case R.id.navigation_notifications:
                return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments,fragment).commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments,fragment).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("fragment interaction", uri.getPath());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

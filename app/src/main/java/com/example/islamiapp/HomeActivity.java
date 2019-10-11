package com.example.islamiapp;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.islamiapp.fragments.radio.RadioFragment;
import com.example.islamiapp.fragments.suras.SurasFragment;
import com.example.islamiapp.fragments.tasbeh.TasbehFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView.OnNavigationItemSelectedListener
            navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int id = menuItem.getItemId();
                    Fragment fragment=null;
                    if(id==R.id.navigation_quran){
                        fragment = new SurasFragment();
                    }else if(id==R.id.navigation_tasbeh){
                        fragment = new TasbehFragment();
                    }else if(id==R.id.navigation_radio){
                        fragment = new RadioFragment();
                    }
                    //replace fragment
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment,fragment)
                            .commit();

                    return true;
                }
            };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_quran);
    }

}

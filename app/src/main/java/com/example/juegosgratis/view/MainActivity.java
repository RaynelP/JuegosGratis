package com.example.juegosgratis.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.juegosgratis.R;
import com.example.juegosgratis.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String TAG = "ACTIVIDAD PRINCIPAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        binding = ActivityMainBinding
                .inflate(getLayoutInflater());
        setSupportActionBar((Toolbar) binding.toolbarActivity);
        setContentView(binding.getRoot());
        configNavigation();

    }

    private void configNavigation() {
        //
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController =  navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.homeFragment,
                        R.id.favoriteFragment
                ).build();

        //
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(binding.navigation, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        NavController navController =
                navHostFragment.getNavController();

        return NavigationUI.navigateUp(navController, (Openable) null);
    }

    public void doShowProgress(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    public void doneShowProgress(){
        binding.progressBar.setVisibility(View.GONE);
    }
}
package com.example.lot_examen2_06032019.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.lot_examen2_06032019.R;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appbarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        navController = Navigation.findNavController(this, R.id.navHostFragment);

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = ActivityCompat.requireViewById(this, R.id.toolbarMain);
        setSupportActionBar(toolbar);

        appbarConfiguration =  new AppBarConfiguration
                .Builder(navController.getGraph())
                .build();

        NavigationUI.setupWithNavController(toolbar, navController, appbarConfiguration);
//        setTitle(R.string.app_name);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> toolbar.setTitle(navController.getCurrentDestination().getLabel()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        return true;
    }
}

package com.antoniojudith.managerpassordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import com.antoniojudith.managerpassordapp.Fragmentos.F_Ajustes;
import com.antoniojudith.managerpassordapp.Fragmentos.F_Todas;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    boolean DobleToqueParaSalir = false;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // fragmento al ejecutar la aplicacion
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmento_container, new F_Todas()).commit();
            navigationView.setCheckedItem(R.id.opcion_todas);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.opcion_todas){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmento_container, new F_Todas()).commit();
        }

        if(id == R.id.opcion_ajustes){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmento_container, new F_Ajustes()).commit();
        }
        if(id == R.id.salir){
            Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_LONG).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(DobleToqueParaSalir){
            super.onBackPressed();
            Toast.makeText(this, "Saliste de la aplicacion", Toast.LENGTH_LONG).show();
            return;

        }
        // al presionar una vez en el boton de retroceso
        this.DobleToqueParaSalir = true;
        Toast.makeText(this, "Presione dos veces para salir", Toast.LENGTH_LONG).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                DobleToqueParaSalir = false;
            }
        }, 2000);

    }
}
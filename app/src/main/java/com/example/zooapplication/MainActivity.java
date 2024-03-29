package com.example.zooapplication;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CreateFragment.OnFragmentInteractionListener,
        EditFragment.OnFragmentInteractionListener,
        DeleteFragment.OnFragmentInteractionListener,
        ListFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener {

    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        if (id == R.id.nav_create) {
            setTitle("Fragmento Crear");
            fragment = new CreateFragment();
        } else if (id == R.id.nav_list) {
            setTitle("Fragmento Lista");
            fragment = new ListFragment();
        } else if (id == R.id.nav_delete) {
            setTitle("Fragmento Eliminar");
            fragment = new DeleteFragment();
        } else if (id == R.id.nav_edit) {
            setTitle("Fragmento Editar");
            fragment = new EditFragment();
        } else if (id == R.id.nav_credits) {
            dialog = new AlertDialog.Builder(this);
            dialog.setMessage("\tDeveloper:\nJosé Adán Cruz Castrejón.\n\n" +
                    "\tTeacher:\nRocio Elizabeth Pulido Alba.\n\n" +
                    "\tObject:\nDesarrollo de aplicaciones móviles.")
            .setTitle("Créditos");
            dialog.show();
//            setTitle("Fragmento Créditos");
//            fragment = new CreditsFragment();
        }

        if (fragment != null) {
            transaction.replace(R.id.frameLayout_contain_main, fragment, "CreditsFragment");
            transaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package com.exmple.grademanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.exmple.grademanage.Fragments.FragmentDelete;
import com.exmple.grademanage.Fragments.FragmentGradeEntry;
import com.exmple.grademanage.Fragments.FragmentSearch;
import com.exmple.grademanage.Fragments.FragmentUpdate;
import com.exmple.grademanage.Fragments.FragmentViewGrades;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawer,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentGradeEntry()).commit();
            navigationView.setCheckedItem(R.id.gradeEntry);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.gradeEntry:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentGradeEntry()).commit();
                break;

            case R.id.viewGrades:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentViewGrades()).commit();
                break;

            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentSearch()).commit();
                break;

            case R.id.updateEntry:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentUpdate()).commit();
                break;

            case R.id.deleteEntry:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentDelete()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
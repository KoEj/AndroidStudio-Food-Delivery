package com.example.bazydanych;


        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.widget.Toast;

        import com.google.android.material.navigation.NavigationView;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

public class loggedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_view);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();

        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String ID = getIntent().getStringExtra("ID");
        switch(item.getItemId()) {
            case R.id.nav_order:
                orderBackground bg_order = new orderBackground(this);
                bg_order.execute(ID);
               // if(bg.getStatus() == AsyncTask.Status.FINISHED) {
               // }
                break;

            case R.id.nav_history_orders:
                historyOrdersBackground bg_history = new historyOrdersBackground(this);
                bg_history.execute(ID);
                break;

            case R.id.nav_car:
                carBackground bg_car = new carBackground(this);
                bg_car.execute(ID);
                break;

            case R.id.nav_calendar:
                Intent intent_shift = new Intent(loggedActivity.this,shiftActivity.class);
                intent_shift.putExtra("ID",ID);
                startActivity(intent_shift);
                break;

            case R.id.nav_reset_password:
                Intent intent_reset_password = new Intent (loggedActivity.this,forgotActivity.class);
                startActivity(intent_reset_password);
                loggedActivity.this.finish();
                //Toast.makeText(this,"Jeszcze nie skonczone :)",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                Intent intent_logout = new Intent(loggedActivity.this,MainActivity.class);
                startActivity(intent_logout);
                loggedActivity.this.finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
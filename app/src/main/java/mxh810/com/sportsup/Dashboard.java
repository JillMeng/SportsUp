package mxh810.com.sportsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Initialize NavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        return true;
                    case R.id.schedule:
                        startActivity(new Intent(getApplicationContext()
                                ,Schedule.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext()
                                ,Info.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.layoutYoga:
                ChangeActivity1();
                break;
            case R.id.layoutHiit:
                ChangeActivity2();
                break;
            case R.id.layoutWeight:
                ChangeActivity3();
                break;
            case R.id.layoutRecipe:
                ChangeActivity4();
                break;
        }
    }

    private void ChangeActivity1() {
        Intent intent = new Intent(Dashboard.this, YogaClass.class);
        startActivity(intent);
    }

    private void ChangeActivity2() {
        Intent intent = new Intent(Dashboard.this, HIITCardio.class);
        startActivity(intent);
    }
    private void ChangeActivity3() {
        Intent intent = new Intent(Dashboard.this, Weights.class);
        startActivity(intent);
    }
    private void ChangeActivity4() {
        Intent intent = new Intent(Dashboard.this, Recipes.class);
        startActivity(intent);
    }
}
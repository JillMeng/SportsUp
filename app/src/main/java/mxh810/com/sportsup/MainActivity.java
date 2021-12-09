package mxh810.com.sportsup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.home:
                ChangeActivity();
                break;
            case R.id.login:
                LoginActivity();
                break;
            case R.id.friend:
                FriendActivity();
            case R.id.location:
                LocationActivity();
        }
    }

    private void ChangeActivity() {
        Intent intent1 = new Intent(MainActivity.this, Dashboard.class);
        startActivity(intent1);
    }

    private void LoginActivity(){
        Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent2);
    }

    private void FriendActivity() {
        Intent intent3 = new Intent(MainActivity.this, FriendList.class);
        startActivity(intent3);
    }

    private void LocationActivity() {
        Intent intent4 = new Intent(MainActivity.this, Location.class);
        startActivity(intent4);
    }
}
package mxh810.com.sportsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    private static final String TAG = "Dashboard";

    private final Context mContext = Dashboard.this;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        //Initialize NavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        setupFirebaseAuth();

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



    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //check if the user is logged in
                checkCurrentUser(user);

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser: checking if user is logged in.");

        if(user == null){
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        }
    }
}
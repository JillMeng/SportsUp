package mxh810.com.sportsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

public class Dashboard extends AppCompatActivity {

    private static final String TAG = "Dashboard";

    private final Context mContext = Dashboard.this;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Gson gson = new Gson();


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
                    case R.id.reminder:
                        startActivity(new Intent(getApplicationContext()
                                , Reminder.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext()
                                ,Info.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.friends:
                        startActivity(new Intent(getApplicationContext(), FriendList.class));
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



    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                Log.e(TAG, "current user is {}" + gson.toJson(user));

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

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        checkCurrentUser(mAuth.getCurrentUser());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
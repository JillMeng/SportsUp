package mxh810.com.sportsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;

import mxh810.com.sportsup.model.User;
import mxh810.com.sportsup.utils.UniversalImageLoader;

public class Dashboard extends AppCompatActivity {

    private static final String TAG = "Dashboard";

    private final Context mContext = Dashboard.this;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        initImageLoader();

        //Initialize NavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        setupFirebaseAuth();
        currentUser = mAuth.getCurrentUser();
        showName(currentUser);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        return true;
                    case R.id.schedule:
                        startActivity(new Intent(getApplicationContext()
                                ,PostActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext()
                                ,InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.friends:
                        Intent intent = new Intent(getApplicationContext(), FriendList.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("current_user_id", currentUser.getUid());
                        intent.putExtras(bundle);
                        startActivity(intent);
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

        mDatabase = FirebaseDatabase.getInstance().getReference();
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

    private void showName(FirebaseUser user){
        String uid = user.getUid();
        TextView userName = (TextView) findViewById(R.id.textUsername);
        // Show name
        mDatabase.child("user_account_settings").child(uid).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
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
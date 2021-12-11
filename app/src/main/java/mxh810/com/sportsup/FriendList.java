package mxh810.com.sportsup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;

import mxh810.com.sportsup.model.Friend;


public class FriendList extends AppCompatActivity {
    private final String TAG = "FriendsActivity";

    private DatabaseReference mDatabase;

    private RecyclerView recyclerView;
    private FriendAdapter rAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private Friend current_user;
    private Friend target_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        //Initialize NavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.friends);

//        setupFirebaseAuth();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.post:
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
                        return true;
                }
                return false;
            }
        });



        recyclerView = findViewById(R.id.list_friends);
        recyclerView.setHasFixedSize(true);
        rLayoutManger = new LinearLayoutManager(this);


        current_user = (Friend)getIntent().getSerializableExtra("current_user");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = FirebaseDatabase.getInstance().getReference("/user");
        FirebaseRecyclerOptions<Friend> options =
                new FirebaseRecyclerOptions.Builder<Friend>()
                        .setQuery(query, new SnapshotParser<Friend>() {
                                    @NonNull
                                    @Override
                                    public Friend parseSnapshot(@NonNull DataSnapshot snapshot) {
                                        String user_name = snapshot.getKey();
                                        String token = (String) snapshot.getValue();
                                        return new Friend(user_name,token);
                                    }
                                }
                        )
                        .build();
        Log.i(TAG, "user count: " + options.toString());
        rAdapter = new FriendAdapter(options);

        recyclerView.setAdapter(rAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
        rAdapter.setCurrentUser(current_user);



        rAdapter.startListening();
    }
}

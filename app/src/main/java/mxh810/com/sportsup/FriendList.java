package mxh810.com.sportsup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import mxh810.com.sportsup.Util.User;


public class FriendList extends AppCompatActivity {
    private final String TAG = "FriendsActivity";

    private DatabaseReference mDatabase;

    private RecyclerView recyclerView;
    private FriendAdapter rAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private User current_user;
    private User target_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);



        recyclerView = findViewById(R.id.list_friends);
        recyclerView.setHasFixedSize(true);
        rLayoutManger = new LinearLayoutManager(this);


        current_user = (User)getIntent().getSerializableExtra("current_user");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = FirebaseDatabase.getInstance().getReference("/user");
        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(query, new SnapshotParser<User>() {
                                    @NonNull
                                    @Override
                                    public User parseSnapshot(@NonNull DataSnapshot snapshot) {
                                        String user_name = snapshot.getKey();
                                        String token = (String) snapshot.getValue();
                                        return new User(user_name,token);
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

    public void selectSticker(View view ){
        Intent intent = new Intent(this, Location.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("target_user",target_user);
        bundle.putSerializable("current_user", current_user);
        intent.putExtras(bundle);
        startActivity(intent);
    }}

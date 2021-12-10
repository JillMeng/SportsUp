package mxh810.com.sportsup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class FriendList extends AppCompatActivity {
    /*private final String TAG = "FriendsActivity";

    private DatabaseReference mDatabase;

    private RecyclerView recyclerView;
    private FriendAdapter rAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private User current_user;
    private User target_user;
    private FloatingActionButton addButton;
    private EditText editName;
    private FloatingActionButton inButton;
    private RecyclerView recycle;
    private String addName;*/

    private ArrayList<ItemFriend> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addButton;
    private EditText editName;
    private EditText editURL;
    private FloatingActionButton inButton;
    private String addName;


    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        //Initialize NavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        //setupFirebaseAuth();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0,0);
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
                    case R.id.friends:

                        return true;
                }
                return false;
            }
        });

        /*recyclerView = findViewById(R.id.list_friends);
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

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(FriendList.this).create();
                View view_ = getLayoutInflater().inflate(R.layout.activity_friend,null);
                editName = view_.findViewById(R.id.nameInput);
                editName.setVisibility(View.VISIBLE);
                inButton = view_.findViewById(R.id.addButton);
                inButton.setVisibility(View.INVISIBLE);
                recycle = view_.findViewById(R.id.list_friends);
                recycle.setVisibility(View.INVISIBLE);

                builder.setView(view_);
                builder.setTitle("Enter Name");
                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar snackbar = Snackbar.make(view, "Input cancelled", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });

                builder.setButton(AlertDialog.BUTTON_POSITIVE, "select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addName = editName.getText().toString();
                        if(Patterns.WEB_URL.matcher(addName).matches()){
                            //itemList.add(0, new ItemFriend(addName));
                            rAdapter.notifyItemInserted(0);
                            Snackbar snackbar = Snackbar.make(view, "Friend Added", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }else{
                            Snackbar snackbar = Snackbar.make(view, "Invalid Name, try again", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });

                builder.show();
            }
        });*/
        initialItemData(savedInstanceState);
        createRecycleView();

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(FriendList.this).create();
                View view_ = getLayoutInflater().inflate(R.layout.input_name,null);
                editName = view_.findViewById(R.id.nameInput);
                editName.setVisibility(View.VISIBLE);
                inButton = view_.findViewById(R.id.addButton);
                inButton.setVisibility(View.INVISIBLE);
                recycle = view_.findViewById(R.id.recycle_view);
                recycle.setVisibility(View.INVISIBLE);

                builder.setView(view_);
                builder.setTitle("Enter Friend Name");
                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar snackbar = Snackbar.make(view, "Input cancelled", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });

                builder.setButton(AlertDialog.BUTTON_POSITIVE, "select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addName = editName.getText().toString();

                        /** if的判断条件是输入的name和数据库中已有的name是否相符
                        * */
                        if(true){
                            itemList.add(0, new ItemFriend(addName));
                            reviewAdapter.notifyItemInserted(0);
                            Snackbar snackbar = Snackbar.make(view, "Friend Added", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }else{
                            Snackbar snackbar = Snackbar.make(view, "Invalid Name, try again", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });

                builder.show();
            }
        });
    }

    /*public void selectSticker(View view ){
        Intent intent = new Intent(this, Dashboard.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("target_user",target_user);
        bundle.putSerializable("current_user", current_user);
        intent.putExtras(bundle);
        startActivity(intent);
    }}*/

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getFriendName());
        }
        super.onSaveInstanceState(outState);
    }

    private void createRecycleView() {
        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        reviewAdapter = new ReviewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                itemList.get(position).onItemClick(position);
                reviewAdapter.notifyItemChanged(position);
            }
        };

        reviewAdapter.setOnItemClickListener(itemClickListener);
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initialItemData (Bundle savedInstanceState){

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");

                    ItemFriend itemFriend = new ItemFriend(itemName);

                    itemList.add(itemFriend);
                }
            }
        }
    }
}

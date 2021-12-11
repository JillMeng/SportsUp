package mxh810.com.sportsup;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView currentNameTextView;
    private LinearLayout adminContainer, updateName, updatePassword, createUser, deleteUser, createGroup, editGroup, uploadAvatar;
    private String uid;
    private FirebaseUser currentUser;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private CircleImageView profileCircleImageView;
    private ImageView imageView_uploading;
    private final long ONE_MEGABYTE = 20 * 1024 * 1024;
    private static final int WRITE_SDCARD_PERMISSION_REQUEST_CODE = 1;
    private static final int CHOICE_FROM_ALBUM_REQUEST_CODE = 4;
    private Context mContext;

    public static InfoActivity newInstance() {
        return new InfoActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Initialize NavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.info);

        //setupFirebaseAuth();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                                ,Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.post:
                        startActivity(new Intent(getApplicationContext()
                                ,PostActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        return true;
                    case R.id.friends:
                        startActivity(new Intent(getApplicationContext(), FriendList.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Button reminderBtn = findViewById(R.id.setReminder);
        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, Reminder.class);
                startActivity(intent);
            }
        });

//        //Set Dashboard Selected
//        bottomNavigationView.setSelectedItemId(R.id.dashboard);
//
//        //setupFirebaseAuth();
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.dashboard:
//                        startActivity(new Intent(getApplicationContext()
//                                ,Dashboard.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.post:
//                        startActivity(new Intent(getApplicationContext()
//                                ,PostActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.info:
//                        return true;
//                    case R.id.friends:
//                        startActivity(new Intent(getApplicationContext(), FriendList.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        });
        initial();
        mContext = getApplicationContext();
        // Show name
        mDatabase.child("Users").child(uid).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentNameTextView.setText("Hello " + dataSnapshot.getValue(String.class) + "!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // Show avatar
        // https://firebase.google.com/docs/storage/android/download-files
        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference().child("images/" + uid + ".jpg");

        profileCircleImageView = findViewById(R.id.profileCircleImageView);
        profileCircleImageView.setImageResource(R.drawable.icon);
//        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Glide.with(mContext)
//                        .load(bytes)
//                        .into(profileCircleImageView);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                profileCircleImageView.setImageResource(R.drawable.icon);
//            }
//        });

        // Update name
        updateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateName.class);
                startActivity(intent);
            }
        });

        // Update password
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdatePassword.class);
                startActivity(intent);
            }
        });
    }


    private void initial() {

        currentNameTextView = findViewById(R.id.yf_textView7_ui);
        updatePassword = findViewById(R.id.yf_linearLayout_updatePassword);
        updateName = findViewById(R.id.yf_linearLayout_updateName);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}
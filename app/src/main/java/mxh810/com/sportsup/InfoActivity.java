package mxh810.com.sportsup;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class InfoActivity extends Fragment {


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
    private Context myContext;

    public static InfoActivity newInstance() {
        return new InfoActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        myContext = getContext();

        final View view = inflater.inflate(R.layout.activity_info, container, false);
        currentNameTextView = view.findViewById(R.id.yf_textView7_ui);
        updatePassword = view.findViewById(R.id.yf_linearLayout_updatePassword);
        updateName = view.findViewById(R.id.yf_linearLayout_updateName);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Show admin activity if the current user is an admin
        mDatabase.child("Users").child(uid).child("Admin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null && dataSnapshot.getValue(Double.class) != 0) {
                    adminContainer.setVisibility(View.VISIBLE);
                } else {
                    adminContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

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
        storageReference = storage.getReference().child("avatars/" + uid + ".jpg");

        profileCircleImageView = view.findViewById(R.id.profileCircleImageView);
        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Glide.with(myContext)
                        .load(bytes)
                        .into(profileCircleImageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                profileCircleImageView.setImageResource(R.drawable.icon);
            }
        });



        // Update name
        updateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, UpdateName.class);
                startActivity(intent);
            }
        });

        // Update password
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, UpdatePassword.class);
                startActivity(intent);
            }
        });

        return view;







}
}
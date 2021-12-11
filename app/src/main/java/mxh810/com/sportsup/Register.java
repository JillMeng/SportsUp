package mxh810.com.sportsup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import mxh810.com.sportsup.model.Friend;
import mxh810.com.sportsup.utils.FireBaseUtil;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";

    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private TextView loadingPleaseWait;
    private Button btnRegister;
    private ProgressBar mProgressBar;
    FirebaseDatabase mFirebaseDatabase;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FireBaseUtil firebaseUtil;

    private DatabaseReference myRef;
    private String CLIENT_REGISTRATION_TOKEN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initial();
        setupFirebaseAuth();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if(checkInputs(email, username, password)){
                    mProgressBar.setVisibility(View.VISIBLE);
                    loadingPleaseWait.setVisibility(View.VISIBLE);

                    firebaseUtil.registerNewEmail(email, password, username);
                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "Something is wrong, please check your Internet connection!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (CLIENT_REGISTRATION_TOKEN.length() < 1) {
                                    CLIENT_REGISTRATION_TOKEN = task.getResult();
                                }
                                Log.e("CLIENT_REGISTRATION_TOKEN", CLIENT_REGISTRATION_TOKEN);
                                Toast.makeText(Register.this, "CLIENT_TOKEN IS: " + CLIENT_REGISTRATION_TOKEN, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    DatabaseReference userList = mFirebaseDatabase.getReference("user");
                    Friend user = new Friend(username, CLIENT_REGISTRATION_TOKEN);
                    userList.child(username).setValue(CLIENT_REGISTRATION_TOKEN);
                }
            }
        });
    }

    private void initial(){
        mContext = Register.this;
        firebaseUtil = new FireBaseUtil(mContext);
        mEmail = (EditText) findViewById(R.id.input_email);
        mUsername = (EditText) findViewById(R.id.input_username);
        btnRegister = (Button) findViewById(R.id.btn_register);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        loadingPleaseWait = (TextView) findViewById(R.id.loadingPleaseWait);
        mPassword = (EditText) findViewById(R.id.input_password);
        mProgressBar.setVisibility(View.GONE);
        loadingPleaseWait.setVisibility(View.GONE);
    }

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //1st check: Make sure the username is not already in use
                            if(firebaseUtil.checkIfUsernameExists(username, dataSnapshot)){
                                Log.d(TAG, "username already exists");
                            }

                            //add new user to the database
                            firebaseUtil.addNewUser(email, username, password, "", "");

                            Toast.makeText(mContext, "Signup successful. Sending verification email.", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    finish();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }


        };
    }

    private boolean checkInputs(String email, String username, String password){
        if(email.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(mContext, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}

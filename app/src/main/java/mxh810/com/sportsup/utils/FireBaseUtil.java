package mxh810.com.sportsup.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import mxh810.com.sportsup.R;
import mxh810.com.sportsup.model.User;
import mxh810.com.sportsup.model.UserInfo;

public class FireBaseUtil {

    private static final String TAG = "FirebaseUtil";

    public String downloadUrl;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    StorageReference mStorageReference;

    // vars
    private final Context mContext;
    private double mPhotoUploadProgress = 0;


    public FireBaseUtil(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mContext = context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();

        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot datasnapshot) {
        User user = new User();

        for (DataSnapshot ds : datasnapshot.child(userID).getChildren()) {

            user.setUsername(ds.getValue(User.class).getUsername());
            Log.d(TAG, "checkIfUsernameExists: username: " + user.getUsername());

            if (user.getUsername().replace(".", " ").equals(username)) {
                Log.d(TAG, "checkIfUsernameExists: FOUND A MATCH: " + user.getUsername());
                return true;
            }
        }
        return false;
    }

    public void addNewUser(String email, String username, String password, String description, String profile_photo) {
        User user = new User(userID, email, username.replace(" ", "."));
        myRef.child(mContext.getString(R.string.user_account_settings))
                .child(userID)
                .setValue(user);


        UserInfo userInfo = new UserInfo(username, password, description, profile_photo);

        myRef.child(mContext.getString(R.string.user_account_settings))
                .child(userID)
                .setValue(userInfo);

    }


    public void registerNewEmail(final String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();

                        } else if (task.isSuccessful()) {
                            verificationEmail();
                            userID = mAuth.getCurrentUser().getUid();
                            Log.d(TAG, "onComplete: Authstate changed: " + userID);
                        }

                    }
                });
    }

    public void verificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                    }else {
                        Toast.makeText(mContext, "couldn't send verification email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
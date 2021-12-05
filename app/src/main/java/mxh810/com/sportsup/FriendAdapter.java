package mxh810.com.sportsup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;


import mxh810.com.sportsup.users.User;


public class FriendAdapter extends FirebaseRecyclerAdapter<User, FriendAdapter.ReviewHolder> {

    private final String TAG = FriendAdapter.class.getSimpleName();

    private static User currentUser;

    public FriendAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
        Log.i(TAG, "constructor");
        ObservableSnapshotArray<User> array = options.getSnapshots();
        Log.i(TAG, "constructor" + array.toString());

    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }


    public static class ReviewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ReviewHolder";
        private TextView user_name;
        private String user_devise_id;

        public ReviewHolder(@NonNull View friendView) {
            super(friendView);
            user_name = friendView.findViewById(R.id.user_name);


            friendView.setOnClickListener((v) -> {
                int position = getLayoutPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(friendView.getContext(), Location.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("current_user",currentUser);
                    bundle.putSerializable("target_user", new User(user_name.getText().toString(),user_devise_id));
                    intent.putExtras(bundle);
                    Log.v(TAG, user_name.getText().toString());
                    friendView.getContext().startActivity(intent);
                }

            });
        }
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list, parent, false);
        return new ReviewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ReviewHolder holder, int position, @NonNull User model) {
        holder.user_name.setText(model.getUserName());
        holder.user_devise_id = model.getToken();
        Log.i(TAG, "inBindViewHolder" + position);
    }


}

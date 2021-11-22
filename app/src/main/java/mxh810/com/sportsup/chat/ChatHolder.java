package mxh810.com.sportsup.chat;

import android.view.View;
import android.widget.TextView;

import mxh810.com.sportsup.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatHolder extends RecyclerView.ViewHolder {
    public TextView chat;
    public TextView name;
    public TextView messageTime;

    public ChatHolder(@NonNull View view) {
        super(view);
        chat = view.findViewById(R.id.show_message);
        name = view.findViewById(R.id.show_name);
        name.setVisibility(View.INVISIBLE);
        messageTime = view.findViewById(R.id.message_time);
    }
}
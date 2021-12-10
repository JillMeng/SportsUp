package mxh810.com.sportsup;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ReviewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemURL;

    public ReviewHolder(View itemView, final ItemClickListener listener){
        super(itemView);
        itemName = itemView.findViewById(R.id.item_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                        String input = itemURL.getText().toString();
                        if (!input.toLowerCase().contains("http://") && !input.toLowerCase().contains("https://")) {
                            input = "http://" + input;
                        }
                        Uri web = Uri.parse(input);
                        Intent Intent_web = new Intent(Intent.ACTION_VIEW, web);
                        itemView.getContext().startActivity(Intent_web);
                    }
                }
            }
        });
    }


}
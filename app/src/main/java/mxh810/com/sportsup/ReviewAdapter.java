package mxh810.com.sportsup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {
    private ArrayList<ItemFriend> itemList;
    private ItemClickListener listener;

    public ReviewAdapter(ArrayList<ItemFriend> itemList){
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        ItemFriend currItem = itemList.get(position);
        holder.itemName.setText(currItem.getFriendName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

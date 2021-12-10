package mxh810.com.sportsup;

public class ItemFriend implements ItemClickListener{
    private String FriendName;


    public ItemFriend(String FriendName){
        this.FriendName = FriendName;

    }



    public String getFriendName(){
        return FriendName;
    }


    @Override
    public void onItemClick(int position) {

    }
}

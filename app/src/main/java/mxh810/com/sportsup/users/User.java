package mxh810.com.sportsup.users;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 2887449439242231404L;

    private String userName;

    private String token;

    private Boolean isOnline;

    //construct method
    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public User(String userName, String token, Boolean isOnline) {
        this.userName = userName;
        this.token = token;
        this.isOnline = Boolean.TRUE;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }


}
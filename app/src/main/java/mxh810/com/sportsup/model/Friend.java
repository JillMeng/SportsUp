package mxh810.com.sportsup.model;

import java.io.Serializable;

public class Friend implements Serializable {
    private static final long serialVersionUID = 2887449439242231404L;

    private String userName;

    private String token;

    //construct method
    public Friend(){

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

    public Friend(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public Friend(String userName) {
        this.userName = userName;
    }

}

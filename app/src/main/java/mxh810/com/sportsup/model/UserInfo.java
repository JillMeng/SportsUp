package mxh810.com.sportsup.model;

public class UserInfo {
    private String description;
    private String password;
    private String profile_photo;
    private String username;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfo(String username, String password, String description, String profile_photo) {
        this.description = description;
        this.username = username;
        this.password = password;
        this.profile_photo = profile_photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "description='" + description + '\'' +
                ", password='" + password + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

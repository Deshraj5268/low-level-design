package tradingsystem.model;

public class User {
    private final String userId;
    private final String name;
    private final String phone;
    private final String email;

    public User(String userId, String name, String phone, String email) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getUserId() { return userId; }
}

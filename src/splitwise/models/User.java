package splitwise.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class User {
    private int userId;
    private String name;
    private String emailId;
    private String phone;

    public User(int userId, String name, String emailId, String phone) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(emailId, user.emailId) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, emailId, phone);
    }
}

package learn.pawpals.models;

import java.util.Objects;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private int roleId;

    // empty constructor
    public User() {
    }

    // constructor
    public User(int userId, String firstName, String lastName, String address, String phone, String email, int roleId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.roleId = roleId;
    }

    // getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId == that.userId && firstName == that.firstName && lastName == that.lastName && address == that.address && phone == that.phone && email == that.email && roleId == that.roleId;
    }
    @Override
    public int hashCode() { return Objects.hash(userId, firstName, lastName, address, phone, email, roleId); }


}

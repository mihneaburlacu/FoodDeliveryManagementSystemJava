package model;

import enums.Role;

import java.io.Serializable;

public class Client extends User implements Serializable {
    private String email;
    private String phoneNumber;


    public Client(int id, String username, String password, Role role, String email, String phone) {
        super(id, username, password, role);
        this.email = email;
        this.phoneNumber = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client nr. " + super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

package model;

import enums.Role;

public class Administrator extends User{
    private String phoneNumber;

    public Administrator(int id, String username, String password, Role role, String phone) {
        super(id, username, password, role);
        this.phoneNumber = phone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    @Override
    public String toString() {
        return "Administrator nr. " + super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

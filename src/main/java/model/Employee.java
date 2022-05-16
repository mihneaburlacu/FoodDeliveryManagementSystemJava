package model;

import enums.Role;

public class Employee extends User{
    public Employee(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }

    @Override
    public String toString() {
        return "Employee nr. " + super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

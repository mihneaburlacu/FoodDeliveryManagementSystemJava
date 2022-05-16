package model;

import dataAcces.UserService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private int clientID;
    private Date orderDate;

    public Order() {
        super();
    }

    public Order(int idClient, Date date) {
        this.clientID = idClient;
        this.orderDate = date;
    }

    public Order(int id, int idClient, Date date) {
        this.id = id;
        this.clientID = idClient;
        this.orderDate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientID, orderDate);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)", new Locale("en", "EN"));

        return "Order nr. " + this.id + " made by client nr. " + UserService.findUserById(clientID) + ", date: " + dateFormat.format(orderDate);
    }
}

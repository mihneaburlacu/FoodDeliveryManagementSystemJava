package dataAcces;

import model.MenuItem;
import model.Order;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

public class OrderService extends Service implements Serializable {
    private static final String file = "orders_list";

    public OrderService() {
        super();
    }

    public static HashMap<Order, TreeSet<MenuItem>> findAllOrders() {
        HashMap<Order, TreeSet<MenuItem>> ordersMap = new HashMap<Order, TreeSet<MenuItem>>();
        ordersMap = (HashMap<Order, TreeSet<MenuItem>>) readObjectFromFile(file);

        return ordersMap;
    }

    public static boolean addOrder(Order order, TreeSet<MenuItem> products) {
        HashMap<Order, TreeSet<MenuItem>> ordersMap = new HashMap<Order, TreeSet<MenuItem>>();

        if(OrderService.findAllOrders() != null) {
            ordersMap = OrderService.findAllOrders();
            order.setId(ordersMap.size() + 1);
        }
        else {
            order.setId(1);
        }
        order.setOrderDate(new Date());
        ordersMap.put(order, products);

        for(Order o : ordersMap.keySet()) {
            System.out.println(o);
        }

        return writeObjectInFile(ordersMap, file);
    }
}

package businessLogic;

import dataAcces.OrderService;
import dataAcces.ProductService;
import dataAcces.UserService;
import model.BaseProduct;
import model.MenuItem;
import model.Order;
import model.User;
import presentation.EmployeeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeliveryService implements IDeliveryServiceProcessing{
    private Set<MenuItem> menuList;
    private HashMap<Order, TreeSet<MenuItem>> ordersList;
    private static final DeliveryService deliveryInstance = new DeliveryService();

    /**
     * Constructor pentru clasa
     */
    public DeliveryService() {
        super();
    }

    /**
     * Metoda findAll() este o metoda pentru a gasi (a incarca din fisiere) toate produsele si toate comenzile
     */
    public void findAll() {
        menuList = ProductService.findAllProducts();
        ordersList = OrderService.findAllOrders();
    }

    /**
     * Metoda printBill realizeaza un fisier ".txt" cu nota de plata pentru produsele comandate de catre client
     * @param order
     * @param products
     */
    public void printBill(Order order, TreeSet<MenuItem> products) {
        StringBuilder sb = new StringBuilder();
        int price = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)", new Locale("en", "EN"));

        sb.append("Order nr. ");
        sb.append(order.getId() + "\n");
        //sb.append("Client nr. ");
        sb.append(UserService.findUserById(order.getClientID()) + "\n");
        sb.append("Products: ");
        int nr = 0;
        for(MenuItem product : products) {
            nr++;
            if(nr != products.size()) {
                sb.append(product.getTitle());
                sb.append(", ");
            }
            else {
                sb.append(product.getTitle());
            }
            price = price + product.getPrice();
        }
        sb.append("\n").append("Price: ").append(price).append("\n");
        sb.append("Date: ").append(dateFormat.format(order.getOrderDate()));

        try {
            FileWriter fileWriter = new FileWriter("billFile.txt", true);
            sb.append("\n\n");
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public Set<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(TreeSet<MenuItem> menuList) {
        this.menuList = menuList;
    }

    public HashMap<Order, TreeSet<MenuItem>> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(HashMap<Order, TreeSet<MenuItem>> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     * isOk() verifica daca toate produsele si toate comenzile din lista sunt ok construite (nu sunt null)
     * @return returneaza true daca toate obiectele sunt in regula si false in caz negativ.
     */
    public boolean isOk() {
        for(MenuItem prod: menuList) {
            if(prod == null) {
                return false;
            }
        }
        for(HashMap.Entry<Order, TreeSet<MenuItem>> o : ordersList.entrySet()) {
            if(o == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * sameType verifica daca obiectele sunt la fel (daca obiectul product contine obiectul input)
     * @param product
     * @param input
     * @return
     */
    public boolean sameType(Object product, String input) {
        boolean sameType = (product instanceof Integer && ((int)product + "").contains(Integer.parseInt(input) + "")) || (product instanceof Double && ((int)product + "").contains(Double.parseDouble(input) + "")) || ((product instanceof String) && ((String) product).toLowerCase().contains(input));

        return sameType;
    }

    /**
     * sameFields verifica daca o lista data are campuri egale (la fel)
     * @param products
     * @param inputs
     * @return
     */
    public boolean sameFields(Object[] products, ArrayList<String> inputs) {
        return IntStream
                .range(0, 7)
                .map(i->inputs.get(i).isBlank() || sameType(products[i], inputs.get(i)) ? 1 : 0)
                .sum() == 7;
    }

    /**
     * Creaza un produs si il adauga in lista de menu
     * @param menuItem
     * @return true daca a reusit crearea sau fals daca nu a reusit
     */
    @Override
    public boolean createProduct(MenuItem menuItem, TreeSet<MenuItem> set) {
        assert menuItem != null : "MenuItem is null (createProduct)";
        assert isOk() : "Not well constructed (createProduct)";
        boolean val = menuList.add(menuItem) && ProductService.addProduct(menuItem, set);
        assert isOk() : "Not well constructed after addition (createProduct)";
        assert menuList.contains(menuItem) : "The product is not present in the menu (createProduct)";

        return val;
    }

    /**
     * Adauga un produs in menu
     * @param menuItem
     * @return true daca a reusit adaugarea sau fals daca nu a reusit
     */
    @Override
    public boolean addProduct(MenuItem menuItem, TreeSet<MenuItem> set) {
        assert menuItem != null : "MenuItem is null (addProduct)";
        assert isOk() : "Not well constructed (addProduct)";
        boolean val = false;
        if(menuList != null)
            val = menuList.add(menuItem) && ProductService.addProduct(menuItem, set);
        else
            val = ProductService.addProduct(menuItem, set);
        assert menuList.contains(menuItem) : "The product is not present in the menu (addProduct)";
        assert isOk() : "Not well constructed after addition (addProduct)";

        return val;
    }

    /**
     * Sterge un produs din menu
     * @param menuItem
     * @return true daca a reusit stergerea sau fals daca nu a reusit
     */
    @Override
    public boolean removeProduct(MenuItem menuItem, TreeSet<MenuItem> set) {
        assert menuItem != null : "MenuItem is null (removeProduct)";
        assert isOk() : "Not well constructed (removeProduct)";
        boolean val = false;
        if(menuList != null)
            val = menuList.remove(menuItem) && ProductService.removeProduct(menuItem, set);
        else {
            val = ProductService.removeProduct(menuItem, set);
        }
        assert !menuList.contains(menuItem) : "The product is not present in the menu (removeProduct)";
        assert isOk() : "Not well constructed after removing (removeProduct)";

        return val;
    }

    /**
     * Modifica un produs din menu care are titlul "title"
     * @param menuItem
     * @return true daca a reusit modificarea sau fals daca nu a reusit modificarea
     */
    @Override
    public boolean modifyProduct(MenuItem menuItem, String title, MenuItem initialProduct, TreeSet<MenuItem> set) {
        assert menuItem != null : "MenuItem is null (modifyProduct)";
        assert isOk() : "Not well constructed (modifyProduct)";
        boolean val = ProductService.modifyProduct(menuItem, title, initialProduct, set);
        //boolean val = menuList.remove(initialProduct) && ProductService.addProduct(menuItem) && ProductService.modifyProduct(menuItem, title, initialProduct);
        assert !menuList.contains(menuItem) : "The product is not present in the menu (modifyProduct)";
        assert isOk() : "Not well constructed after removing (modifyProduct)";

        return val;
    }

    /**
     * Importa produsele din fisierul .csv primit
     * @return true daca a reusit importarea sau fals daca nu a reusit
     */
    @Override
    public boolean importProducts(TreeSet<MenuItem> setWhereTheyAreInserted) {
        assert isOk() : "Not well constructed (importProduct)";
        boolean val;
        val = false;
        try {
            File inputFile = new File("products.csv");
            InputStream inputStream = new FileInputStream(inputFile);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            menuList = buffer.lines().skip(1).map(BaseProduct::new).collect(Collectors.toSet());
            buffer.close();
            val = ProductService.addMoreProducts(menuList, setWhereTheyAreInserted);
        } catch(IOException exp) {
            exp.printStackTrace();
        }
        assert isOk() : "Not well constructed after the import (importProduct)";

        return val;
    }

    /**
     * Plaseaza o comanda
     * @param order comanda care va fi plasata
     * @param products lista de produse din comanda
     * @return true daca a reusit plasarea comenzii sau fals daca nu a reusit
     */
    @Override
    public boolean placeOrder(Order order, TreeSet<MenuItem> products) {
        assert order != null && !products.isEmpty() : "Order is empty (placeOrder)";
        //boolean val = OrderService.addOrder(order, products) && ordersList.put(order, products) == null;
        boolean val = OrderService.addOrder(order, products);
        if(val == true) {
            printBill(order, products);
            assert ordersList.containsKey(order) : "Order is not present in the list (placeOrder)";
        }
        return val;
    }

    /**
     * etoda searchProduct ne ajuta sa cautam (folosind lambda expression) produsele din menu dupa anumite criterii
     * @param stringBuilder
     * @param values
     */
    @Override
    public void searchProduct(StringBuilder stringBuilder, ArrayList<String> values) {
        menuList
                .stream()
                .filter(prod -> prod != null)
                .map(MenuItem::getValues)
                .filter(products -> sameFields(products, values))
                .forEach(prod -> stringBuilder.append(prod));
    }

    @Override
    public void generateTimeReport(int start, int end) {
        assert start >= 0 && start < end && end < 24 : "Invalid start and end time (generateTimeReport)";
        try{
            FileWriter writer = new FileWriter("GenerateTimeReport.txt");

            writer.write("Orders between " + start + " and " +end + " \n");
            //HashMap<Order, TreeSet<MenuItem>> orders = DeliveryService.deliveryInstance.getOrdersList();
            HashMap<Order, TreeSet<MenuItem>> orders = OrderService.findAllOrders();
            //Calendar calendar = Calendar.getInstance();
            orders
            //ordersList
                    .keySet()
                    .stream()
                    .filter(order -> start < order.getOrderDate().getHours() && order.getOrderDate().getHours() < end)
                    .forEach(order -> {
                        try {
                            writer.write(order + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateStockReport(int orderedCantity) {
        assert orderedCantity > 0 : "Invalid cantity (generateStockReport)";
        FileWriter writer = null;
        try {
            writer = new FileWriter("GenerateStockReport.txt");
            StringBuilder sb = new StringBuilder();
            sb.append("Products ordered more than " + orderedCantity + "\n");
            ArrayList<MenuItem> productsArrayList = new ArrayList<MenuItem>();
            //HashMap<Order, TreeSet<MenuItem>> orderMap = DeliveryService.deliveryInstance.getOrdersList();
            HashMap<Order, TreeSet<MenuItem>> orderMap = OrderService.findAllOrders();
            orderMap
                    .keySet()
                            .stream()
                                    .map(orderMap::get)
                                            .forEach(productsArrayList::addAll);
            Map<MenuItem, Long> productFrequency = productsArrayList
                    .stream()
                            .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
            ArrayList<MenuItem> orderedProducts = new ArrayList<>();
            productFrequency
                    .keySet()
                            .stream()
                                    .filter(item -> helperStockReport(productFrequency, item.getTitle()) >= 1 && productFrequency.get(item) >= orderedCantity)
                                            .forEach(item -> {
                                                sb.append(item.getTitle() + " was ordered " + productFrequency.get(item) + "\n");
                                                orderedProducts.add(item);
                                            });
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void generateDateReport(int index) {
        assert index > 0 && index <= 7: "Invalid day (generateStockReport)";
        FileWriter writer = null;
        try {
            writer = new FileWriter("GenerateDateReport.txt");
            StringBuilder sb = new StringBuilder();
            sb.append("Products ordered in the day " + returnDay(index) + ", cantity: "+ "\n");
            ArrayList<MenuItem> productsArrayList = new ArrayList<MenuItem>();
            //HashMap<Order, TreeSet<MenuItem>> orderMap = DeliveryService.deliveryInstance.getOrdersList();
            HashMap<Order, TreeSet<MenuItem>> orderMap = OrderService.findAllOrders();
            Calendar calendar = Calendar.getInstance();
            orderMap
                    .keySet()
                    .stream()
                    .filter(order -> setTime(order.getOrderDate(), calendar) && calendar.get(Calendar.DAY_OF_WEEK) == index)
                    .map(orderMap::get)
                    .forEach(productsArrayList::addAll);
            Map<MenuItem, Long> productFrequency = productsArrayList
                    .stream()
                    .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
            ArrayList<MenuItem> orderedProducts = new ArrayList<>();
            productFrequency
                    .keySet()
                    .stream()
                    .filter(item -> helperStockReport(productFrequency, item.getTitle()) >= 1)
                    .forEach(item -> {
                        sb.append(item.getTitle() + " was ordered " + productFrequency.get(item) + "\n");
                        orderedProducts.add(item);
                    });
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateLoyalCustomersReport(int orderCantity, int orderValue) {

        assert orderCantity > 0 && orderValue > 0 : "Invalid amounts (generateLoyalCustomersReport)";
        FileWriter writer = null;
        try {
            writer = new FileWriter("GenerateLoyalCustomersReport.txt");
            StringBuilder sb = new StringBuilder();
            sb.append("Clients that have ordered more than " + orderCantity + " times " + " and the price was greater than " + orderValue + ": \n" );
            ArrayList<User> usersArrayList = new ArrayList<User>();
            Map<Order, TreeSet<MenuItem>> orderMap = OrderService.findAllOrders();
            orderMap
                    .keySet()
                            .stream()
                                    .filter(order -> orderMap.get(order)
                                            .stream()
                                                    .mapToInt(MenuItem::getPrice)
                                                            .sum() >= orderValue)
                                            .forEach(order -> usersArrayList.add(UserService.findUserById(order.getClientID())));

            Map<User, Long> clientFrequency = usersArrayList
                    .stream()
                            .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
            Set<User> clients = new HashSet<User>();
            clientFrequency
                    .keySet()
                            .stream()
                                    .filter(client -> helperLoyalReport(clientFrequency, client.getId()) >= 1 && clientFrequency.get(client) >= orderCantity)
                                            .forEach(client-> {sb.append(client.toString() + "\n"); clients.add(client); });
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isNotNull(Set<MenuItem> set) {
        for(MenuItem product : set) {
            if(product == null) {
                return false;
            }
        }
        return true;
    }

    public Set<MenuItem> searchAll(Set<MenuItem> set, String title, String rating, String calories, String protein,
                                   String sodium, String fat, String price){
        assert isNotNull(set);
        Set<MenuItem> titleRes = null;
        Set<MenuItem> ratingRes = null;
        Set<MenuItem> caloriesRes = null;
        Set<MenuItem> proteinRes = null;
        Set<MenuItem> fatRes = null;
        Set<MenuItem> sodiumRes = null;
        Set<MenuItem> priceRes = null;

        if(!title.equals("")){
            titleRes = set.stream()
                    .filter(e -> e.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toSet());
        }else{
            titleRes = set;
        }
        if(!rating.equals("")){
            Double ratingVal = Double.parseDouble(rating);
            ratingRes = titleRes.stream()
                    .filter(e -> e.getRating() >= ratingVal)
                    .collect(Collectors.toSet());
        }else{
            ratingRes = titleRes;
        }
        if(!calories.equals("")){
            int caloriesVal = Integer.parseInt(calories);
            caloriesRes = ratingRes.stream()
                    .filter(e -> e.getCalories() <= caloriesVal)
                    .collect(Collectors.toSet());
        }else{
            caloriesRes = ratingRes;
        }
        if(!protein.equals("")){
            int proteinVal = Integer.parseInt(protein);
            proteinRes = caloriesRes.stream()
                    .filter(e -> e.getProtein() <= proteinVal)
                    .collect(Collectors.toSet());
        }else{
            proteinRes = caloriesRes;
        }
        if(!fat.equals("")){
            int fatVal = Integer.parseInt(fat);
            fatRes = proteinRes.stream()
                    .filter(e -> e.getFat() <= fatVal)
                    .collect(Collectors.toSet());
        }else{
            fatRes = proteinRes;
        }
        if(!sodium.equals("")){
            int sodiumVal = Integer.parseInt(sodium);
            sodiumRes = fatRes.stream()
                    .filter(e -> e.getSodium() <= sodiumVal)
                    .collect(Collectors.toSet());
        }else{
            sodiumRes = fatRes;
        }
        if(!price.equals("")){
            int priceVal = Integer.parseInt(price);
            priceRes = sodiumRes.stream()
                    .filter(e -> e.getPrice() <= priceVal)
                    .collect(Collectors.toSet());
        }else{
            priceRes = sodiumRes;
        }

        return priceRes;
    }

    public Long helperStockReport(Map<MenuItem, Long> productFrequency, String title) {
        Long val = Long.valueOf(0);

        for(MenuItem p : productFrequency.keySet()) {
            if(p.getTitle().equals(title)) {
                val++;
            }
        }
        for(MenuItem menuItem : productFrequency.keySet()) {
            if(menuItem.getTitle().equals(title)){
                productFrequency.put(menuItem, val);
                break;
            }
        }

        return val;
    }

    public Long helperLoyalReport(Map<User, Long> clientFrequency, int id) {
        Long val = Long.valueOf(0);

        for(User p : clientFrequency.keySet()) {
            if(p.getId() == id) {
                val++;
            }
        }
        for(User user : clientFrequency.keySet()) {
            if(user.getId() == id){
                clientFrequency.put(user, val);
                break;
            }
        }

        return val;
    }

    public boolean setTime(Date date, Calendar calendar) {
        calendar.setTime(date);
        return true;
    }

    public String returnDay(int index) {
        if(index == 1) {
            return "Sunday";
        }
        else if(index == 2) {
            return "Monday";
        }
        else if(index == 3) {
            return "Tuesday";
        }
        else if(index == 4) {
            return "Wednesday";
        }
        else if(index == 5) {
            return "Thursday";
        }
        else if(index == 6) {
            return "Friday";
        }
        else {
            return "Saturday";
        }
    }
}

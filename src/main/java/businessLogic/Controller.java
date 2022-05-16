package businessLogic;

import com.sun.source.tree.Tree;
import comparator.MenuItemComparator;
import dataAcces.ProductService;
import dataAcces.UserService;
import enums.Role;
import model.*;
import presentation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Controller {
    private LogView logView;
    private ClientView clientView;
    private AdministratorView administratorView;
    private EmployeeView employeeView;
    private OpenView openView;
    private int clientID;

    public Controller(LogView logView, ClientView clientView, AdministratorView administratorView) {
        this.logView = logView;
        this.clientView = clientView;
        this.administratorView = administratorView;
        this.clientID = 0;
        this.employeeView = new EmployeeView();
        this.openView = new OpenView();
        TreeSet<MenuItem> set = new TreeSet<MenuItem>(new MenuItemComparator());
        employeeView.setVisible(false);

        logView.addRegisterListener(new ActionListener(){
            String usernameInput = "";
            String passwordInput = "";
            Role roleInput = null;
            String emailInput = "";
            String phoneNumberInput = "";
            UserService userService = new UserService();

            public void actionPerformed(ActionEvent e) {
                usernameInput = logView.getUsernameTextField();
                passwordInput = logView.getPasswordTextField();
                emailInput = logView.getEmailTextField();
                phoneNumberInput = logView.getPhoneNumberTextField();
                roleInput = logView.getRoleTextField();
                boolean registered = false;
                if(roleInput == Role.CLIENT) {
                    User clientRegistered = new Client(1000, usernameInput, passwordInput, roleInput, emailInput, phoneNumberInput);
                    registered = UserService.registerUser(clientRegistered);
                    clientID = clientRegistered.getId();
                }
                else if(roleInput == Role.ADMINISTRATOR) {
                    registered = UserService.registerUser(new Administrator(1000, usernameInput, passwordInput, roleInput, phoneNumberInput));
                }
                else {
                    registered = UserService.registerUser(new Employee(1000, usernameInput, passwordInput, roleInput));
                }
                if(registered == true) {
                    logView.writeMessage("User " + usernameInput + " registered");

                    if(roleInput == Role.CLIENT) {
                        clientView.setVisible(true);
                        openClientView(set);
                    }
                    if(roleInput == Role.ADMINISTRATOR){
                        administratorView.setVisible(true);
                    }
                    if(roleInput == Role.EMPLOYEE) {
                        employeeView.setVisible(true);
                    }

                }
                else {
                    logView.writeMessage("User " + usernameInput + " not registered");
                }
            }
        });

        logView.addLogInListener(new ActionListener() {
            String usernameInput = "";
            String passwordInput = "";
            UserService userService = new UserService();

            public void actionPerformed(ActionEvent e) {
                usernameInput = logView.getUsernameLogInTextField();
                passwordInput = logView.getPasswordLogInTextField();
                User userNew = userService.loginUser(usernameInput, passwordInput);

                if(userNew != null) {
                    if(userNew.getRole() == Role.CLIENT) {
                        clientID = userNew.getId();
                        clientView.setVisible(true);
                        openClientView(set);
                    }
                    if(userNew.getRole() == Role.ADMINISTRATOR){
                        administratorView.setVisible(true);
                    }
                    if(userNew.getRole() == Role.EMPLOYEE) {
                        employeeView.setVisible(true);
                    }
                }
                else {
                    logView.writeMessage("Password or username incorrect!");
                }
            }

            //}
        });

        clientView.addSearchListener(new ActionListener() {
            String titleInput = "";
            String ratingInput = "";
            String caloriesInput = "";
            String proteinInput = "";
            String fatInput = "";
            String sodiumInput = "";
            String priceInput = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientView.getTitleTextField() != null)
                    titleInput = clientView.getTitleTextField();
                if(clientView.getRatingTextField() != null)
                    ratingInput = clientView.getRatingTextField();
                if(clientView.getCaloriesTextField() != null)
                    caloriesInput = clientView.getCaloriesTextField();
                if(clientView.getProteinTextField() != null)
                    proteinInput = clientView.getProteinTextField();
                if(clientView.getFatTextField() != null)
                    fatInput = clientView.getFatTextField();
                if(clientView.getSodiumTextField() != null)
                    sodiumInput = clientView.getSodiumTextField();
                if(clientView.getPriceTextField() != null)
                    priceInput = clientView.getPriceTextField();
                DeliveryService deliveryService = new DeliveryService();
                Set<MenuItem> resultedSet = deliveryService.searchAll(set, titleInput, ratingInput, caloriesInput, proteinInput, fatInput, sodiumInput, priceInput);
                StringBuilder sb = new StringBuilder();
                StringBuilder sbSecond = new StringBuilder();
                try {
                    FileWriter writer = new FileWriter("searchFile.txt");
                    for(MenuItem menuItem : resultedSet) {
                        sb.append(menuItem.getTitle() + "; ");
                        sbSecond.append(menuItem.toString() + "\n");
                    }
                    clientView.setSearchTextField(sb.toString());
                    writer.write(sbSecond.toString());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        clientView.addMakeAnOrderListener(new ActionListener() {
            int nrOfProducts = 0;
            //ArrayList<String> titles = new ArrayList<String>();
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeView.setVisible(true);
                employeeView.setTextAreaText("");
                nrOfProducts = Integer.parseInt(clientView.getNrProductsText());
                String titleLine = clientView.getTitlesText();
                String[] titles = titleLine.split("&");
                DeliveryService deliveryService = new DeliveryService();
                TreeSet<MenuItem> products = new TreeSet<MenuItem>(new MenuItemComparator());
                for(String t : titles) {
                    Set<MenuItem> foundProduct = deliveryService.searchAll(set, t, clientView.getRatingTextField(), clientView.getCaloriesTextField(), clientView.getProteinTextField(), clientView.getSodiumTextField(), clientView.getFatTextField(), clientView.getPriceTextField());
                    products.add(foundProduct.iterator().next());
                }
                Date date = new Date();
                Order order = new Order(1000, clientID, date);
                boolean ret = deliveryService.placeOrder(order, products);
                StringBuilder sbEmp = new StringBuilder();
                if(ret == true) {
                    clientView.writeMessage("The order was placed");
                    sbEmp.append("This products were ordered: \n");
                    for(MenuItem p : products) {
                        sbEmp.append(p.getTitle() + "\n");
                    }
                    sbEmp.append("\n");
                    employeeView.setTextAreaText(sbEmp.toString());
                }
                else {
                    clientView.writeMessage("The order was not placed");
                }
            }
        });

        administratorView.addImportListener(new ActionListener() {
            //OpenView openView = new OpenView();
            @Override
            public void actionPerformed(ActionEvent e) {
                openView.setVisible(true);
                openImportView(set, openView);
            }
        });

        administratorView.addModifyListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService deliveryService = new DeliveryService();
                Set<MenuItem> products = deliveryService.searchAll(set, administratorView.getTitle(), clientView.getRatingTextField(), clientView.getCaloriesTextField(), clientView.getProteinTextField(), clientView.getSodiumTextField(), clientView.getFatTextField(), clientView.getPriceTextField());
                MenuItem menuItem = new BaseProduct(administratorView.getTitle(), Integer.parseInt(administratorView.getPrice()), Double.parseDouble(administratorView.getRating()), Integer.parseInt(administratorView.getCalories()), Integer.parseInt(administratorView.getProtein()), Integer.parseInt(administratorView.getFat()), Integer.parseInt(administratorView.getSodium()));
                boolean ret = deliveryService.modifyProduct(menuItem, administratorView.getTitle(), products.iterator().next(), set);
                if(ret == true) {
                    administratorView.writeMessage("The product " + administratorView.getTitle() + " was modified");
                    for(int i = 0; i < 640; i++) {
                        if(openView.getTableText(i, 0).equals(administratorView.getTitle())) {
                            openView.setTableText(administratorView.getRating(), i, 1);
                            openView.setTableText(administratorView.getCalories(), i, 2);
                            openView.setTableText(administratorView.getProtein(), i, 3);
                            openView.setTableText(administratorView.getFat(), i, 4);
                            openView.setTableText(administratorView.getSodium(), i, 5);
                            openView.setTableText(administratorView.getPrice(), i, 6);
                        }
                    }
                }
                else {
                    administratorView.writeMessage("The product " + administratorView.getTitle() + " was not modified");
                }
            }
        });

        administratorView.addDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService deliveryService = new DeliveryService();
                Set<MenuItem> products = deliveryService.searchAll(set, administratorView.getTitle(), clientView.getRatingTextField(), clientView.getCaloriesTextField(), clientView.getProteinTextField(), clientView.getSodiumTextField(), clientView.getFatTextField(), clientView.getPriceTextField());
                boolean ret = deliveryService.removeProduct(products.iterator().next(), set);
                if(ret == true) {
                    administratorView.writeMessage("The product " + administratorView.getTitle() + " was removed");
                    for(int i = 0; i < 640; i++) {
                        if(openView.getTableText(i, 0).equals(administratorView.getTitle())) {
                            openView.setTableText("", i, 0);
                            openView.setTableText("", i, 1);
                            openView.setTableText("", i, 2);
                            openView.setTableText("", i, 3);
                            openView.setTableText("", i, 4);
                            openView.setTableText("", i, 5);
                            openView.setTableText("", i, 6);
                        }
                    }
                }
                else {
                    administratorView.writeMessage("The product " + administratorView.getTitle() + " was not removed");
                }
            }
        });

        administratorView.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService deliveryService = new DeliveryService();
                MenuItem menuItem = new BaseProduct(administratorView.getTitle(), Integer.parseInt(administratorView.getPrice()), Double.parseDouble(administratorView.getRating()), Integer.parseInt(administratorView.getCalories()), Integer.parseInt(administratorView.getProtein()), Integer.parseInt(administratorView.getFat()), Integer.parseInt(administratorView.getSodium()));
                boolean ret = deliveryService.addProduct(menuItem, set);
                if(ret == true) {
                    administratorView.writeMessage("The product " + administratorView.getTitle() + " was added");
                            openView.setTableText(menuItem.getTitle(), 0, 0);
                            openView.setTableText(menuItem.getRating(), 0, 1);
                            openView.setTableText(menuItem.getCalories(), 0, 2);
                            openView.setTableText(menuItem.getProtein(), 0, 3);
                            openView.setTableText(menuItem.getFat(), 0, 4);
                            openView.setTableText(menuItem.getSodium(), 0, 5);
                            openView.setTableText(menuItem.getPrice(), 0, 6);
                }
                else {
                    administratorView.writeMessage("The product " + administratorView.getTitle() + " was not added");
                }
            }
        });

        administratorView.addCreateCompositeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleLine = administratorView.getTitlesCompositeProduct();
                String[] titles = titleLine.split("&");
                DeliveryService deliveryService = new DeliveryService();
                TreeSet<MenuItem> products = new TreeSet<MenuItem>(new MenuItemComparator());
                for(String t : titles) {
                    Set<MenuItem> foundProduct = deliveryService.searchAll(set, t, clientView.getRatingTextField(), clientView.getCaloriesTextField(), clientView.getProteinTextField(), clientView.getSodiumTextField(), clientView.getFatTextField(), clientView.getPriceTextField());
                    products.add(foundProduct.iterator().next());
                }
                CompositeProduct compositeProduct = new CompositeProduct(administratorView.getTitleCompositeProduct(), products);
                if(compositeProduct != null) {
                    administratorView.writeMessage("The composite product was created");
                    administratorView.setCompositeProductTextArea(compositeProduct.toString());
                }
                else {
                    administratorView.writeMessage("The composite product was not created");
                }
            }
        });

        administratorView.addGenerateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService deliveryService = new DeliveryService();
                //deliveryService.generateTimeReport(10, 13);
                deliveryService.generateTimeReport(administratorView.getStartHour(), administratorView.getEndHour());
                //deliveryService.generateStockReport(3);
                deliveryService.generateStockReport(administratorView.getOrderCantity());
                //deliveryService.generateDateReport(2);
                deliveryService.generateDateReport(administratorView.getIndexDay());
                //deliveryService.generateLoyalCustomersReport(3, 10);
                deliveryService.generateLoyalCustomersReport(administratorView.getOrderAmount(), administratorView.getOrderValue());
                administratorView.writeMessage("Check the files .txt");
            }
        });

    }
    public void openClientView(TreeSet<MenuItem> set) {
        DeliveryService deliveryService = new DeliveryService();
        StringBuilder sb = new StringBuilder();
        //TreeSet<MenuItem> set = new TreeSet<MenuItem>(new MenuItemComparator());
        boolean ret = deliveryService.importProducts(set);
        if(ret == false) {
            clientView.writeMessage("Cannot import products");
        }
        writeInClientTable(set, sb);
        try {
            FileWriter writer = new FileWriter("allProducts.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInClientTable(TreeSet<MenuItem> set, StringBuilder sb) {
        /*
        for(MenuItem p : set) {
            String[] string = new String[7];
            string[0] = p.getTitle();
            string[1] = String.valueOf(p.getRating());
            string[2] = String.valueOf(p.getCalories());
            string[3] = String.valueOf(p.getProtein());
            string[4] = String.valueOf(p.getFat());
            string[5] = String.valueOf(p.getSodium());
            string[6] = String.valueOf(p.getPrice());
            clientView.getModel().addRow(string);
        }
        */
        int i = 0;
        for(MenuItem p : set) {
            i++;
            clientView.setTableText(p.getTitle(), i, 0);
            clientView.setTableText(p.getRating(), i, 1);
            clientView.setTableText(p.getCalories(), i, 2);
            clientView.setTableText(p.getProtein(), i, 3);
            clientView.setTableText(p.getFat(), i, 4);
            clientView.setTableText(p.getSodium(), i, 5);
            clientView.setTableText(p.getPrice(), i, 6);
            sb.append(p.toString() + "\n");


            //if(i == 640) {
                //break;
            //}

        }
    }

    public void writeInImportTable(TreeSet<MenuItem> set, OpenView openView) {
        int i = 0;
        for(MenuItem p : set) {
            i++;
            openView.setTableText(p.getTitle(), i, 0);
            openView.setTableText(p.getRating(), i, 1);
            openView.setTableText(p.getCalories(), i, 2);
            openView.setTableText(p.getProtein(), i, 3);
            openView.setTableText(p.getFat(), i, 4);
            openView.setTableText(p.getSodium(), i, 5);
            openView.setTableText(p.getPrice(), i, 6);

            //if(i == 640) {
            //break;
            //}
        }
    }

    public void openImportView(TreeSet<MenuItem> set, OpenView openView) {
        DeliveryService deliveryService = new DeliveryService();
        //TreeSet<MenuItem> set = new TreeSet<MenuItem>(new MenuItemComparator());
        boolean ret = deliveryService.importProducts(set);
        if(ret == false) {
            openView.writeMessage("Cannot import products");
        }
        writeInImportTable(set, openView);
    }


}

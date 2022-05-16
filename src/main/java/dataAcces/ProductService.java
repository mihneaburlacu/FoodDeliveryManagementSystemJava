package dataAcces;

import businessLogic.DeliveryService;
import comparator.MenuItemComparator;
import model.BaseProduct;
import model.MenuItem;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ProductService extends Service{
    private static final String file = "products_list.txt";

    public ProductService() {
        super();
    }

    public static HashSet<MenuItem> findAllProd() {
        HashSet<MenuItem> productsList = new HashSet<MenuItem>();
        productsList = (HashSet<MenuItem>) Service.readObjectFromFile(file);

        return productsList;
    }

    public static MenuItem findProductByTitle(String title) {
        HashSet<MenuItem> productsList = new HashSet<MenuItem>();
        productsList = (HashSet<MenuItem>) Service.readObjectFromFile(file);

        for(MenuItem p : productsList) {
            if(p.getTitle().compareTo(title) == 0) {
                return p;
            }
        }

        return null;
    }

    public static TreeSet<MenuItem> findAllProducts() {
        TreeSet<MenuItem> productsList = new TreeSet<MenuItem>();
        productsList = (TreeSet<MenuItem>) Service.readObjectFromFile(file);

        return productsList;
    }

    public static boolean addProduct(MenuItem menuItem, TreeSet<MenuItem> set) {
        TreeSet<MenuItem> productsList = new TreeSet<MenuItem>();
        helper();
        if(ProductService.findAllProducts() != null)
            productsList = ProductService.findAllProducts();
        else
            productsList = set;

        if(productsList.add(menuItem) && writeObjectInFile(productsList, file)) {
            return true;
        }

        return false;
    }

    public static boolean addMoreProducts(Set<MenuItem> products, TreeSet<MenuItem>setWhereTheyAreInserted) {
        //TreeSet<MenuItem> productsList = new TreeSet<MenuItem>();
        //if(ProductService.findAllProducts() != null) {
            File fileToDelete = new File(file);
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(fileToDelete);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            writer.print("");
            writer.close();
        //}
        if(ProductService.findAllProducts() != null)
            setWhereTheyAreInserted = ProductService.findAllProducts();

        for(MenuItem p : products) {
            setWhereTheyAreInserted.add(p);
        }

        if(writeObjectInFile(products, file)) {
            return true;
        }

        return false;
    }

    public static boolean removeProduct(MenuItem menuItem, TreeSet<MenuItem> set) {
        TreeSet<MenuItem> productsList = new TreeSet<MenuItem>();
        helper();
        if(ProductService.findAllProducts() != null)
            productsList = ProductService.findAllProducts();
        else
            productsList = set;

        if(productsList.remove(menuItem) == false) {
            return false;
        }
        else {
            return writeObjectInFile(productsList, file);
        }
    }

    public static boolean modifyProduct(MenuItem menuItem, String title, MenuItem initialProduct, TreeSet<MenuItem> set) {
        TreeSet<MenuItem> productsList = new TreeSet<MenuItem>();
        helper();
        if(ProductService.findAllProducts() != null)
            productsList = ProductService.findAllProducts();
        else
            productsList = set;

        if(initialProduct == null) {
            return false;
        }

        if(productsList.remove(initialProduct) == false) {
            return false;
        }

        productsList.add(menuItem);

        return writeObjectInFile(productsList, file);
    }

    public static void helper() {
        File fileToDelete = new File(file);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileToDelete);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }
}

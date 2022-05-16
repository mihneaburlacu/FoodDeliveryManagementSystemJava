package model;

import java.io.Serializable;
import java.util.TreeSet;

public class CompositeProduct extends MenuItem implements Serializable {
    private String title;
    private TreeSet<MenuItem> productSet;

    public CompositeProduct() {
        super();
    }

    public CompositeProduct(String title, TreeSet<MenuItem> prodList) {
        this.title = title;
        this.productSet = prodList;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int getPrice() {
        int price = 0;

        for(MenuItem p : productSet) {
            price = price + p.getPrice();
        }

        return price;
    }

    @Override
    public double getRating() {
        double price = 0;

        for(MenuItem p : productSet) {
            price = price + p.getRating();
        }

        return price;
    }

    @Override
    public int getCalories() {
        int price = 0;

        for(MenuItem p : productSet) {
            price = price + p.getCalories();
        }

        return price;
    }

    @Override
    public int getProtein() {
        int price = 0;

        for(MenuItem p : productSet) {
            price = price + p.getProtein();
        }

        return price;
    }

    @Override
    public int getFat() {
        int price = 0;

        for(MenuItem p : productSet) {
            price = price + p.getFat();
        }

        return price;
    }

    @Override
    public int getSodium() {
        int price = 0;

        for(MenuItem p : productSet) {
            price = price + p.getSodium();
        }

        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TreeSet<MenuItem> getProductSet() {
        return productSet;
    }

    public void setProductSet(TreeSet<MenuItem> productSet) {
        this.productSet = productSet;
    }

    public BaseProduct getBaseProducts() {
        int price = 0;
        double rating = 0;
        int calories = 0;
        int protein = 0;
        int fat = 0;
        int sodium = 0;
        String line = "";

        for(MenuItem product : productSet) {
            price = price + product.getPrice();
            rating = rating + product.getRating();
            protein = protein + product.getProtein();
            calories = calories + product.getCalories();
            sodium = sodium + product.getSodium();
            fat = fat + product.getFat();
        }

        line = title + "," + (rating/productSet.size()) + "," + calories + "," + protein + "," + sodium + "," + (int)(9.0f / 10 * price);
        BaseProduct baseProduct = new BaseProduct(line);

        return baseProduct;
    }

    @Override
    public String toString() {
        String res = "";
        res = res + "Composite product " + this.title + ":\n";
        for(MenuItem p : productSet) {
            res = res + p.toString() + "\n";
        }
        return res;
    }
}

package model;

import java.io.Serializable;
import java.lang.reflect.Field;

public class BaseProduct extends MenuItem implements Serializable {
    private String title;
    private int price;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;

    public BaseProduct() {
        super();
    }

    public BaseProduct(String title, int price, double rating, int calories, int protein, int fat, int sodium) {
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
    }

    public BaseProduct(String file) {
        String[] s;
        s = file.split(",");

        this.title = s[0];
        this.rating = Double.parseDouble(s[1]);
        this.calories = Integer.parseInt(s[2]);
        this.protein = Integer.parseInt(s[3]);
        this.fat = Integer.parseInt(s[4]);
        this.sodium = Integer.parseInt(s[5]);
        this.price = Integer.parseInt(s[6]);
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public Object[] getValues() {
        Object[] values = new Object[BaseProduct.class.getDeclaredFields().length - 1];
        Field[] fields = BaseProduct.class.getDeclaredFields();

        for(int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try{
                values[i - 1] = fields[i].get(this);
            }catch(IllegalAccessException exp) {
                exp.printStackTrace();
            }
        }

        return values;
    }

    @Override
    public String toString() {
        return "BaseProduct: " + title + " " + price + " " + rating + " " + calories + " " + protein + " " + " " + fat+ " " + sodium + " ";
    }
}

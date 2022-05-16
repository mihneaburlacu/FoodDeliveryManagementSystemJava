package model;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    public MenuItem() {
        super();
    }

    public abstract String getTitle();

    public abstract int getPrice();

    public Object[] getValues() {
        if(this instanceof CompositeProduct) {
            return ((((CompositeProduct) this).getBaseProducts().getValues()));
        }
        else if(this instanceof BaseProduct) {
            return ((BaseProduct) this).getValues();
        }
        return null;
    }

    public abstract double getRating();

    public abstract int getCalories();

    public abstract int getProtein();

    public abstract int getFat();

    public abstract int getSodium();
}

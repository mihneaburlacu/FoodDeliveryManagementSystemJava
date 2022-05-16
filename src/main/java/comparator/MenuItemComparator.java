package comparator;

import model.BaseProduct;
import model.MenuItem;
import model.User;

import java.io.Serializable;
import java.util.Comparator;

public class MenuItemComparator implements Comparator<MenuItem>, Serializable {
    @Override
    public int compare(MenuItem o1, MenuItem o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}

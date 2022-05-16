package comparator;

import model.User;

import java.io.Serializable;
import java.util.Comparator;

public class UserComparator implements Comparator<User>, Serializable {
    @Override
    public int compare(User o1, User o2) {
        if(o1.getId() > o2.getId()) {
            return 1;
        }
        else if(o1.getId() < o2.getId()){
            return -1;
        }
        else {
            return o1.getUsername().compareTo(o2.getUsername());
        }
    }
}

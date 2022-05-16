package dataAcces;

import comparator.UserComparator;
import enums.Role;
import model.Administrator;
import model.Client;
import model.User;

import java.util.ArrayList;
import java.util.TreeSet;

public class UserService extends Service{
    private static final String file = "user_list.txt";
    private static TreeSet<User> usersAllList;

    public UserService() {
        super();
    }

    public static ArrayList<User> findAllUsers() {
        ArrayList<User> usersList = new ArrayList<User>();
        usersList = (ArrayList<User>) readObjectFromFile(file);

        return usersList;
    }

    public static User findUserById(int id) {
        ArrayList<User> usersList = new ArrayList<User>();
        usersList = UserService.findAllUsers();

        for(User u : usersList) {
            if(u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public static User findUserByIdAndByRole(int id, Role role) {
        ArrayList<User> usersList = new ArrayList<User>();
        usersList = UserService.findAllUsers();

        for(User u : usersList) {
            if(u.getRole() == role && u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public static User findUserByUsernameAndByRole(String username, Role role) {
        ArrayList<User> usersList = new ArrayList<User>();
        usersList = UserService.findAllUsers();

        for(User u : usersList) {
            if(u.getRole() == role && u.getUsername().equals(username)) {
                return u;
            }
        }

        return null;
    }

    public static User loginUser(String username, String password) {
        ArrayList<User> usersList = new ArrayList<User>();
        usersList = UserService.findAllUsers();

        for(User u : usersList) {
            if((u.getUsername().compareTo(username) == 0) && (u.getPassword().compareTo(password) == 0)) {
                return u;
            }
        }

        return null;
    }

    public static boolean registerUser(User user) {
        ArrayList<User> usersList = new ArrayList<User>();
        if(UserService.findAllUsers() != null) {
            usersList = UserService.findAllUsers();
            user.setId(usersList.size() + 1);
        }
        else {
            User userAdaugare = new User(1, "mihneaburlacu", "pass", Role.CLIENT);
            usersList.add(userAdaugare);
            writeObjectInFile(usersList, file);
            user.setId(2);
        }

        if(usersList.add(user) && writeObjectInFile(usersList, file)) {
            return true;
        }

        return false;
    }
}

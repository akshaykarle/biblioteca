package com.twu28.biblioteca;

import java.util.ArrayList;

public class UserCollection {
    ArrayList<User> appUsers = new ArrayList<User>();

    public void seedUsers() {
        for(int i = 0; i < 3; i++) {
            String name = "111-111" + String.valueOf(i + 1);
            String password = "abc";
            String emailId = "user" + String.valueOf(i + 1) + "@thoughtworks.com";
            long contactNumber = 1234567890L;
            appUsers.add(new User(name, password, emailId, contactNumber));
        }
    }

    public void setAppUsers(ArrayList<User> validUsers) {
        appUsers = validUsers;
    }

    User findUserByName(String username) {
        for (User u : appUsers) {
            if(u.getUserName().equals(username))
                return u;
        }
        return null;
    }

    public User findAndAuthenticateUser(String username, String password) {
        User user = findUserByName(username);
        if(user != null && user.authenticate(password))
            return user;
        return null;
    }
}

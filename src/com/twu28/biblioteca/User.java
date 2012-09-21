package com.twu28.biblioteca;

import java.util.UUID;

class User {
    private String userName, password, emailId;
    private long phoneNumber;

    public void setUserName(String userName) {
        if(validUserNameFormat(userName))
            this.userName = userName;
        else
            System.out.println("Please Enter Valid UserName");
    }

    private boolean validUserNameFormat(String userName) {
        return userName.matches("\\d\\d\\d-\\d\\d\\d\\d");
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String userpassword, String emailAddress, int contactNumber) {
        this.setUserName(name);
        password = userpassword;
        emailId = emailAddress;
        phoneNumber = contactNumber;
    }

    public String getUserName() {
        return userName;
    }

    public boolean authenticate(String passwordToValidate) {
        return password.equals(passwordToValidate);
    }

    public void display() {
        System.out.println("Username:\t" + userName);
        System.out.println("Email ID:\t" + emailId);
        System.out.println("Phone Number:  " + phoneNumber);
    }
}

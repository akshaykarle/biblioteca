package com.twu28.biblioteca;

import java.util.UUID;

class User {
    private String userName, password, emailId;
    private long phoneNumber;

    public void setUserName(String userName) {
        if(validUserNameFormat(userName))
            this.userName = userName;
        else
            System.out.println("Please enter Valid UserName");
    }

    private boolean validUserNameFormat(String userName) {
        return userName.matches("\\d\\d\\d-\\d\\d\\d\\d");
    }

    public void setPassword(String userpassword) {
        password = userpassword;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        if(emailId.matches("\\w+\\@\\w+\\.\\w+"))
            this.emailId = emailId;
        else
            System.out.println("Please enter Valid Email ID.");
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        if(phoneNumber >= 1000000000L && phoneNumber <= 9999999999L)
            this.phoneNumber = phoneNumber;
        else
            System.out.println("Please enter Valid Phone Number.");
    }

    public User(String name, String userpassword, String emailAddress, long contactNumber) {
        this.setUserName(name);
        this.setPassword(userpassword);
        this.setEmailId(emailAddress);
        this.setPhoneNumber(contactNumber);
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

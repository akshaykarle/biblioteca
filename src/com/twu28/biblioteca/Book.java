package com.twu28.biblioteca;

/**
 * Created with IntelliJ IDEA.
 * User: akshay
 * Date: 11/09/12
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
class Book {
    String name, author, publisher;
    int id, totalCopies, reserved;

    public Book seedData() {
        id = reserved = totalCopies = 1;
        name = author = publisher = "foo";
        return this;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("ID: " +  id);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Total number of copies: " + totalCopies);
        System.out.println("Number of copies reserved: " + reserved);
    }

    public boolean reserve() {
        return false;  //To change body of created methods use File | Settings | File Templates.
    }
}

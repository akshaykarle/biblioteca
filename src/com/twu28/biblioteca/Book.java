package com.twu28.biblioteca;

class Book {
    String name, author, publisher;
    int id;
    boolean reserved = false;

    public Book seedData() {
        id = 1;
        reserved = false;
        name = author = publisher = "foo";
        return this;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("ID: " +  id);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Reserved?: " + reserved);
    }

    public void setReserve(boolean reserve) {
        reserved = reserve;
    }

    public boolean isReserved() {
        return reserved;
    }
}

package com.twu28.biblioteca;

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
        if((totalCopies - reserved) > 0) {
            reserved++;
            return true;
        }
        else
            return false;
    }
}

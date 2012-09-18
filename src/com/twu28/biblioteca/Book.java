package com.twu28.biblioteca;

class Book {
    private String name, author, publisher;
    private int id;
    private boolean reserved;

    public Book(int identifier, String bookName, String bookAuthor, String bookPublisher, boolean bookReserve) {
        id = identifier;
        reserved = bookReserve;
        name = bookName;
        author = bookAuthor;
        publisher = bookPublisher;
    }

    public void setName(String bookName) {
        name = bookName;
    }

    public void setAuthor(String bookAuthor) {
        author = bookAuthor;
    }

    public void setPublisher(String bookPublisher) {
        publisher = bookPublisher;
    }

    public void setId(int identifier) {
        id = identifier;
    }

    public void setReserve(boolean reserve) {
        reserved = reserve;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher()  {
        return publisher;
    }

    public int getId() {
        return id;
    }

    public boolean isNotReserved() {
        return !reserved;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("ID: " +  id);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Reserved?: " + reserved);
    }
}

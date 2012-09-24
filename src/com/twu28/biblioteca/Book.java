package com.twu28.biblioteca;

class Book {
    private String name, author, publisher;
    private int id;

    public Book(int identifier, String bookName, String bookAuthor, String bookPublisher) {
        id = identifier;
        name = bookName;
        author = bookAuthor;
        publisher = bookPublisher;
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

    public void display() {
        System.out.println("\nName: " + name);
        System.out.println("ID: " +  id);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
    }
}

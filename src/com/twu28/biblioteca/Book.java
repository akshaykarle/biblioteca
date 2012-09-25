package com.twu28.biblioteca;

class Book {
    private final String name;
    private final String author;
    private final String publisher;
    private final int id;

    public Book(int identifier, String bookName, String bookAuthor, String bookPublisher) {
        id = identifier;
        name = bookName;
        author = bookAuthor;
        publisher = bookPublisher;
    }

    public String getName() {
        return name;
    }

    public String getDisplayData() {
        String displayString = id + "\t" + name + "\t" + author + "\t" + publisher + "\n";
        return displayString;
    }
}

package com.twu28.biblioteca;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: akshay
 * Date: 13/09/12
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class BibliotecaLauncher {
    Book[] bk;
    public static void main(String args[]) throws IOException {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.seedData();
        biblioteca.displayWelcome();
        biblioteca.enterOption();
    }


}

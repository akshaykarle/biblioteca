package com.twu28.biblioteca;

import java.io.IOException;

public class BibliotecaLauncher {
    public static void main(String args[]) throws IOException {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.seedData();
        biblioteca.displayWelcome();
        biblioteca.enterOption();
    }
}

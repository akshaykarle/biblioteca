package com.twu28.biblioteca;

import java.io.IOException;

public class BibliotecaLauncher {
    public static void main(String args[]) throws IOException {
        UserInteractor consoleInteractor = new UserInteractor();
        consoleInteractor.seedData();
        consoleInteractor.displayWelcome();
        consoleInteractor.enterOption();
    }
}

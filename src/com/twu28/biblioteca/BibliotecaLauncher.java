package com.twu28.biblioteca;

import java.io.IOException;

public class BibliotecaLauncher {
    public static void main(String args[]) throws IOException {
        UserInteraction consoleInteraction = new UserInteraction();
        consoleInteraction.seedData();
        consoleInteraction.displayWelcome();
        consoleInteraction.enterOption();
    }
}

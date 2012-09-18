package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertTrue;

public class UserInteractorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private UserInteractor userInteractor;
    private Biblioteca bibliotecaMock;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        userInteractor = new UserInteractor();
        bibliotecaMock = createMock(Biblioteca.class);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        bibliotecaMock = null;
        userInteractor = null;
    }

    @Test
    public void ShouldDisplayWelcome() {
        userInteractor.displayWelcome();
        assertTrue(outContent.toString().contains("Welcome to Biblioteca!\n"));
    }

    @Test
    public void ShouldDisplayMenu() {
        userInteractor.displayMenu();
        assertTrue(outContent.toString().contains("Menu"));
    }

    @Test
    public void ShouldHandleInvalidOptions() throws IOException {
        userInteractor.selectOption(-1);
        assertTrue(outContent.toString().contains("Select a valid option!!"));
    }

    @Test
    public void shouldNotDisplayBooksIfNotPresent() throws IOException {
        userInteractor.selectOption(1);
        assertTrue(outContent.toString().contains("No books present in Biblioteca!!"));
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumber() throws IOException {
        userInteractor.selectOption(3);
        assertTrue(outContent.toString().contains("Please talk to Librarian. Thank you."));
    }
}

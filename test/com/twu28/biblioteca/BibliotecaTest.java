package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: akshay
 * Date: 13/09/12
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BibliotecaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private Biblioteca biblioteca;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Before
    public void setupBiblioteca() {
        biblioteca = new Biblioteca();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @After
    public void cleanUpBiblioteca() {

    }

    @Test
    public void ShouldDisplayWelcome() {
        biblioteca.displayWelcome();
        assertEquals("Welcome to Biblioteca!\n", outContent.toString());
    }

    @Test
    public void ShouldDisplayMenu() {
        biblioteca.displayMenu();
        assertTrue(outContent.toString().contains("Menu"));
    }

    @Test
    public void ShouldHandleInvalidOptions() {
        assertEquals(0, biblioteca.selectOption(-1));
    }
}

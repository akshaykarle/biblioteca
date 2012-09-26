package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.InvalidParameterException;

public class UserTest {
    private static final Factory factory = new Factory();

    @Before
    public void setUp() {
        factory.setUp();
    }

    @After
    public void cleanUp() {
        factory.cleanUp();
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidUserName() {
        boolean thrown = false;
        try{
            factory.user.setUserName("foo");
        } catch (InvalidParameterException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidEmailID() {
        boolean thrown = false;
        try{
            factory.user.setEmailId("foo.bar");
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidPhoneNumber() {
        boolean thrown = false;
        try{
            factory.user.setPhoneNumber(12345L);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void shouldSetCorrectUserName() {
        String name = "999-9999";
        factory.user.setUserName(name);
        assertEquals(name, factory.user.getUserName());
    }

    @Test
    public void shouldDisplayAllFields() {
        String displayData = factory.user.getDisplayData();
        displayData.contains(factory.user.getUserName());
        displayData.contains(factory.user.getEmailId());
        displayData.contains(String.valueOf(factory.user.getPhoneNumber()));
    }

    @Test
    public void shouldInvalidateIncorrectPassword() {
        assertFalse(factory.user.authenticate("xyz"));
    }

    @Test
    public void checkOutBookShouldAddBookToCollection() {
        factory.user.checkOutBook(factory.book);
        assertEquals(factory.book, factory.user.books.getBooks().get(0));
    }

}

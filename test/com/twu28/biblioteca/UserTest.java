package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private User user;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        user = new User("111-1111", "abc", "foo@bar.com", 1234567890L);
    }

    @After
    public void cleanUp() {
        user = null;
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidUserName() {
        user.setUserName("foo");
        assertTrue(outContent.toString().contains("Please enter Valid UserName"));
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidEmailID() {
        user.setEmailId("foo.bar");
        assertTrue(outContent.toString().contains("Please enter Valid Email"));
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidPhoneNumber() {
        user.setPhoneNumber(12345L);
        assertTrue(outContent.toString().contains("Please enter Valid Phone Number"));
    }

    @Test
    public void shouldSetCorrectUserName() {
        String name = "999-9999";
        user.setUserName(name);
        assertEquals(name, user.getUserName());
    }

    @Test
    public void shouldDisplayAllFields() {
        String displayData = user.getDisplayData();
        displayData.contains(user.getUserName());
        displayData.contains(user.getEmailId());
        displayData.contains(String.valueOf(user.getPhoneNumber()));
    }

    @Test
    public void shouldInvalidateIncorrectPassword() {
        assertFalse(user.authenticate("xyz"));
    }

    @Test
    public void checkOutBookShouldAddBookToCollection() {
        Book book = new Book(1, "foo", "bar", "example");
        user.checkOutBook(book);
        assertEquals(book, user.books.getBooks().get(0));
    }

}

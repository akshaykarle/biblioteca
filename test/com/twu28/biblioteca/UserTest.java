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
        user = new User("111-1111", "abc", "foo@bar.com", 123456890);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        user = null;
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidUserName() {
        user.setUserName("foo");
        assertTrue(outContent.toString().contains("Please Enter Valid UserName"));
    }

    @Test
    public void shouldSetCorrectUserName() {
        String name = "999-9999";
        user.setUserName(name);
        assertEquals(name, user.getUserName());
    }

    @Test
    public void shouldDisplayAllFields() {
        user.display();
        assertTrue(outContent.toString().contains(user.getUserName()));
        assertTrue(outContent.toString().contains(user.getEmailId()));
        assertTrue(outContent.toString().contains(String.valueOf(user.getPhoneNumber())));
    }

    @Test
    public void shouldInvalidateIncorrectPassword() {
        assertFalse(user.authenticate("xyz"));
    }

}

package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

public class UserTest {
    private static final CommonTestData commonTestData = new CommonTestData();

    @Before
    public void setUp() {
        commonTestData.setUp();
    }

    @After
    public void cleanUp() {
        commonTestData.cleanUp();
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidUserName() {
        boolean thrown = false;
        try{
            commonTestData.user.setUserName("foo");
        } catch (InvalidParameterException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidEmailID() {
        boolean thrown = false;
        try{
            commonTestData.user.setEmailId("foo.bar");
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidPhoneNumber() {
        boolean thrown = false;
        try{
            commonTestData.user.setPhoneNumber(12345L);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void shouldSetCorrectUserName() {
        String name = "999-9999";
        commonTestData.user.setUserName(name);
        assertEquals(name, commonTestData.user.getUserName());
    }

    @Test
    public void shouldDisplayAllFields() {
        String displayData = commonTestData.user.getDisplayData();
        displayData.contains(commonTestData.user.getUserName());
        displayData.contains(commonTestData.user.getEmailId());
        displayData.contains(String.valueOf(commonTestData.user.getPhoneNumber()));
    }

    @Test
    public void shouldInvalidateIncorrectPassword() {
        assertFalse(commonTestData.user.authenticate("xyz"));
    }

    @Test
    public void checkOutBookShouldAddBookToCollection() {
        commonTestData.user.checkOutBook(commonTestData.book);
        assertEquals(commonTestData.book, commonTestData.user.books.getBooks().get(0));
    }

}

package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
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
        //arrange
        boolean thrown = false;
        //act
        try{
            commonTestData.user.setUserName("foo");
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        //asserts
        assertTrue(thrown);
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidEmailID() {
        //arrange
        boolean thrown = false;
        //act
        try{
            commonTestData.user.setEmailId("foo.bar");
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        //asserts
        assertTrue(thrown);
    }

    @Test
    public void shouldRaiseErrorOnSettingInvalidPhoneNumber() {
        //arrange
        boolean thrown = false;
        //act
        try{
            commonTestData.user.setPhoneNumber(12345L);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        //asserts
        assertTrue(thrown);
    }

    @Test
    public void shouldSetCorrectUserName() {
        //arrange
        String name = "999-9999";
        //act
        commonTestData.user.setUserName(name);
        assertEquals(name, commonTestData.user.getUserName());
    }

    @Test
    public void shouldDisplayAllFields() {
        //act
        String displayData = commonTestData.user.getDisplayData();
        //asserts
        assertTrue(displayData.contains(commonTestData.user.getUserName()));
        assertTrue(displayData.contains(commonTestData.user.getEmailId()));
        assertTrue(displayData.contains(String.valueOf(commonTestData.user.getPhoneNumber())));
    }

    @Test
    public void shouldInvalidateIncorrectPassword() {
        //act and assert
        assertFalse(commonTestData.user.authenticate("xyz"));
    }

    @Test
    public void checkOutBookShouldAddBookToCollection() {
        //act
        commonTestData.user.checkOutBook(commonTestData.book);
        //assert
        assertEquals(commonTestData.book, commonTestData.user.books.getBooks().get(0));
    }
}

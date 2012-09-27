package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.createMock;

public class UserCollectionTest {
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
    public void ShouldFindUserByName() {
        //arrange
        commonTestData.userCollection.add(commonTestData.user);
        //act and assert
        assertEquals(commonTestData.user, commonTestData.userCollection.findUserByName(commonTestData.userName));
    }

    @Test
    public void FindAndAuthenticateUserShouldReturnCorrectUser() {
        //arrange
        commonTestData.userCollection.add(commonTestData.user);
        //act and assert
        assertEquals(commonTestData.user, commonTestData.userCollection.findAndAuthenticateUser(commonTestData.userName, commonTestData.password));
    }
}

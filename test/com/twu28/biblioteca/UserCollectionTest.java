package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.createMock;

public class UserCollectionTest {
    private final String userName = "111-1111";
    private final String password = "abc";
    private final String emailId = "foo@example.com";
    private final Long phoneNumber = 1234567890L;

    private User user;
    private UserCollection userCollection;

    @Before
    public void setUp() {
        user = new User(userName, password, emailId, phoneNumber);
        userCollection = new UserCollection();
    }

    @After
    public void cleanUp() {
        user = null;
        userCollection = null;
    }

    @Test
    public void ShouldFindUserByName() {
        userCollection.add(user);
        assertEquals(user, userCollection.findUserByName(userName));
    }

    @Test
    public void FindAndAuthenticateUserShouldReturnCorrectUser() {
        userCollection.add(user);
        assertEquals(user, userCollection.findAndAuthenticateUser(userName, password));
    }
}

package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.createMock;

public class UserCollectionTest {
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
    public void ShouldFindUserByName() {
        factory.userCollection.add(factory.user);
        assertEquals(factory.user, factory.userCollection.findUserByName(factory.userName));
    }

    @Test
    public void FindAndAuthenticateUserShouldReturnCorrectUser() {
        factory.userCollection.add(factory.user);
        assertEquals(factory.user, factory.userCollection.findAndAuthenticateUser(factory.userName, factory.password));
    }
}

package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.createMock;

public class UserCollectionTest {
    private User userMock;
    private UserCollection userCollectionMock;
    private ArrayList<User> users;

    @Before
    public void setUp() {
        userMock = createMock(User.class);
        userCollectionMock = new UserCollection();
        users = new ArrayList<User>();
    }

    @After
    public void cleanUp() {
        userMock = null;
        userCollectionMock = null;
        users = null;
    }

    @Test
    public void ShouldFindUserByName() {
        expect(userMock.getUserName()).andReturn("foo");
        replay(userMock);
        users.add(userMock);
        userCollectionMock.setAppUsers(users);
        assertEquals(userMock, userCollectionMock.findUserByName("foo"));
        verify(userMock);
    }

    @Test
    public void FindAndAuthenticateUserShouldReturnCorrectUser() {
        expect(userMock.getUserName()).andReturn("foo");
        expect(userMock.authenticate("foo")).andReturn(true);
        replay(userMock);
        users.add(userMock);
        userCollectionMock.setAppUsers(users);
        assertEquals(userMock, userCollectionMock.findAndAuthenticateUser("foo", "foo"));
        verify(userMock);
    }
}

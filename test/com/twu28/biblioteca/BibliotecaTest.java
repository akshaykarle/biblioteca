package com.twu28.biblioteca;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: akshay
 * Date: 13/09/12
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BibliotecaTest {
    @Test
    public void ShouldHandleInvalidOptions() {
        Biblioteca biblioteca = new Biblioteca();
        Assert.assertEquals(-1, biblioteca.selectOption(-1));
    }
}

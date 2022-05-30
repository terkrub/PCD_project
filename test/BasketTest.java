/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author terkrub
 */
public class BasketTest {
    
    public BasketTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItems method, of class Basket.
     */
    @Test
    public void testAddItems() {
        System.out.println("addItems");
        String name = "";
        int amount = 0;
        int expResult = 1;
        Basket instance = new Basket();
        instance.addItems(name, amount);
        assertEquals(expResult,instance.basket.size());
    }

    /**
     * Test of removeItems method, of class Basket.
     */
    @Test
    public void testRemoveItems() {
        System.out.println("removeItems");
        String name = "";
        int expResult = 0;
        Basket instance = new Basket();
        instance.addItems(name, 0);
        instance.removeItems(name);
        assertEquals(expResult,instance.basket.size());
    }

    /**
     * Test of totalItems method, of class Basket.
     */
    @Test
    public void testTotalItems() {
        System.out.println("totalItems");
        Basket instance = new Basket();
        String name = "";
        int amount = 10;
        instance.addItems(name, amount);
        int expResult = 10;
        int result = instance.totalItems();
        assertEquals(expResult, result);
    }
    
}

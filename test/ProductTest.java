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
public class ProductTest {
    
    public ProductTest() {
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
     * Test of getProductName method, of class Product.
     */
    @Test
    public void testGetProductName() {
        System.out.println("getProductName");
        Product instance = new Product("pen",10,1.99);
        String expResult = "pen";
        String result = instance.getProductName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = new Product("pen",10,1.99);
        double expResult = 1.99;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.00);

    }

    /**
     * Test of getInventories method, of class Product.
     */
    @Test
    public void testGetInventories() {
        System.out.println("getInventories");
        Product instance = new Product("pen",10,1.99);
        int expResult = 10;
        int result = instance.getInventories();
        assertEquals(expResult, result);

    }
    
}

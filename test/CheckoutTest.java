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
public class CheckoutTest {
    
    public CheckoutTest() {
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
     * Test of calculatePrice method, of class Checkout.
     */
    @Test
    public void testCalculatePrice() {
        System.out.println("calculatePrice");
        Data data = new Data();
        data.products.put("pen", new Product("pen",2,1.99));
        Basket basket = new Basket();
        basket.addItems("pen", 2);
        
        Checkout instance = new Checkout(data, basket);
        double expResult = 1.99+1.99;
        double result = instance.calculatePrice();
        assertEquals(expResult, result,0.00);
    }
    
}

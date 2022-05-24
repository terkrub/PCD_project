/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author terkrub
 */
public class Product {
    String productName;
    int inventories;
    double price;

    public String getProductName() {
        return productName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventories() {
        return inventories;
    }

    public void setInventories(int inventories) {
        this.inventories = inventories;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

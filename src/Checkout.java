
import java.awt.HeadlessException;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author terkrub
 */
public class Checkout {
    Data data;
    Basket basket;
    
    public Checkout(Data data, Basket basket){
        this.data = data;
        this.basket = basket;
    }
    
    public double calculatePrice(){
        double total = 0.00;
        
        for(String s : basket.basket.keySet()){
            total += data.products.get(s).getPrice()*basket.basket.get(s);
        }
        
        return total;
    }
    
    public boolean payAll(UpdateDB update){
        try{
            if (data.money >= calculatePrice()) {
                update.addHistory("Buy", new Date(),Double.valueOf(basket.totalItems()), calculatePrice());
                data.history.push(new History("Buy", new Date(),Double.valueOf(basket.totalItems()), calculatePrice()));
                for(String s : basket.basket.keySet()){
                    update.updateProduct(s, data.products.get(s).getInventories());
                }

                data.money -= calculatePrice();
                basket.basket.clear();
                JOptionPane.showMessageDialog(null,"Payment Sucessful !!!","Sucess",JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch(HeadlessException e){
            return false;
        }
        
        return false;
    }
}

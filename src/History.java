
import java.util.Date;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author terkrub
 */
public class History {
    String action;
    Date datetime;
    double amount;
    double totalPrice;
    
    public History(String action, Date datetime, double amount,double totalPrice){
        this.action = action;
        this.datetime = datetime;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }
}

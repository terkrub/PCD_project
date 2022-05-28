
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author terkrub
 */
public class Basket {
    HashMap <String,Integer> basket;
    
    public Basket(){
        this.basket = new HashMap();
    }
    
    public void addItems(String name, int amount){
        if (basket.containsKey(name)) {
            basket.replace(name, amount+basket.get(name));
        }
        else{
            this.basket.put(name,amount);
        }
    }
    
    public void removeItems(String name){
        this.basket.remove(name);
    }
    
    public int totalItems(){
        int result = 0;
        
        result = this.basket.values().stream().map(i -> i).reduce(result, Integer::sum);
        
        return result;
    }
}

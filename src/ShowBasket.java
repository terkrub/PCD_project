
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author terkrub
 */
public class ShowBasket implements ActionListener {
    
    StoreGUI store;
    Data data;
    JButton button;
    Basket basket;
    Product product;
    JPanel basketPanel;
    Checkout check;
    
    public ShowBasket(Data data, Basket basket,StoreGUI store){
        this.data = data;
        this.basket = basket;
        this.store = store;
        this.check = new Checkout(data,basket);
    }
    
    public void showItems(JPanel itemList){
        this.basketPanel = itemList;
        itemList.removeAll();
        itemList.setLayout(new GridLayout(basket.basket.size(),5));
        
        for(String s : basket.basket.keySet()){
            product = data.products.get(s);
            itemList.add(new JLabel(s));
            itemList.add(new JLabel(product.getPrice()+" $"));
            itemList.add(new JLabel(basket.basket.get(s)+ " pieces"));
            itemList.add(new JLabel(String.format("%.2f $",(product.getPrice()*basket.basket.get(s)))));
            button = new JButton("Edit "+ product.getProductName());
            button.addActionListener(this);
            itemList.add(button);
            button = new JButton("Remove "+ product.getProductName());
            button.addActionListener(this);
            itemList.add(button);
        }   
        
        store.totalPrice.setText(String.format("TOTAL PRICE : %.2f $", check.calculatePrice()));
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String[] str = command.split(" ");
        String pName = str[1];
        if (str.length >2) {
            pName = str[1]+" "+str[2];
        }
        product = data.products.get(pName);
        
        if (str[0].equalsIgnoreCase("Remove")) {
            System.out.println("remove");
            product.inventories += basket.basket.get(pName);
            basket.removeItems(pName);
            
            this.showItems(this.basketPanel);
            this.basketPanel.setVisible(false);
            this.basketPanel.setVisible(true);
            store.ViewBasketB.setText("VIEW BASKET(Total items : "+ basket.totalItems() +")");
        }
        else{
            System.out.println("edit");
            String userInput = JOptionPane.showInputDialog(null,"How many pieces?", null);
            
            
            
            try{
                int amount = Integer.parseInt(userInput);
                

                if (amount >0 && amount <= product.inventories + basket.basket.get(pName)) {
                    product.inventories += basket.basket.get(pName);
                    basket.removeItems(pName);
                    this.basket.addItems(pName, amount);
                    product.inventories -= amount;
                    this.showItems(this.basketPanel);
                    this.basketPanel.setVisible(false);
                    this.basketPanel.setVisible(true);
                    this.store.ViewBasketB.setText("VIEW BASKET(Total items : "+ basket.totalItems() +")");
                }
                else if (amount > product.inventories) {
                    JOptionPane.showMessageDialog(null,"Sorry we dont have enought products to sell","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter the amount you want to buy","ERROR",JOptionPane.ERROR_MESSAGE);
                }


            }catch(Exception ex){
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to buy","ERROR",JOptionPane.ERROR_MESSAGE);
                this.showItems(this.basketPanel);
                this.basketPanel.setVisible(false);
                this.basketPanel.setVisible(true);
            }
        }

    }
}

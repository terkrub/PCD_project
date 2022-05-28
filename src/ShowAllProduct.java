
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
public class ShowAllProduct implements ActionListener {
    StoreGUI store;
    Data data;
    JButton button;
    Basket basket;
    
    JPanel productpanel;
    
    public ShowAllProduct(Data data, Basket basket,StoreGUI store){
        this.data = data;
        this.basket = basket;
        this.store = store;
    }
    
    public void showProducts(JPanel productList){
        this.productpanel = productList;
        productList.removeAll();
        productList.setLayout(new GridLayout(data.products.size(),3));
        
        for(Product product : data.products.values()){
            productList.add(new JLabel(product.getProductName()+" ("+product.getInventories()+" items left)"));
            productList.add(new JLabel(String.valueOf(product.getPrice())+" $"));
            button = new JButton("Buy "+ product.getProductName());
            button.addActionListener(this);
            productList.add(button);
            
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String[] str = command.split(" ");
        Product product = data.products.get(str[1]);
        if (str[0].equalsIgnoreCase("buy")) {
            
            String userInput = JOptionPane.showInputDialog(null,"How many pieces?", null);
            
            try{
                int amount = Integer.parseInt(userInput);

                if (amount >0 && amount <= product.inventories) {
                    this.basket.addItems(str[1], amount);
                    product.inventories -= amount;
                    this.showProducts(this.productpanel);
                    this.productpanel.setVisible(false);
                    this.productpanel.setVisible(true);
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
                this.showProducts(this.productpanel);
                this.productpanel.setVisible(false);
                this.productpanel.setVisible(true);
            }
        
        }

    }
}

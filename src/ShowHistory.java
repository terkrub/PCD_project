
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author terkrub
 */
public class ShowHistory {
    Data data;
    
    public ShowHistory(Data data){
        this.data = data;
    }
    
    public void show(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        while(!data.history.isEmpty()){
            
            History history = data.history.pop();
            Object[] row = new Object[4];
            Date date = history.datetime;
            SimpleDateFormat format = new SimpleDateFormat ("yyyy/MM/dd");
            row[0] = history.action;
            row[1] = format.format(date);
            row[2] = history.amount;
            row[3] = String.format("%.2f $",history.totalPrice);
            
            model.addRow(row);
        }
    }
}

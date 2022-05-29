
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
public class UpdateDB {
    Connection conn;
    Data data;
    public UpdateDB(Connection conn,Data data){
        this.conn = conn;
        this.data = data;
    }
    
    public void updateMoney(){
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE UserInfo SET money = " + data.money + "WHERE userid = '" + data.username+"'");
        } catch (SQLException e) {
        }
    }
    
    public void addHistory(String action, Date date, Double amount, Double totalprice){
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println(sqlDate);
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO History "
                        + "VALUES('" + data.username + "', '" + action + "', '" + sqlDate +"', " + amount +", " + totalprice +")");
        } catch (SQLException e) {
               System.out.println(e.getMessage());
        }
    }
    
    public void updateProduct(String name,int inventories){
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE Products SET inventories = " + inventories + "WHERE productname = '" + name+"'");
        } catch (SQLException e) {
        }
    }
}

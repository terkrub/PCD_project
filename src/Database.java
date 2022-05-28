import java.sql.*;


public class Database {

    Data data;
    Connection conn = null;
    String url = "jdbc:derby:ShoppingApp;create=true";  //url of the DB host

    String dbusername = "pdc";  //your DB username
    String dbpassword = "pdc";   //your DB password

    public Database(){
        this.data = new Data();
        this.dbsetup();
        this.addProduct("Pen",100,0.99);
        this.addProduct("TV",100,999.99);
        this.addProduct("Kitkat",1000,1.99);
        this.addProduct("m&m",500,2.99);
        this.addProduct("Paper box",555,0.5);
        this.addProduct("iPhone11",600,1999.99);
        this.addProduct("Moniter",99,499.99);
        this.addProduct("Heater",600,99.99);
        this.addProduct("Candy",600,0.99);
        this.addProduct("Keyboard",555,95.99);
        this.importProductsDB();
    }

    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "UserInfo";

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(50), password VARCHAR(50), money DOUBLE)");
            }

            if (!checkTableExisting("Products")) {
                statement.executeUpdate("CREATE TABLE Products (productname VARCHAR(30), inventories INT, price DOUBLE)");
            }
            
            statement.close();
            

        } catch (Throwable e) {
            System.out.println("error");

        }
    }
    
    public boolean importProductsDB(){
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT productname, inventories, price FROM Products ");
            
            while(rs.next()){
               this.data.products.put(rs.getString("productname"),new Product(rs.getString("productname"), rs.getInt("inventories") ,rs.getDouble("price")));
            }
            System.out.println(this.data.products.size());
            return true;
            
        }catch(SQLException e){
            System.out.println("Error"+this.data.products.size());
            return false;
        }
        
    }
    
    public boolean addProduct(String name, int inventories, double price){
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT productname FROM Products "
                    + "WHERE productname = '" + name + "'");
            if (rs.next()) {
                System.out.println("Already have this product");
                return false;
            } else {
                
                System.out.println("no such user");
                statement.executeUpdate("INSERT INTO Products "
                    + "VALUES('" + name + "', " + inventories + ", " + price + ") ");
                
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("table");
            return false;
        }
         
    }
    
    public boolean checkUsername(String username){
        
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                System.out.println("this username already use");
                return false;
            } else {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error");
            return false;
        }
    }

    public Data checkLogin(String username, String password) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid, password, money FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                String pass = rs.getString("password");
                System.out.println("found user");
                if (password.compareTo(pass) == 0) {
                    data.money = rs.getInt("money");
                    data.username = username;
                    data.loginFlag = true;
                } else {
                    data.loginFlag = false;
                }
            } else {
                System.out.println("no such user");
                /*
                statement.executeUpdate("INSERT INTO UserInfo "
                        + "VALUES('" + username + "', '" + password + "', 0)");
                data.money = 0;
                data.loginFlag = true;
*/
            }

        } catch (SQLException ex) {
            System.out.println("no such user");
        }
        return data;

    }

    public boolean insertNewUser(String username, String password) {
        try {
            Statement statement = conn.createStatement();

            statement.executeUpdate("INSERT INTO UserInfo "
                    + "VALUES('" + username + "', '" + password + "', 0)");
            return true;

        } catch (SQLException e) {
            return false;
        }
    }



    public boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
}

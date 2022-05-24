import java.sql.*;


public class Database {

    Connection conn = null;
    String url = "jdbc:derby:ShoppingApp;create=true";  //url of the DB host

    String dbusername = "pdc";  //your DB username
    String dbpassword = "pdc";   //your DB password

    public Database(){
        this.dbsetup();
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
        Data data = new Data();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid, password, money FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                String pass = rs.getString("password");
                System.out.println("found user");
                if (password.compareTo(pass) == 0) {
                    data.money = rs.getInt("money");
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

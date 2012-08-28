/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinv;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.entity.Player;

/**
 *
 * @author Smile42RU
 */
public class DB {
    
    public static String host;
    public static String user;
    public static String password;
    public static String db;
    public static String table;
    
    public static Connection conn = null;    
    public static Statement stmt;
    
    public static void main(String[] args) {
        
    }

    static void connect() throws SQLException {
        try{
            if(conn == null){
                    String url = "jdbc:mysql://"+DB.host+"/"+DB.db;
                    Class.forName ("com.mysql.jdbc.Driver").newInstance();
                    conn = (Connection) DriverManager.getConnection (url, DB.user, DB.password);
                    System.out.println ("[SQLinv] Database connection established");

                conn.setEncoding("UTF-8");
            }
        }  catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.err.println ("[SQLinv] Cannot connect to database server");
        }
    }
    
    static void createTable() throws SQLException { 
        try{
            stmt = (Statement) conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `"+DB.table+"` (user_id varchar(50), inventory varchar(1000)) ENGINE=MyISAM DEFAULT CHARSET=utf8");
        } catch (Exception ex) {
            System.err.println ("[SQLinv] Cannot create table "+DB.table);
        }
    }
    
    static void save(Player player, String ItemsInInventoryString) throws SQLException{   
        try{
            stmt = (Statement) conn.createStatement();
            //ResultSet res = stmt.executeQuery("select user_id from "+DB.table+" where(`user_id` = '"+player.getName()+"') limit 1");

            String sql = "UPDATE "+DB.table+" SET `inventory` = '"+ItemsInInventoryString+"' WHERE (`user_id` = '"+player.getName()+"')";

            stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(sql);
        } catch(Exception ex) {
            System.err.println ("[SQLinv] Cannot save inventory for player: "+player.getName());
        }
    }
    
    static String restore(Player player) throws SQLException{
        stmt = (Statement) conn.createStatement();
        ResultSet res = stmt.executeQuery("select inventory from "+DB.table+" where(`user_id` = '"+player.getName()+"') limit 1");
        
        try{
            res.first();
            return res.getString("inventory");
        } catch(Exception ex) {
            DB.createPlayer(player);    
            
            String emptyInv = "";
            for(int i = 0; i < 36; i++){
                emptyInv = emptyInv + "0:0:0:0:"+i+":0:0:;";
            }            
            return emptyInv;
        }
    }

    static void createPlayer(Player player) throws SQLException {
        try{
            String emptyInv = "";
            for(int i = 0; i < 36; i++){
                emptyInv = emptyInv + "0:0:0:0:"+i+":0:0:;";
            }  
            String sql = "INSERT INTO "+DB.table+" (user_id,inventory) VALUES('"+player.getName()+"', '"+emptyInv+"')";

            stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(sql);
        } catch(Exception ex) {
            System.err.println ("[SQLinv] Cannot create new player");
        }
    }    
}

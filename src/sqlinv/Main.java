/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinv;

import java.io.*;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Smile42RU
 */
public class Main extends JavaPlugin implements Listener, Serializable{
    
    public static Main plugin;
    public static final Logger _log = Logger.getLogger("Minecraft");
    
    @Override
    public void onEnable(){
       Bukkit.getPluginManager().registerEvents(this, this);
       
       File fileConf = new File(getDataFolder(), "config.yml");
        if(!fileConf.exists()){
            InputStream resourceAsStream = Main.class.getResourceAsStream("/config.yml");
            getDataFolder().mkdirs();
            try {
                FileOutputStream fos = new FileOutputStream(fileConf);
                byte[] buff = new byte[65536];
                int n;
                while((n = resourceAsStream.read(buff)) > 0){
                    fos.write(buff, 0, n);
                    fos.flush();
                }
                fos.close();
                buff = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileConfiguration conf = YamlConfiguration.loadConfiguration(fileConf);
        
        DB.host = conf.getString("database.host");        
        DB.user = conf.getString("database.user");    
        DB.password = conf.getString("database.password");    
        DB.db = conf.getString("database.db");    
        DB.table = conf.getString("database.table");
        
        try{
            System.err.println("[SQLinv] Config loaded");
            DB.connect();
            DB.createTable();
            System.err.println("[SQLinv] Database connected");
        } catch(Exception ex) { 
            System.err.println("[SQLinv] Config not found or cannot connect to database");
        }
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("sqlinv")){
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("reload")){
                    File fileConf = new File(getDataFolder(), "config.yml");
                    FileConfiguration conf = YamlConfiguration.loadConfiguration(fileConf);
        
                    DB.host = conf.getString("database.host");        
                    DB.user = conf.getString("database.user");    
                    DB.password = conf.getString("database.password");    
                    DB.db = conf.getString("database.db");    
                    DB.table = conf.getString("database.table");

                    try{
                        DB.connect();
                        DB.createTable();
                        System.err.println("[SQLinv] Config reloaded");
                    } catch(Exception ex) { 
                        ex.printStackTrace();
                    }
                    
                    return true;
                }
            }
        }        
        return false;
    }
    
    
    //PlayerPickupItemEvent
    @EventHandler 
    public void onPickupItemEvent(PlayerPickupItemEvent event) throws IOException, SQLException{
        Player player = event.getPlayer();
        Inventory.checkInv(player);
    }
    
    //PlayerDropItemEvent
    @EventHandler 
    public void onDropItemEvent(PlayerDropItemEvent event) throws IOException, SQLException{
        Player player = event.getPlayer();
        Inventory.checkInv(player);
    }
    
    //PlayerItemHeldEvent
    @EventHandler 
    public void onItemHeldEvent(PlayerItemHeldEvent event) throws IOException, SQLException{
        Player player = event.getPlayer();
        Inventory.checkInv(player);
    }    
    
    //PlayerDeath and Respawn
    @EventHandler 
    public void onRespawn(PlayerRespawnEvent event) throws IOException, SQLException{
        Player player = event.getPlayer();
        Inventory.emptyInv(player);
    }  
    
    //PlayerQuit
    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws IOException, SQLException{
        Player player = event.getPlayer();
        Inventory.checkInv(player);
    }
    
    //PlayerJoin
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException, SQLException{
        Player player = event.getPlayer();
        Inventory.restoreInv(player);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
}

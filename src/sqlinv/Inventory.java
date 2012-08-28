/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinv;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Smile42RU
 */
public class Inventory {
    
    static void checkInv(Player player) {
        String[][] ItemsInInventory = new String[40][7];
        int i;
        
        System.err.println("s4");
        for(i = 0; i <= 35; i++){            
            ItemStack curItem = player.getInventory().getItem(i); 
            
            if(curItem == null){
                curItem = new ItemStack(Material.AIR);
            }
            
            int type = curItem.getTypeId();
            int amount = curItem.getAmount();
            int durability = curItem.getDurability();
            String[] indexItem = curItem.getData().toString().split("\\(");
            indexItem = indexItem[1].split("\\)");
            String finalIndexItem = indexItem[0];
            int enchlvl = 0;
            String enchtype = null;
            
            try{               
                if(curItem.containsEnchantment(Enchantment.ARROW_DAMAGE)){
                    enchtype = "ARROW_DAMAGE";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.ARROW_DAMAGE);
                } else if(curItem.containsEnchantment(Enchantment.ARROW_FIRE)){
                    enchtype = "ARROW_FIRE";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.ARROW_FIRE);                    
                } else if(curItem.containsEnchantment(Enchantment.ARROW_INFINITE)){
                    enchtype = "ARROW_INFINITE";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.ARROW_INFINITE);                    
                } else if(curItem.containsEnchantment(Enchantment.ARROW_KNOCKBACK)){
                    enchtype = "ARROW_KNOCKBACK";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK);                    
                } else if(curItem.containsEnchantment(Enchantment.DAMAGE_ALL)){
                    enchtype = "DAMAGE_ALL";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.DAMAGE_ALL);                    
                } else if(curItem.containsEnchantment(Enchantment.DAMAGE_ARTHROPODS)){
                    enchtype = "DAMAGE_ARTHROPODS";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);                    
                } else if(curItem.containsEnchantment(Enchantment.DAMAGE_UNDEAD)){
                    enchtype = "DAMAGE_UNDEAD";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);                    
                } else if(curItem.containsEnchantment(Enchantment.DIG_SPEED)){
                    enchtype = "DIG_SPEED";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.DIG_SPEED);                    
                } else if(curItem.containsEnchantment(Enchantment.DURABILITY)){
                    enchtype = "DURABILITY";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.DURABILITY);                    
                } else if(curItem.containsEnchantment(Enchantment.FIRE_ASPECT)){
                    enchtype = "FIRE_ASPECT";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.FIRE_ASPECT);                    
                } else if(curItem.containsEnchantment(Enchantment.KNOCKBACK)){
                    enchtype = "KNOCKBACK";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.KNOCKBACK);                    
                } else if(curItem.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
                    enchtype = "LOOT_BONUS_BLOCKS";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);                    
                } else if(curItem.containsEnchantment(Enchantment.LOOT_BONUS_MOBS)){
                    enchtype = "LOOT_BONUS_MOBS";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);                    
                } else if(curItem.containsEnchantment(Enchantment.OXYGEN)){
                    enchtype = "OXYGEN";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.OXYGEN);                    
                } else if(curItem.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
                    enchtype = "PROTECTION_ENVIRONMENTAL";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);                    
                } else if(curItem.containsEnchantment(Enchantment.PROTECTION_EXPLOSIONS)){
                    enchtype = "PROTECTION_EXPLOSIONS";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS);                    
                } else if(curItem.containsEnchantment(Enchantment.PROTECTION_FALL)){
                    enchtype = "PROTECTION_FALL";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.PROTECTION_FALL);                    
                } else if(curItem.containsEnchantment(Enchantment.PROTECTION_FIRE)){
                    enchtype = "PROTECTION_FIRE";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.PROTECTION_FIRE);                    
                } else if(curItem.containsEnchantment(Enchantment.PROTECTION_PROJECTILE)){
                    enchtype = "PROTECTION_PROJECTILE";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);                    
                } else if(curItem.containsEnchantment(Enchantment.SILK_TOUCH)){
                    enchtype = "SILK_TOUCH";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.SILK_TOUCH);                    
                } else if(curItem.containsEnchantment(Enchantment.WATER_WORKER)){
                    enchtype = "WATER_WORKER";
                    enchlvl = curItem.getEnchantmentLevel(Enchantment.WATER_WORKER);                    
                }
            } catch(Exception ex) {
                System.err.println("[SQLinv] Ench error #1");
            }
            
            ItemsInInventory[i][0] = String.format("%d", i);                    // place
            ItemsInInventory[i][1] = String.format("%d", type);                 // type
            ItemsInInventory[i][2] = String.format("%d", amount);               // amount
            ItemsInInventory[i][3] = String.format("%d", durability);           // durability
            ItemsInInventory[i][4] = finalIndexItem;                            // idindex  
            ItemsInInventory[i][5] = String.format("%d", enchlvl);              // ench lvl
            ItemsInInventory[i][6] = enchtype;                                  // ench type
        }
        System.err.println("s5");
        
        //getHelmet
            ItemStack helmet = new ItemStack(Material.AIR);
            if(player.getInventory().getHelmet() != null){
                helmet = player.getInventory().getHelmet();                
            }
                
            ItemsInInventory[36][0] = String.format("%d", 103);    
            ItemsInInventory[36][1] = String.format("%d", helmet.getTypeId());
            ItemsInInventory[36][2] = String.format("%d", helmet.getAmount());            
            ItemsInInventory[36][3] = String.format("%d", helmet.getDurability());
            ItemsInInventory[36][4] = String.format("%d", 0);
            ItemsInInventory[36][5] = String.format("%d", 0); 
            ItemsInInventory[36][6] = String.format("%d", 0); 
        System.err.println("s6"); 

        
        //getChestplate
            ItemStack chestplate = new ItemStack(Material.AIR);
            if(player.getInventory().getChestplate() != null){
                chestplate = player.getInventory().getChestplate();                
            }
            
            ItemsInInventory[37][0] = String.format("%d", 102); 
            ItemsInInventory[37][1] = String.format("%d", chestplate.getTypeId());
            ItemsInInventory[37][2] = String.format("%d", chestplate.getAmount());            
            ItemsInInventory[37][3] = String.format("%d", chestplate.getDurability());
            ItemsInInventory[37][4] = String.format("%d", 0);
            ItemsInInventory[37][5] = String.format("%d", 0); 
            ItemsInInventory[37][6] = String.format("%d", 0); 
        System.err.println("s7"); 

        
        //getLeggings
            ItemStack leggins = new ItemStack(Material.AIR);
            if(player.getInventory().getLeggings() != null){
                leggins = player.getInventory().getLeggings();                
            }
            
            ItemsInInventory[38][0] = String.format("%d", 101); 
            ItemsInInventory[38][1] = String.format("%d", leggins.getTypeId());
            ItemsInInventory[38][2] = String.format("%d", leggins.getAmount());
            ItemsInInventory[38][3] = String.format("%d", leggins.getDurability());
            ItemsInInventory[38][4] = String.format("%d", 0);
            ItemsInInventory[38][5] = String.format("%d", 0);  
            ItemsInInventory[38][6] = String.format("%d", 0);  
        System.err.println("s8");
            
        //getBoots
            ItemStack boots = new ItemStack(Material.AIR);
            if(player.getInventory().getBoots() != null){
                boots = player.getInventory().getBoots();                
            }   
            
            ItemsInInventory[39][0] = String.format("%d", 100); 
            ItemsInInventory[39][1] = String.format("%d", boots.getTypeId());
            ItemsInInventory[39][2] = String.format("%d", boots.getAmount());
            ItemsInInventory[39][3] = String.format("%d", boots.getDurability());
            ItemsInInventory[39][4] = String.format("%d", 0);
            ItemsInInventory[39][5] = String.format("%d", 0); 
            ItemsInInventory[39][6] = String.format("%d", 0);  
        
        String ItemsInInventoryString = new String();
        System.err.println("s9");
        
        for(String[] row : ItemsInInventory){       
            // ID:INDEXID:AMOUNT:DURABILITY:PLACE:ENCHLVL:ENCHTYPE
            ItemsInInventoryString = ItemsInInventoryString+row[1]+":"+row[4]+":"+row[2]+":"+row[3]+":"+row[0]+":"+row[5]+":"+row[6]+":;";
        }
        
        System.err.println("s10");
        
        try {
            DB.save(player, ItemsInInventoryString);
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void emptyInv(Player player) {
        int i;
        ItemStack curItem = new ItemStack(Material.AIR);
             
        curItem.setAmount(1);
        player.getInventory().setHelmet(curItem);
        player.getInventory().setChestplate(curItem);
        player.getInventory().setLeggings(curItem);
        player.getInventory().setBoots(curItem);
        player.getInventory().setItemInHand(curItem);
        
        for(i = 0; i <= 36; i++){     
            curItem.setAmount(1);
            player.getInventory().setItem(i, curItem);
        }
        
        Inventory.checkInv(player);
    }

    static void restoreInv(Player player) throws SQLException {
        ItemStack curItem = new ItemStack(Material.AIR);
        int i = 0;
        
        String resultInventoryRestore = DB.restore(player); 

        String[] invArr = resultInventoryRestore.split(";");
        for(String row : invArr){
            
            Pattern p = Pattern.compile("\\w{1,}"); //\w{1,}
            Matcher m = p.matcher(row);
            
            try{
                String[] results = new String[7]; 
                int x = 0;
                while (m.find() == true){
                    results[x] = m.group(); //System.out.println("matches1: "+m.group());
                    x++;
                }
                
                // ID:INDEXID:AMOUNT:DURABILITY:PLACE:ENCHLVL:ENCHTYPE
                // results[0]:results[1]:results[2]:results[3]:results[4]:results[5]:results[6]:
                
                if(Integer.parseInt(results[4]) >= 99){                
                    switch(Integer.parseInt(results[4])){
                        case 103:
                            curItem.setTypeId(Integer.parseInt(results[0]));                
                            curItem.setAmount(Integer.parseInt(results[2]));
                            curItem.setDurability(Short.parseShort(results[3]));
                            player.getInventory().setHelmet(curItem);
                        break;
                        case 102:
                            curItem.setTypeId(Integer.parseInt(results[0]));                
                            curItem.setAmount(Integer.parseInt(results[2]));
                            curItem.setDurability(Short.parseShort(results[3]));
                            player.getInventory().setChestplate(curItem);
                        break;
                        case 101:
                            curItem.setTypeId(Integer.parseInt(results[0]));                
                            curItem.setAmount(Integer.parseInt(results[2]));
                            curItem.setDurability(Short.parseShort(results[3]));
                            player.getInventory().setLeggings(curItem);
                        break;
                        case 100:
                            curItem.setTypeId(Integer.parseInt(results[0]));                
                            curItem.setAmount(Integer.parseInt(results[2]));
                            curItem.setDurability(Short.parseShort(results[3]));
                            player.getInventory().setBoots(curItem);
                        break;
                    }
                }   else    {
                    curItem.setTypeId(Integer.parseInt(results[0]));                
                    curItem.setAmount(Integer.parseInt(results[2]));            
                    curItem.setDurability(Short.parseShort(results[3]));
                    if(Integer.parseInt(results[5]) > 0){
                        curItem.addEnchantment(Enchantment.getByName(results[6]), Integer.parseInt(results[5]));
                    }
                    player.getInventory().setItem(Integer.parseInt(results[4]), curItem);
                }                
            } catch(Exception ex) {
                System.err.println(ex);
            }
            i++;
        }
    }
}

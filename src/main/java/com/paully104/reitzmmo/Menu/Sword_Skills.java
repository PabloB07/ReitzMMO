package com.paully104.reitzmmo.Menu;

import com.google.common.collect.Multimap;
import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

public class Sword_Skills implements Listener {

    private final int underFireDuration = API.weaponskillConfig.getInt("Swords.WeaponSkills.Under_Fire.DurationInSeconds");
    private final int underFireLevelNeeded = API.weaponskillConfig.getInt("Swords.WeaponSkills.Under_Fire.LevelRequirement");

    private static void createDisplay(Material material, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);

        Sword_Skills.setItem(Slot, item);

    }

    private static void setLore(ItemStack item, String lore) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);


    }


    public static final Inventory Sword_Skills = Bukkit.createInventory(null, 9, "Sword Skills");
    // The first parameter, is the inventory owner. I make it null to let everyone use it.
    //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
    //The third parameter, is the inventory name. This will accept chat colors.

    static {
        final int underFireDuration = API.weaponskillConfig.getInt("Swords.WeaponSkills.Under_Fire.DurationInSeconds");
        final boolean underFireEnabled = API.weaponskillConfig.getBoolean("Swords.WeaponSkills.Under_Fire.Enabled");

        if(underFireEnabled) {
            createDisplay(Material.WOODEN_SWORD, 0, "Under Fire", "Run faster for " + underFireDuration + " seconds Cost: 1 food");
        }


    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory == Sword_Skills) {
            if(null != clicked)
            {
                if(clicked.hasItemMeta())
                {
                    if (Objects.requireNonNull(clicked.getItemMeta()).getDisplayName().equalsIgnoreCase("Under Fire")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        String uuid = player.getUniqueId().toString();
                        PlayerData pd = API.Players.get(uuid);
                        int level = pd.getData().getInt("Level");
                        if(level >= underFireLevelNeeded) {
                            setLore(player.getInventory().getItemInMainHand(), "Under Fire");
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED +"[WS] Insufficient level!");
                        }
                        // Adds dirt



                    }
                    else {
                        event.setCancelled(true);
                    }
                }
            }


        }

    }
}
/*
 * Copyright (c) 2021 Stroyer
 *
 * AutoBroadcast is licensed under the MIT License;
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package net.stroyer.autobroadcast.GUIs;

import com.sun.tools.javac.jvm.Items;
import net.stroyer.autobroadcast.Methods.FillBlank;
import net.stroyer.autobroadcast.Methods.NewItem;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BroadcastSettingsGUI {

    public static Inventory mainInv;
    public static ItemStack interval = NewItem.createGuiItem(Material.CLOCK, ChatColor.GOLD + "Interval", ChatColor.YELLOW + "" + BroadcastSettings.settings.getSecondsInterval() + " seconds");
    public static ItemStack prefix = NewItem.createGuiItem(Material.NAME_TAG, ChatColor.LIGHT_PURPLE + "Prefix", BroadcastSettings.settings.getPrefix());
    public static ItemStack back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");

    public static void open(Player player){
        mainInv = Bukkit.createInventory(null, 9, "Broadcast Settings");
        interval = NewItem.createGuiItem(Material.CLOCK, ChatColor.GOLD + "Interval", ChatColor.YELLOW + "" + BroadcastSettings.settings.getSecondsInterval() + " seconds");
        mainInv.setItem(2, prefix);
        mainInv.setItem(4, interval);
        mainInv.setItem(6, back);

        mainInv = FillBlank.updateInventory(mainInv);
        player.openInventory(mainInv);
    }

    public static void invEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            MainGUI.open((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(interval)){
            intervalAdjust((Player) e.getWhoClicked());
        }
    }

    public static Inventory intervalInventory;
    public static ItemStack plus60 = NewItem.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "+60 seconds");
    public static ItemStack minus60 = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "-60 seconds");
    public static ItemStack plus10 = NewItem.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "+10 seconds");
    public static ItemStack minus10 = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "-10 seconds");
    public static ItemStack plus1 = NewItem.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "+1 seconds");
    public static ItemStack minus1 = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "-1 seconds");
    public static ItemStack currentInterval;
    public static ItemStack set = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GOLD + "Set Interval");
    public static ItemStack backInterval = NewItem.createGuiItem(Material.BARRIER, ChatColor.GOLD + "Back");

    private static int newInterval = BroadcastSettings.settings.getSecondsInterval();


    public static void intervalAdjust(Player player){
        intervalInventory = Bukkit.createInventory(null, 9, "Adjust interval");
        currentInterval = NewItem.createGuiItem(Material.CLOCK, ChatColor.BLUE + "Interval:", BroadcastSettings.settings.getSecondsInterval() + " seconds");
        intervalInventory.setItem(0, backInterval);
        intervalInventory = Bukkit.createInventory(null, 9, "Change Interval");
        intervalInventory.setItem(7, plus60);
        intervalInventory.setItem(6, plus10);
        intervalInventory.setItem(5, plus1);
        intervalInventory.setItem(4, currentInterval);
        intervalInventory.setItem(1, minus60);
        intervalInventory.setItem(2, minus10);
        intervalInventory.setItem(3, minus1);

        intervalInventory = FillBlank.updateInventory(intervalInventory);
        player.openInventory(intervalInventory);
    }

    public static void adjust(int add, Player player){
        newInterval += add;
        if(newInterval <= 0){
            newInterval = BroadcastSettings.settings.getSecondsInterval();
            return;
        }
        BroadcastSettings.settings.setInterval(newInterval);
        currentInterval = NewItem.createGuiItem(Material.CLOCK, ChatColor.BLUE + "Interval:", BroadcastSettings.settings.getSecondsInterval() + " seconds");
        intervalInventory.setItem(4, currentInterval);
//        player.closeInventory();
//        player.openInventory(intervalInventory);
        player.updateInventory();
    }

    public static void adjustEvent(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem().equals(backInterval)){
            e.getWhoClicked().openInventory(mainInv);
        }
        if(e.getCurrentItem().equals(plus1)){
            adjust(1, p);
        }
        if(e.getCurrentItem().equals(plus10)){
            adjust(10, p);
        }
        if(e.getCurrentItem().equals(plus60)){
            adjust(60, p);
        }
        if(e.getCurrentItem().equals(minus1)){
            adjust(-1, p);
        }
        if(e.getCurrentItem().equals(minus10)){
            adjust(-10, p);
        }
        if(e.getCurrentItem().equals(minus60)){
            adjust(-60, p);
        }
    }
}

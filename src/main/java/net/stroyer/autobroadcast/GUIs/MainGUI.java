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

import net.stroyer.autobroadcast.Methods.FillBlank;
import net.stroyer.autobroadcast.Methods.NewItem;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import net.stroyer.autobroadcast.Objects.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MainGUI {

    public static Inventory mainInventory;
    public static ItemStack broadcastSettings;
    public static ItemStack messages;
    public static ItemStack toggleEnable;

    public static void open(Player player){
        mainInventory = Bukkit.createInventory(null, 45, ChatColor.DARK_AQUA + "AutoBroadcast Menu");
        broadcastSettings = NewItem.createGuiItem(Material.REDSTONE, ChatColor.RED + "Broadcast Settings");
        messages = NewItem.createGuiItem(Material.PAPER, ChatColor.WHITE + "Broadcast Messages");
        mainInventory.setItem(19, messages);
        mainInventory.setItem(22, broadcastSettings);
        toggleEnable = getEnabled();
        mainInventory.setItem(25, toggleEnable);

        mainInventory = FillBlank.updateInventory(mainInventory);

        GUI main = new GUI(mainInventory, null);
        main.open(player);
    }

    public static ItemStack getEnabled(){
        if(BroadcastSettings.settings.isEnabled()){
            return NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Disable Broadcast");
        }else{
            return NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "Enable Broadcast");
        }
    }

    public static void clickEvent(InventoryClickEvent e) {
        if(e.getCurrentItem().equals(messages)){
            GUI messagesGUI = new GUI(null, mainInventory);
            messagesGUI.open((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(broadcastSettings)){

        }
        if(e.getCurrentItem().equals(toggleEnable)){
            BroadcastSettings.settings.toggleEnabled();
            updateInventory((Player) e.getWhoClicked());
        }
    }

    public static void updateInventory(@NotNull Player player){
        open(player);
    }
}

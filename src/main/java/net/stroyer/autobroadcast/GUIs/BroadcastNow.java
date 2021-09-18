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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BroadcastNow {
    public static Inventory inv = Bukkit.createInventory(null, 27, "Broadcast Now");
    public static ItemStack back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED  + " Back");
    public static ItemStack messages = NewItem.createGuiItem(Material.PAPER, ChatColor.GOLD + "Existing Message");
    public static ItemStack newMessage = NewItem.createGuiItem(Material.PAPER, ChatColor.GREEN + "New Message");

    public static void open(Player player){
        inv.setItem(18, back);
        inv.setItem(12, messages);
        inv.setItem(14, newMessage);
        inv = FillBlank.updateInventory(inv);
        player.openInventory(inv);
    }

    public static void event(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            MainGUI.open((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(messages)){
            BroadcastNowMessages.open((Player) e.getWhoClicked());
        }
    }
}

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

import net.stroyer.autobroadcast.Broadcast.Broadcast;
import net.stroyer.autobroadcast.Methods.FillBlank;
import net.stroyer.autobroadcast.Methods.NewItem;
import net.stroyer.autobroadcast.Objects.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BroadcastNowViewMessage {
    public static Inventory inv = Bukkit.createInventory(null, 27, "View message to send");
    public static ItemStack back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");
    public static ItemStack type = NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.GOLD + "Message type");
    public static ItemStack message = NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.YELLOW + "Message");
    public static ItemStack send = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "Send broadcast");

    public static Message messageMain = null;

    public static void open(Player p, Message msg){

        String preview = "";
        if(msg.getMessage().toCharArray().length <= 30){
            preview = msg.getMessage();
        }else{
            preview = msg.getMessage().substring(0, 29);
        }

        message = NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.YELLOW + "Message", preview);
        type = NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.GOLD + "Type",  msg.getType().name());


        inv.setItem(13, message);
        inv.setItem(11, type);
        inv.setItem(15, send);
        inv = FillBlank.updateInventory(inv);
        inv.setItem(26, back);
        messageMain = msg;

        p.openInventory(inv);
    }

    public static void event(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            BroadcastNowMessages.open((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(send)){
            if(messageMain != null){
                Broadcast.send(messageMain);
                e.getWhoClicked().closeInventory();
            }
        }
    }
}

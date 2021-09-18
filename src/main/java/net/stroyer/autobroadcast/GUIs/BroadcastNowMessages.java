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
import net.stroyer.autobroadcast.Objects.Message;
import net.stroyer.autobroadcast.Objects.MessageObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BroadcastNowMessages {
    public static Inventory inv = Bukkit.createInventory(null, 54, "Broadcast a message");
    public static ItemStack back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");
    public static List<MessageObject> objs = new ArrayList<>();

    public static void open(Player p){
        for(Message message : Message.messages){
            MessageObject mOb = new MessageObject(message);
            String preview;
            if (mOb.message.getMessage().toCharArray().length <= 29){
                preview = mOb.message.getMessage();
            }else{
                preview = mOb.message.getMessage().substring(0, 29);
            }

            mOb.item = NewItem.createGuiItem(Material.PAPER, ChatColor.RED + "" + mOb.message.getType().name(), mOb.message.getColor() + mOb.message.getMessage());
            objs.add(mOb);
            inv.setItem(message.getId(), mOb.item);
        }
        inv = FillBlank.updateInventory(inv);
        inv.setItem(53, back);
        p.openInventory(inv);
    }

    public static void interactEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            BroadcastNow.open((Player) e.getWhoClicked());
            return;
        }
        Message msg = null;
        for(MessageObject msgObj : objs){
            if(e.getCurrentItem().equals(msgObj.item)){
                msg = msgObj.message;
                BroadcastNowViewMessage.open((Player) e.getWhoClicked(), msg);
                break;
            }
        }
    }
}

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
import net.stroyer.autobroadcast.Methods.Send;
import net.stroyer.autobroadcast.Objects.GUI;
import net.stroyer.autobroadcast.Objects.Message;
import net.stroyer.autobroadcast.Objects.MessageObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static net.stroyer.autobroadcast.GUIs.MainGUI.mainInventory;
import static net.stroyer.autobroadcast.GUIs.MainGUI.messages;

public class MessagesGUI {
    public static Inventory inv;
    public static List<MessageObject> messageItems = new ArrayList<MessageObject>();
    public static ItemStack newMessage;
    public static ItemStack back;

    public static void open(Player player){
        inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Messages");
        newMessage = NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.GREEN + "New Message");
        for(Message message : Message.messages){
            MessageObject mOb = new MessageObject(message);
            messageItems.add(mOb);
            inv.setItem(message.getId(), mOb.item);
        }

        back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");
        inv.setItem(45, back);
        inv.setItem(53, newMessage);

        inv = FillBlank.updateInventory(inv);
        GUI messagesGUI = new GUI(inv, mainInventory);
        messagesGUI.open(player);
    }

    public static void invInteract(InventoryClickEvent e){
        if(e.getCurrentItem().equals(newMessage)){
            NewMessage.openInventory((Player) e.getWhoClicked());
            return;
        }
        if(e.getCurrentItem().equals(back)){
            MainGUI.open((Player) e.getWhoClicked());
            return;
        }
        if(e.getClick().equals(ClickType.RIGHT)){
            for(MessageObject msgObj : messageItems){
                if(e.getCurrentItem().equals(msgObj.item)){
                    messageSettings(msgObj.message, (Player) e.getWhoClicked());
                    return;
                }
            }
        }
        if(e.getClick().equals(ClickType.LEFT)){
            for(MessageObject msgObj : messageItems){
                if(e.getCurrentItem().equals(msgObj.item)){
                    Send.message((Player) e.getWhoClicked(), "You will now be sent the message you left clicked.");
                    Player p = (Player)e.getWhoClicked();
                    p.sendMessage(" ");
                    p.sendMessage(" ");
                    p.sendMessage(" ");
                    Broadcast.sendPlayer(p, msgObj.message);
                    p.closeInventory();
                    return;
                }
            }
        }

    }

    public static void messageSettings(Message message, Player player){
        Message.messages.remove(message);
        open(player);
    }
}

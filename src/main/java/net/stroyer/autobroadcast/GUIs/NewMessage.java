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

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.stroyer.autobroadcast.Methods.FillBlank;
import net.stroyer.autobroadcast.Methods.NewItem;
import net.stroyer.autobroadcast.Methods.Send;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import net.stroyer.autobroadcast.Objects.GUI;
import net.stroyer.autobroadcast.Objects.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;

public class NewMessage {
    private static int id;
    private static String message = "Demo Message";
    private static ChatMessageType type = ChatMessageType.CHAT;
    private static ChatColor color = ChatColor.WHITE;
    public static Inventory inv;
    public static ItemStack typeItem;
    public static ItemStack colorItem;
    public static ItemStack messageItem;
    public static ItemStack create;

    public static void openInventory(Player player){

        inv = Bukkit.createInventory(null, 27, "Create message");
        typeItem = NewItem.createGuiItem(Material.REDSTONE_TORCH, ChatColor.DARK_PURPLE + "Message type", "Currently: " + type.name());
        colorItem = NewItem.createGuiItem(Material.WHITE_WOOL, ChatColor.AQUA + "Chat Color", color + "This is color the message will be.");
        messageItem = NewItem.createGuiItem(Material.PAPER, ChatColor.WHITE + "Message", color + message);
        create = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "Create Message");

        inv.setItem(10, typeItem);
        inv.setItem(12, colorItem);
        inv.setItem(14, messageItem);
        inv.setItem(16, create);

        inv = FillBlank.updateInventory(inv);
        GUI gui = new GUI(inv, MessagesGUI.inv);
        gui.open(player);
    }

    public static void inventoryInteract(InventoryClickEvent e){
        if(e.getCurrentItem().equals(colorItem)){
            pickColor((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(darkRed)){
            color = ChatColor.DARK_RED;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(red)){
            color = ChatColor.RED;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(gold
        )){
            color = ChatColor.GOLD;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(yellow)){
            color = ChatColor.YELLOW;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(darkGreen)){
            color = ChatColor.DARK_GREEN;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(green)){
            color = ChatColor.GREEN;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(aqua)){
            color = ChatColor.AQUA;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(darkAqua)){
            color = ChatColor.DARK_AQUA;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(darkBlue)){
            color = ChatColor.DARK_BLUE;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(blue)){
            color = ChatColor.BLUE;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(lightPurple)){
            color = ChatColor.LIGHT_PURPLE;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(darkPurple)){
            color = ChatColor.DARK_PURPLE;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(white)){
            color = ChatColor.WHITE;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(gray)){
            color = ChatColor.GRAY;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(darkGray)){
            color = ChatColor.DARK_GRAY;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(black)){
            color = ChatColor.BLACK;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(typeItem)){
            pickType((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(ActionBarItem)){
            type = ChatMessageType.ACTION_BAR;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(ChatItem)){
            type = ChatMessageType.CHAT;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(title)){
            type = ChatMessageType.SYSTEM;
            openInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(messageItem)){
            newMessage((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(create)){
            createMessage((Player) e.getWhoClicked());
        }
    }

    public static Inventory pickColorInv;
    public static ItemStack darkRed = NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Dark Red");
    public static ItemStack red = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Red");
    public static ItemStack gold = NewItem.createGuiItem(Material.GOLD_BLOCK, ChatColor.GOLD + "Gold");
    public static ItemStack yellow = NewItem.createGuiItem(Material.YELLOW_CONCRETE, ChatColor.YELLOW + "Yellow");
    public static ItemStack darkGreen = NewItem.createGuiItem(Material.GREEN_CONCRETE_POWDER,ChatColor.DARK_GREEN + "Dark Green");
    public static ItemStack green = NewItem.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN +"Green");
    public static ItemStack aqua = NewItem.createGuiItem(Material.LIGHT_BLUE_CONCRETE_POWDER, ChatColor.AQUA +"Aqua");
    public static ItemStack darkAqua = NewItem.createGuiItem(Material.BLUE_SHULKER_BOX, ChatColor.DARK_AQUA + "Dark Aqua");
    public static ItemStack darkBlue = NewItem.createGuiItem(Material.BLUE_CONCRETE, ChatColor.DARK_BLUE + "Dark Blue");
    public static ItemStack blue = NewItem.createGuiItem(Material.BLUE_STAINED_GLASS, ChatColor.BLUE + "Blue");
    public static ItemStack lightPurple = NewItem.createGuiItem(Material.PURPLE_CONCRETE_POWDER, ChatColor.LIGHT_PURPLE + "Light Purple");
    public static ItemStack darkPurple = NewItem.createGuiItem(Material.PURPLE_GLAZED_TERRACOTTA, ChatColor.DARK_PURPLE + "Dark Purple");
    public static ItemStack white = NewItem.createGuiItem(Material.WHITE_CONCRETE_POWDER, ChatColor.WHITE + "White");
    public static ItemStack gray = NewItem.createGuiItem(Material.GRAY_CONCRETE_POWDER, ChatColor.GRAY + "Gray");
    public static ItemStack darkGray = NewItem.createGuiItem(Material.GRAY_TERRACOTTA, ChatColor.DARK_GRAY + "Dark Gray");
    public static ItemStack black = NewItem.createGuiItem(Material.BLACK_CONCRETE_POWDER, ChatColor.BLACK + "Black");

    public static void pickColor(Player player){
        //16 colors
        pickColorInv = Bukkit.createInventory(null, 18, "Pick your message color");
        pickColorInv.setItem(0, darkRed);
        pickColorInv.setItem(1, red);
        pickColorInv.setItem(2, gold);
        pickColorInv.setItem(3, yellow);
        pickColorInv.setItem(4, darkGreen);
        pickColorInv.setItem(5, green);
        pickColorInv.setItem(6, aqua);
        pickColorInv.setItem(7, darkAqua);
        pickColorInv.setItem(8, darkBlue);
        pickColorInv.setItem(9, blue);
        pickColorInv.setItem(10, lightPurple);
        pickColorInv.setItem(11, darkPurple);
        pickColorInv.setItem(12, white);
        pickColorInv.setItem(13, gray);
        pickColorInv.setItem(14, darkGray);
        pickColorInv.setItem(15, black);

        inv = FillBlank.updateInventory(inv);

        player.openInventory(pickColorInv);
    }

    public static Inventory typeInv;
    public static ItemStack ActionBarItem = NewItem.createGuiItem(Material.EXPERIENCE_BOTTLE, ChatColor.GREEN + "Action Bar");
    public static ItemStack ChatItem = NewItem.createGuiItem(Material.PAPER, ChatColor.GOLD + "Chat Message");
    public static ItemStack title = NewItem.createGuiItem(Material.EMERALD, ChatColor.AQUA + "Title Message");

    public static void pickType(Player player){
        typeInv = Bukkit.createInventory(null, 9, "Message type");
        typeInv.setItem(2, ChatItem);
        typeInv.setItem(4, ActionBarItem);
        typeInv.setItem(6, title);
        typeInv = FillBlank.updateInventory(typeInv);
        player.openInventory(typeInv);
    }

    public static Boolean waitingForInput = false;
    public static Player playerWaitingForInput;

    public static void newMessage(Player player){
        playerWaitingForInput = player;
        waitingForInput = true;
        player.closeInventory();
        TextComponent text = new TextComponent(ChatColor.GOLD + "Type the message you wish to broadcast or click me to cancel. " + ChatColor.GRAY + "Note: You can use chat codes like &4, &c, etc.");
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/b cancel"));
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.RED + "Cancel Input")));
        player.spigot().sendMessage(text);
    }

    public static void canceledInput(Player player){
        openInventory(player);
        playerWaitingForInput = null;
        waitingForInput = false;
    }

    public static void recievedInput(String msg, Player player){
        playerWaitingForInput = null;
        waitingForInput = false;
        message = msg;
        Send.message(player, "Message was set to: ");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.spigot().sendMessage(type, new TextComponent("       " + BroadcastSettings.settings.getPrefix()));
        player.sendMessage("");
        player.spigot().sendMessage(type, new TextComponent(color + message));
        player.sendMessage("");
        player.sendMessage("");
        openInventory(player);
    }

    public static void createMessage(Player player){
        Message msg = new Message(type, message, color);
        MessagesGUI.open(player);
        message = "Demo message";
        type = ChatMessageType.CHAT;
        color = ChatColor.WHITE;
    }
}

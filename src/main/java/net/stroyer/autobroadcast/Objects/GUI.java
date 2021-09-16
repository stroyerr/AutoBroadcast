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

package net.stroyer.autobroadcast.Objects;

import net.stroyer.autobroadcast.GUIs.MainGUI;
import net.stroyer.autobroadcast.GUIs.MessagesGUI;
import net.stroyer.autobroadcast.GUIs.NewMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    private static List<GUI> guis = new ArrayList<>();

    private Inventory thisInv;
    private Inventory priorInv;

    public GUI(@NotNull Inventory inv, @Nullable Inventory prior){
        this.thisInv = inv;
        this.priorInv = prior;
        guis.add(this);
    }

    public void closeThisInventory(Player player){
        if(this.priorInv == null){
            if(player.getInventory() == null){
                return;
            }
            player.closeInventory(InventoryCloseEvent.Reason.UNKNOWN);
        }else{
            player.closeInventory(InventoryCloseEvent.Reason.UNKNOWN);
            player.openInventory(this.priorInv);
            this.thisInv = this.priorInv;
            this.priorInv = findGUI(this.thisInv).priorInv;
            player.updateInventory();
        }
    }

    public void open(@NotNull Player player){
        player.closeInventory(InventoryCloseEvent.Reason.UNKNOWN);
        player.openInventory(this.thisInv);
    }

    public static GUI findGUI(@NotNull Inventory inventoryToFindFor){
        for(GUI gui : guis){
            if(gui.thisInv.equals(inventoryToFindFor)){
                return gui;
            }
        }

        return null;

    }

    public Inventory getThisInv(){
        return this.thisInv;
    }

    public Inventory getPriorInv(){
        return this.priorInv;
    }

    public void clickEvent(InventoryClickEvent e) {
        if(e.getInventory().equals(MainGUI.mainInventory)){
            MainGUI.clickEvent(e);
        }
        if(e.getInventory().equals(MessagesGUI.inv)){
            NewMessage.openInventory((Player) e.getWhoClicked());
        }
    }
}

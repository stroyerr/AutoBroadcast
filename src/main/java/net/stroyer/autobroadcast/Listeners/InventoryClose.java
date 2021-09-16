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

package net.stroyer.autobroadcast.Listeners;

import net.stroyer.autobroadcast.GUIs.MainGUI;
import net.stroyer.autobroadcast.GUIs.MessagesGUI;
import net.stroyer.autobroadcast.GUIs.NewMessage;
import net.stroyer.autobroadcast.Main;
import net.stroyer.autobroadcast.Objects.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {
    @EventHandler
    public static void closedInventory(InventoryCloseEvent e){
//        if(GUI.findGUI(e.getInventory()) == null){
//            return;
//        }else{
//            if(e.getReason().equals(InventoryCloseEvent.Reason.UNKNOWN)){
//                return;
//            }
//            GUI gui = GUI.findGUI(e.getInventory());
//            gui = new GUI(GUI.findGUI(e.getInventory()).getPriorInv(), GUI.findGUI(GUI.findGUI(e.getInventory()).getPriorInv()).getPriorInv());
//            gui.open((Player) e.getPlayer());
////            e.getPlayer().closeInventory();
////            e.getPlayer().openInventory(GUI.findGUI(e.getInventory()).getPriorInv());
//            //GUI.findGUI(e.getInventory()).closeThisInventory((Player) e.getPlayer());
//        }

        if (e.getInventory().equals(MainGUI.mainInventory)) {
            return;
        }
        if(e.getInventory().equals(MessagesGUI.inv)){
            e.getPlayer().openInventory(MainGUI.mainInventory);
            return;
        }
        if(e.getInventory().equals(NewMessage.inv)){
            e.getPlayer().openInventory(MessagesGUI.inv);
            return;
        }
    }
}

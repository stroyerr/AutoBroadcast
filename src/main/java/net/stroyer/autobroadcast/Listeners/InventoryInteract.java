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

import net.stroyer.autobroadcast.GUIs.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryInteract implements Listener {
    @EventHandler
    public static void playerInteract(InventoryClickEvent e) {
        if(e.getInventory().equals(MainGUI.mainInventory)){
            MainGUI.clickEvent(e);
            e.setCancelled(true);
        }
        if(e.getInventory().equals(MessagesGUI.inv)){
            MessagesGUI.invInteract(e);
            e.setCancelled(true);
        }
        if(e.getInventory().equals(NewMessage.inv) || e.getInventory().equals(NewMessage.pickColorInv) || e.getInventory().equals(NewMessage.typeInv)){
            e.setCancelled(true);
            NewMessage.inventoryInteract(e);
        }
        if(e.getInventory().equals(BroadcastSettingsGUI.mainInv)){
            e.setCancelled(true);
            BroadcastSettingsGUI.invEvent(e);
        }
        if(e.getInventory().equals(BroadcastSettingsGUI.intervalInventory)){
            e.setCancelled(true);
            BroadcastSettingsGUI.adjustEvent(e);
        }
        if(e.getInventory().equals(BroadcastNow.inv)){
            BroadcastNow.event(e);
            e.setCancelled(true);
        }
        if(e.getInventory().equals(BroadcastNowMessages.inv)){
            e.setCancelled(true);
            BroadcastNowMessages.interactEvent(e);
        }
        if(e.getInventory().equals(BroadcastNowViewMessage.inv)){
            e.setCancelled(true);
            BroadcastNowViewMessage.event(e);
        }
    }
}

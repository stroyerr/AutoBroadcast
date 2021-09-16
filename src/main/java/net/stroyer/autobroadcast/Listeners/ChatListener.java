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

import io.papermc.paper.event.player.AsyncChatEvent;
import net.stroyer.autobroadcast.GUIs.BroadcastSettingsGUI;
import net.stroyer.autobroadcast.GUIs.NewMessage;
import net.stroyer.autobroadcast.Methods.GetColoredString;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public static void playerInput(PlayerChatEvent e){
        if(!NewMessage.waitingForInput && !BroadcastSettingsGUI.waitingInput){
            return;
        }
        if(NewMessage.playerWaitingForInput != null){
            if(NewMessage.playerWaitingForInput.equals(e.getPlayer())){
                String newString = GetColoredString.getRawWithColors(e.getMessage());
                NewMessage.recievedInput(newString, e.getPlayer());
                e.setCancelled(true);
                return;
            }
        }else if(BroadcastSettingsGUI.playerToWait != null){
            if(BroadcastSettingsGUI.playerToWait.equals(e.getPlayer())){
                    String newString = GetColoredString.getRawWithColors(e.getMessage());
                    BroadcastSettingsGUI.gotPrefix(e.getPlayer(), newString);
                    e.setCancelled(true);
                    return;
            }
        }else return;

    }
}

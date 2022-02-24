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

package net.stroyer.autobroadcast.Broadcast;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import net.stroyer.autobroadcast.Objects.Message;
import net.stroyer.autobroadcast.Util.Playsound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Random;

public class Broadcast {

    private static int currentId = 0;

    public static void broadcastMessage(){
        if(Message.messages.size() == 0){
            return;
        }
        if(BroadcastSettings.settings.isRandomised()){
            Random random = new Random();
            int i;
            if(Message.messages.size() == 1){
                i = 0;
            }else{
                i = random.nextInt(Message.messages.size()-1);
            }
            send(Message.messages.get(i));
        }else{
            if(Message.messages.size() == currentId){
            currentId = 0;
        }
                send(Message.messages.get(currentId));
                currentId++;
        }
    }

    public static void send(Message message){
        Playsound.all();
        for(Player player : Bukkit.getOnlinePlayers()){
            if(message.getType().equals(ChatMessageType.ACTION_BAR)){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message.getMessage()));
            }else if(message.getType().equals(ChatMessageType.CHAT)){
                player.sendMessage("");
                player.sendMessage("");
                player.spigot().sendMessage(message.getType(), new TextComponent("        " + BroadcastSettings.settings.getPrefix()));
                player.sendMessage("");
                player.spigot().sendMessage(message.getType(), new TextComponent(message.getColor() + message.getMessage()));
                player.sendMessage("");
                player.sendMessage("");
            }else if(message.getType().equals(ChatMessageType.SYSTEM)){
                player.sendTitle(BroadcastSettings.settings.getPrefix(), message.getMessage(), 10, 100, 10);
            }
        }
    }

    public static void sendPlayer(Player player, Message message){
        if(message.getType().equals(ChatMessageType.ACTION_BAR)){
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message.getMessage()));
        }else if(message.getType().equals(ChatMessageType.CHAT)){
            player.sendMessage("");
            player.sendMessage("");
            player.spigot().sendMessage(message.getType(), new TextComponent("        " + BroadcastSettings.settings.getPrefix()));
            player.sendMessage("");
            player.spigot().sendMessage(message.getType(), new TextComponent(message.getColor() + message.getMessage()));
            player.sendMessage("");
            player.sendMessage("");
        }else if(message.getType().equals(ChatMessageType.SYSTEM)){
            player.sendTitle(BroadcastSettings.settings.getPrefix(), message.getMessage(), 10, 100, 10);
        }
    }

}

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

import net.md_5.bungee.api.chat.TextComponent;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import net.stroyer.autobroadcast.Objects.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class Broadcast {

    private static int currentId = 0;

    public static void broadcastMessage(){
        Bukkit.getLogger().info("gotbroadcast");
        if(Message.messages.size() == 0){
            return;
        }
        if(BroadcastSettings.settings.isRandomised()){
            Random random = new Random();
            int i = random.nextInt(Message.messages.size()-1);
            send(Message.messages.get(i));
        }else{
            if(Message.messages.size() == currentId - 1){
            currentId = 0;
        }else{
            currentId ++;
        }
            send(Message.messages.get(currentId));

        }
    }

    public static void send(Message message){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.spigot().sendMessage(message.getType(), new TextComponent(BroadcastSettings.settings.getPrefix() + message.getColor() + message.getMessage()));

        }
    }

    public static void sendPlayer(Player player, Message message){
        player.spigot().sendMessage(message.getType(), new TextComponent(BroadcastSettings.settings.getPrefix() + message.getColor() + message.getMessage()));
    }

}

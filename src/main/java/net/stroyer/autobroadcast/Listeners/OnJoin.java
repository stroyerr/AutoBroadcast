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

import net.stroyer.autobroadcast.Main;
import net.stroyer.autobroadcast.Methods.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnJoin implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e){

        BukkitRunnable br = new BukkitRunnable() {

            @Override
            public void run() {
                if(e.getPlayer().hasPermission("autobroadcast.menu")){
                    if(Main.versionInt == 0){
                        Send.message(e.getPlayer(), ChatColor.GREEN  + "Stroyer_'s AutoBroadcast is up to date. Find dev versions at " + ChatColor.YELLOW + "https://github.com/stroyerr/AutoBroadcast/releases");
                    }else if(Main.versionInt == -1){
                        Send.message(e.getPlayer(), ChatColor.RED  + "Stroyer_'s AutoBroadcast outdated. Please update as this version may contain bugs and glitches! " + ChatColor.YELLOW + "https://www.spigotmc.org/resources/autobroadcast.96264/");
                    }else if(Main.versionInt == 1){
                        Send.message(e.getPlayer(),ChatColor.LIGHT_PURPLE + "You are running a dev build of Stroyer_'s AutoBroadcast. This build contains new unreleased features.");
                    }
                }
            }
        };

        if(e.getPlayer().hasPermission("autobroadcast.menu")){
            br.runTaskLater(Bukkit.getPluginManager().getPlugin("AutoBroadcast"), 11L);
        }
    }
}

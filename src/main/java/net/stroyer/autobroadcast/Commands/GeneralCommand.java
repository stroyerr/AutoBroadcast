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

package net.stroyer.autobroadcast.Commands;

import net.stroyer.autobroadcast.GUIs.BroadcastSettingsGUI;
import net.stroyer.autobroadcast.GUIs.NewMessage;
import net.stroyer.autobroadcast.Main;
import net.stroyer.autobroadcast.Methods.GetColoredString;
import net.stroyer.autobroadcast.Methods.OpenGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GeneralCommand implements CommandExecutor {

    private Main main;
    public GeneralCommand(Main main){this.main = main;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            OpenGUI.open(player);
        }

        if(args.length == 1) {
            if (args[0].equalsIgnoreCase("cancel")) {
                NewMessage.canceledInput(player);
                BroadcastSettingsGUI.canceledInput(player);
            }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("debug")){
                Bukkit.broadcastMessage(GetColoredString.getRawWithColors(args[1]));
            }
        }

        return true;
    }
}

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

import net.stroyer.autobroadcast.Methods.FillBlank;
import net.stroyer.autobroadcast.Methods.NewItem;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import net.stroyer.autobroadcast.Objects.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MainGUI {

    public static Inventory mainInventory;
    public static ItemStack broadcastSettings;
    public static ItemStack messages;
    public static ItemStack toggleEnable;
    public static ItemStack broadcastNow;

//Above this I listed a number of variables which the GUI will use. The reason I define these outside of the
//method and not inside it, is because if I define the variables inside of the method, I cannot use them outside
//of the method. Since they are defined here, I can use them anywhere.

/*
They have the public modifier, which means any class I make can access these variables. For example, any class
could access the mainInventory variable by doing MainGUI.mainInventory whereas if I defined these inside of my
method, that couldn't happen.

They are static which means that every time they are used, it is the same object just being modified. If they
weren't static, I couldn't access them from the open method, because it is static.

A static method must use static variables (for the most part).
*/

    public static void open(Player player){
        mainInventory = Bukkit.createInventory(null, 45, ChatColor.DARK_AQUA + "AutoBroadcast Menu");
// Creates an inventory. null means that the inventory has no owner, 45 is the size (it must be a multiple of 9 between)
// 0 and 54) and the third parameter (Dark_AQUA + "AutoBroadcast Menu") is the name that appears at the top of the inv.
        broadcastSettings = NewItem.createGuiItem(Material.REDSTONE, ChatColor.RED + "Broadcast Settings");
//This is a reference to the ItemStack broadcastSettings which I defined outside the class. NewItem.createGuiItem is a method
//I created on a different class. You can also just use a new ItemStack(Material.[MATERIAL]) instead.
        messages = NewItem.createGuiItem(Material.PAPER, ChatColor.WHITE + "Messages");
//This is a reference to the messages itemstack defined earlier.
        broadcastNow = NewItem.createGuiItem(Material.ENDER_PEARL, ChatColor.LIGHT_PURPLE + "Broadcast Now", "Send a message now");
        mainInventory.setItem(19, broadcastNow);
//Every inventory has a number of methods (these methods are defined by Bukkit)
//Here I am setting the item at index (19) to the ItemStack (broadcastNow) in the inventory (mainInventory).
//NOTE: The first spot is 0, hence the last spot of this one will be 44, not 45. It is always starting at 0 not 1
//for indexes.
        mainInventory.setItem(21, messages);
        mainInventory.setItem(23, broadcastSettings);
        toggleEnable = getEnabled();
//Here, I set the toggleEnable ItemStack, defined earlier to a method, getEnabled(), which is defined after this.
/*
    Since getEnabled() has a return type of ItemStack and is not void, it returns an ItemStack when called.
    I am setting the toggleEnable ItemStack to the ItemStack which I get from the getEnabled() mehtod.
*/
        mainInventory.setItem(25, toggleEnable);

        mainInventory = FillBlank.updateInventory(mainInventory);

//  This is a class I made (FillBlank) which takes an inventory you send it (hence why mainInventory is in brackets of the function)
//  And fills each empty slot with a BLACK_GLASS_PANE. This is me setting the mainInventory equal to a modified
//  version of it which comes from the FillBlank.updateInventory method.

        GUI main = new GUI(mainInventory, null);
//  This is a non-static GUI tracker but it is not necessary and was only used to make things smoother, don't worry
//  about this at this time. 
        main.open(player);
//  For you, you would want to make this line:
//  player.openInventory(mainInventory);
//  as main.open is a non-static GUI method I created.
    }


//This is the method that returned the ItemStack to the "Enabled itemstack earlier.

    public static ItemStack getEnabled(){
//Here, it checks if the Broadcast Setting is on or off (this is saved in a class called BroadcastSettings)
        if(BroadcastSettings.settings.isEnabled()){
            return NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Disable Broadcast", ChatColor.GREEN + "Currently enabled.");
    //If it is enabled, it creates the above ItemStack, if it's disabled it creates the blow ItemStack.
}else{
            return NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "Enable Broadcast", ChatColor.RED + "Currently disabled.");
        }
//  When it returns this item (the return keyword above) it means that it is sending the ItemStack to whatever function called it.
    }

//  This is a series of if statements which handle the action of the click. This starts in an event handler called
//  on click (this is in a  different class) and then calls this function, after cancelling the click event.
//  This method below simply checks what item was clicked, and makes an action accordingly. 
    public static void clickEvent(InventoryClickEvent e) {
        if(e.getCurrentItem().equals(messages)){
            MessagesGUI.open((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(broadcastSettings)){
            BroadcastSettingsGUI.open((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(toggleEnable)){
            BroadcastSettings.settings.toggleEnabled();
            updateInventory((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(broadcastNow)){
            BroadcastNow.open((Player) e.getWhoClicked());
        }
    }

    public static void updateInventory(@NotNull Player player){
        open(player);
    }
}

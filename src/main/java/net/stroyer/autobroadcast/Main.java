package net.stroyer.autobroadcast;

import net.stroyer.autobroadcast.Broadcast.BroadcastHandler;
import net.stroyer.autobroadcast.Broadcast.BroadcastTimer;
import net.stroyer.autobroadcast.Commands.GeneralCommand;
import net.stroyer.autobroadcast.Listeners.ChatListener;
import net.stroyer.autobroadcast.Listeners.InventoryClose;
import net.stroyer.autobroadcast.Listeners.InventoryInteract;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import net.stroyer.autobroadcast.Objects.Message;
import net.stroyer.autobroadcast.Util.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic



        getCommand("autobroadcast").setExecutor(new GeneralCommand(this));
        getServer().getPluginManager().registerEvents(new InventoryInteract(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        int pluginId = 12799;
        Metrics metrics = new Metrics(this, pluginId);

        File directory = new File("./plugins/AutoBroadcast");
        if (! directory.exists()){
            directory.mkdir();
        }

        File settingsFile = new File("./plugins/AutoBroadcast/settings.ab");
        File messagesFile = new File("./plugins.AutoBroadcast/messages.ab");
        if(!settingsFile.exists() || !messagesFile.exists()){
            try {
                settingsFile.createNewFile();
                messagesFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().info("Found IOException when searching for Autobroadcast settings... generating settings file with demo settings now.");
            }
        }

        try{
            BroadcastSettings.loadSettings();
            Message.load();
        }catch (IOException | ClassNotFoundException e){
            Bukkit.getLogger().info("No broadcast settings found... generating demo settings now.");
            BroadcastSettings settings = new BroadcastSettings();
        }

        BroadcastTimer.startTimer();

    }

    @Override
    public void onDisable() {

        try {
            BroadcastSettings.saveSettings();
            Message.save();
        } catch (IOException e) {}
        BroadcastTimer.stopTimer();
    }
}

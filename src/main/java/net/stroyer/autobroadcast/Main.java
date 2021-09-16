package net.stroyer.autobroadcast;

import net.stroyer.autobroadcast.Broadcast.BroadcastHandler;
import net.stroyer.autobroadcast.Broadcast.BroadcastTimer;
import net.stroyer.autobroadcast.Commands.GeneralCommand;
import net.stroyer.autobroadcast.Listeners.ChatListener;
import net.stroyer.autobroadcast.Listeners.InventoryClose;
import net.stroyer.autobroadcast.Listeners.InventoryInteract;
import net.stroyer.autobroadcast.Listeners.OnJoin;
import net.stroyer.autobroadcast.Objects.BroadcastSettings;
import net.stroyer.autobroadcast.Objects.Message;
import net.stroyer.autobroadcast.Util.Metrics;
import net.stroyer.autobroadcast.Util.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static int versionInt;

    @Override
    public void onEnable() {
        // Plugin startup logic



        getCommand("autobroadcast").setExecutor(new GeneralCommand(this));
        getServer().getPluginManager().registerEvents(new InventoryInteract(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
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
            Message.load();
            BroadcastSettings.loadSettings();
        }catch (IOException | ClassNotFoundException e){
            Bukkit.getLogger().info("No broadcast settings found... generating demo settings now.");
            BroadcastSettings settings = new BroadcastSettings();
        }

        BroadcastTimer.startTimer();

        Logger logger = this.getLogger();

        new UpdateChecker(this, 96264).getVersion(version -> {
            double latestOnlineVersion;
            double currentVersionRun;
            latestOnlineVersion = Double.parseDouble(version);
            currentVersionRun = Double.parseDouble(this.getDescription().getVersion());
            if (latestOnlineVersion == currentVersionRun) {
                logger.info("There is not a new update available.");
                versionInt = 0;
            } else if (latestOnlineVersion > currentVersionRun){
                logger.info("There is a new update available.");
                versionInt = -1;
            } else if (latestOnlineVersion < currentVersionRun){
                logger.info("You are running a dev version of EventsPlus");
                versionInt = 1;
            }
        });

    }

    @Override
    public void onDisable() {

        try {
            Message.save();
            BroadcastSettings.saveSettings();
        } catch (IOException e) {}
        BroadcastTimer.stopTimer();
    }
}

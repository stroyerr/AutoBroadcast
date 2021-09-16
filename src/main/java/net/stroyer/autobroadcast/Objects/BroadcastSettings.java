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

package net.stroyer.autobroadcast.Objects;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BroadcastSettings implements Serializable {

    public static BroadcastSettings settings;

    private static String demoPrefix = ChatColor.GOLD + "Stroyer_'s " + ChatColor.RED + "AutoBroadcast " + ChatColor.YELLOW + " // " + ChatColor.GRAY;
    private Boolean enabled = true;
    private List<String> messages = new ArrayList<>();
    private String prefix = "";
    private int secondsInterval;
    private Boolean randomised;

    public BroadcastSettings(@NotNull Boolean enabled, @NotNull List<String> messages, @NotNull String prefix, int secondsInterval, Boolean randomised){
        this.enabled = enabled;
        this.prefix = prefix;
        this.secondsInterval = secondsInterval;
        this.randomised = randomised;
        settings = this;
    }

    public BroadcastSettings(){
        this.enabled = true;
        this.prefix = demoPrefix;
        this.secondsInterval = 60;
        this.randomised = false;
        settings = this;
    }

    public int getSecondsInterval(){
        return secondsInterval;
    }

    public Boolean isEnabled(){
        return this.enabled;
    }

    public void toggleEnabled(){
        this.enabled = !this.enabled;
    }

    public static void loadSettings() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/AutoBroadcast/settings.ab");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        BroadcastSettings newSettings = (BroadcastSettings) oIn.readObject();
        settings = newSettings;
    }

    public static void saveSettings() throws IOException {
        FileOutputStream fOut = new FileOutputStream("./plugins/AutoBroadcast/settings.ab");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(settings);
    }

    public boolean isRandomised() {
        return this.randomised;
    }

    public String getPrefix(){
        return this.prefix;
    }

    public void setInterval(int newInterval) {
        secondsInterval = newInterval;
    }

    public void toggleRandomised() {
        settings.randomised = !settings.isRandomised();
    }

    public void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }
}

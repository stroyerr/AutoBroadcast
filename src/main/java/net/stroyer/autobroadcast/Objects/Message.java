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

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {

    public static List<Message> messages = new ArrayList<Message>();

    private ChatMessageType type;
    private String message;
    private ChatColor color;
    private int id;

    public Message(ChatMessageType type, String message, ChatColor color){
        this.type = type;
        this.message = message;
        this.color = color;
        this.id = messages.size();
        messages.add(this);
    }

    public ChatMessageType getType(){
        return this.type;
    }

    public String getMessage(){
        return message;
    }

    public ChatColor getColor(){
        return this.color;
    }

    public int getId(){
        return this.id;
    }

    public static void save() throws IOException {
        FileOutputStream fOut = new FileOutputStream("./plugins/AutoBroadcast/messages.ab");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(messages);
    }

    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/AutoBroadcast/messages.ab");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        messages = (List<Message>) oIn.readObject();
    }


}

package me.pauzen.forceopblocker;

import me.pauzen.jlib.reflection.Reflect;
import net.minecraft.server.v1_7_R3.JsonListEntry;
import net.minecraft.server.v1_7_R3.OpList;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import sun.reflect.Reflection;

public final class OpListProxy extends OpList {

    public OpListProxy(OpList opList) {
        super(opList.c());
    }

    @Override
    public void add(JsonListEntry e) {
        if (Reflection.getCallerClass() == Allow.class) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Opped player " + ((GameProfile) e.f()).getName());
            super.add(e);
        } else checkAllowed(Reflect.getCallerClasses(), ((GameProfile) e.f()).getName());
    }

    protected void checkAllowed(Class[] callerClasses, String name) {
        Allow.setPlayerToOp(name);
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Caller classes:");
        int amountToList = (callerClasses.length > 15 ? 15 : callerClasses.length - 1);
        for (int i = 0; i < amountToList; i++) System.out.println(amountToList - i + ": " + callerClasses[i].getCanonicalName());
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Blocked player opping.");
        Allow.printNumber();
    }
}

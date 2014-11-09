package me.pauzen.forceopblocker;

import me.pauzen.jlib.reflection.Reflect;
import net.minecraft.server.v1_7_R4.JsonListEntry;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import sun.reflect.Reflection;

import java.util.HashMap;

final class HashMapProxy<K, V> extends HashMap<K, V> {

    @Override
    public V put(K key, V value) {
        if (Reflection.getCallerClass(3) == Allow.class) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Opped player " + ((GameProfile) ((JsonListEntry) value).getKey()).getName());
            super.put(key, value);
            Allow.regen();
            Allow.setPlayerToOp(null);
        } else checkAllowed(Reflect.getCallerClasses(), ((GameProfile) ((JsonListEntry) value).getKey()).getName());
        return null;
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

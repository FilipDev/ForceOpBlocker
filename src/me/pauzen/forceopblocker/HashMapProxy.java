package me.pauzen.forceopblocker;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.reflection.ReflectionFactory;
import net.minecraft.server.v1_7_R4.JsonListEntry;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.defaults.OpCommand;
import sun.reflect.Reflection;

import java.util.HashMap;

public final class HashMapProxy<K, V> extends HashMap<K, V> {

    @Override
    public V put(K key, V value) {
        if (Reflection.getCallerClass(3) == Allow.class) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Opped player " + ((GameProfile) ((JsonListEntry) value).getKey()).getName());
            super.put(key, value);
            Allow.regen();
            Allow.setPlayerToOp(null);
        } else checkAllowed(ReflectionFactory.getCallerClasses(), ((GameProfile) ((JsonListEntry) value).getKey()).getName());
        return null;
    }

    protected void checkAllowed(Class[] callerClasses, String name) {
        Allow.setPlayerToOp(name);
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Classes that set player " + ChatColor.WHITE + name + ChatColor.RED + " to op:");
        int amountToList = (callerClasses.length > 15 ? 15 : callerClasses.length - 1);
        for (int i = 0; i < amountToList; i++) {
            if (!stringStartsWith(callerClasses[i].toString(), "class net.minecraft", "class org.bukkit", "class me.pauzen"))
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + callerClasses[i].toString());
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Blocked player opping.");
        Allow.printNumber();
        try {
            Objects.replaceStringValue(Classes.getString(OpCommand.class, "Opped "), ChatColor.YELLOW + "Attempted to op player: " + ChatColor.WHITE);
        } catch (IllegalArgumentException ignored) {
        }
    }

    private boolean stringStartsWith(String testString, String... strings) {
        if (testString == null) return false;
        for (String string : strings)
            if (testString.startsWith(string)) return true;
        return false;
    }
}

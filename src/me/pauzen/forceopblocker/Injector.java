package me.pauzen.forceopblocker;

import me.pauzen.jlib.objects.Objects;
import me.pauzen.jlib.reflection.Reflection;
import net.minecraft.server.v1_7_R4.OpList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;

import java.util.HashMap;

final class Injector {

    private Injector() {
    }

    protected static void inject() {
        OpList opList = ((CraftServer) Bukkit.getServer()).getHandle().getOPs();
        Reflection<OpList> opListReflection = new Reflection<>(opList);
        HashMap map = (HashMap) opListReflection.getValue("d");
        System.out.println(map.getClass());
        Objects.setClass(map, HashMapProxy.class);
        System.out.println(map.getClass());
        System.out.println(opList.getClass());
        System.out.println(opList.getClass().getSuperclass());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Injected.");
    }

}
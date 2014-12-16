package me.pauzen.forceopblocker;

import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.reflection.Reflection;
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
        Objects.setClass(map, new HashMapProxy<>());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Injected.");
    }

}
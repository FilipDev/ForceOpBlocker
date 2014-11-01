package me.pauzen.forceopblocker.injector;

import me.pauzen.forceopblocker.OpListProxy;
import me.pauzen.jlib.objects.Objects;
import net.minecraft.server.v1_7_R3.OpList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R3.CraftServer;

public final class Injector {

    private Injector() {
    }

    public static void inject() {
        OpList opList = ((CraftServer) Bukkit.getServer()).getHandle().getOPs();
        Objects.replaceObjectType(opList, new OpListProxy(opList));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Injected.");
    }

}

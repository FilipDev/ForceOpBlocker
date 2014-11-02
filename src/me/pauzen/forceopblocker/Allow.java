package me.pauzen.forceopblocker;

import me.pauzen.jlib.random.RandomProvider;
import net.minecraft.server.v1_7_R4.JsonListEntry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import sun.reflect.Reflection;

final class Allow {

    private Allow() {
    }

    final static class Holder {
        private static int checkForInt = RandomProvider.getRandom().nextInt(1000000);
        private static String playerToOp;
    }

    protected static void regen() {
        Holder.checkForInt = RandomProvider.getRandom().nextInt(1000000);
    }

    protected static Integer getCheckForInt() {
        if (Reflection.getCallerClass() == Main.class || Reflection.getCallerClass() == Allow.class) return Holder.checkForInt;
        else {
            regen();
            return null;
        }
    }

    protected static void opPlayer(Player player) {
        if (Reflection.getCallerClass() == Main.class) {
            if (player == null) Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Player is offline. Cannot op.");
            else {
                player.sendMessage(ChatColor.YELLOW + "You are now op!");
                ((CraftServer) Bukkit.getServer()).getHandle().getOPs().add(new JsonListEntry(((CraftPlayer) player).getProfile()));
            }
        }
    }

    protected static String getPlayerToOp() {
        return Holder.playerToOp;
    }

    protected static void setPlayerToOp(String name) {
        if (Reflection.getCallerClass() == HashMapProxy.class) Holder.playerToOp = name;
    }

    protected static void printNumber() {
        Allow.regen();
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "Type \"" + ChatColor.GREEN + "allowop " + Allow.getCheckForInt() + ChatColor.WHITE + "\" to allow this action.");
    }
}
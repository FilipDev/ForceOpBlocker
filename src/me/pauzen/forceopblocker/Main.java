package me.pauzen.forceopblocker;

import me.pauzen.jlib.reflection.Reflection;
import net.minecraft.server.v1_7_R4.OpList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        //Objects.setClass(MinecraftServer.getServer().getPlayerList(), PlayerListV2.class);
        /*if (Bukkit.getPluginManager().getPlugin("JLib Plugin") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Cannot find JLib plugin. Disabling.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }*/
        Injector.inject();
        OpList opList = ((CraftServer) Bukkit.getServer()).getHandle().getOPs();
        Reflection<OpList> opListReflection = new Reflection<>(opList);
        System.out.println(opListReflection.callMethod("d", new Object()));
        System.out.println(opList.getClass());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            if (args.length == 1) {
                Integer integer = Integer.parseInt(args[0]);
                if (integer.equals(Allow.getCheckForInt())) Allow.opPlayer(Bukkit.getPlayerExact(Allow.getPlayerToOp()));
                else Allow.printNumber();
            }
        return true;
    }

    /*public class PlayerListV2 extends PlayerList {

        public PlayerListV2(MinecraftServer minecraftserver) {
            super(minecraftserver);
        }

        @Override
        public void changeDimension(EntityPlayer entityplayer, int i, org.bukkit.event.player.PlayerTeleportEvent.TeleportCause cause) {
        }
    }*/
}

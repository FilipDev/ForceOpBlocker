package me.pauzen.forceopblocker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        /*if (Bukkit.getPluginManager().getPlugin("JLib Plugin") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Cannot find JLib plugin. Disabling.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }*/
        Injector.inject();
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
}

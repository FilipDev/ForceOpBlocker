package me.pauzen.forceopblocker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Injector.inject();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            if (args.length == 1) {
                try {
                    Integer integer = Integer.parseInt(args[0]);
                    if (integer.equals(Allow.getCheckForInt())) {
                        Allow.opPlayer(Bukkit.getPlayerExact(Allow.getPlayerToOp()));
                        return true;
                    }
                } catch (NumberFormatException ignored) {
                }
                Allow.printNumber();
            }
        return true;
    }
}

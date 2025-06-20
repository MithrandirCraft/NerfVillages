package es.mithrandircraft.nerfvillages.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandNerfVillages implements CommandExecutor {
    private final es.mithrandircraft.nerfvillages.NerfVillages mainClassAccess;

    public CommandNerfVillages(es.mithrandircraft.nerfvillages.NerfVillages main) {
        this.mainClassAccess = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) { //Check for what command it is in following list:
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r"))
            {
                CommandARGReload.R(sender, mainClassAccess);
            }
        }
        return false;
    }
}
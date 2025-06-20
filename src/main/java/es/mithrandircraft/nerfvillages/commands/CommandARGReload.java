package es.mithrandircraft.nerfvillages.commands;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class CommandARGReload {
    public static void R(CommandSender sender, NerfVillages mainClass) {
        if (sender instanceof Player) //Is player
        {
            Player player = (Player) sender;
            if (player.hasPermission("NerfVillages.Commands.Reload")) {
                //Do reload
                mainClass.reloadConfig(); //Reload main config
                player.sendMessage("NerfVillages reloaded.");
            } else player.sendMessage("No permission for command.");
        } else { //Is console
            //Do reload
            mainClass.reloadConfig();
            System.out.println("NerfVillages reloaded.");
        }
    }
}
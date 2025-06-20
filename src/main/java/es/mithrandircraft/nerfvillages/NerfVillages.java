package es.mithrandircraft.nerfvillages;

import es.mithrandircraft.nerfvillages.commands.CommandNerfVillages;
import es.mithrandircraft.nerfvillages.events.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class NerfVillages extends JavaPlugin {
    public boolean allowAReplenish = true;

    public void onEnable() {
        //Config load:
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Event registering:
        getServer().getPluginManager().registerEvents(new EntityBreedEv(this), this);
        getServer().getPluginManager().registerEvents(new EntityDeathEv(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntityEv(this), this);
        getServer().getPluginManager().registerEvents(new VillagerCareerChangeEv(this), this);

        //Commands:
        getCommand("nerfvillages").setExecutor(new CommandNerfVillages(this));
    }

    public void onDisable() {}

    private void MainRunnable() {
        (new BukkitRunnable() {
            public void run() {
                NerfVillages.this.allowAReplenish = true;
            }
        }).runTaskTimer(this, getConfig().getInt("GlobalRestockTime"), getConfig().getInt("GlobalRestockTime"));
    }
}
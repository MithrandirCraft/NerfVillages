package es.mithrandircraft.nerfvillages;

import es.mithrandircraft.nerfvillages.events.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class NerfVillages extends JavaPlugin {

    public boolean allowAReplenish = true;

    @Override
    public void onEnable() {
        //Config load:
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Event registring
        //getServer().getPluginManager().registerEvents(new VillagerReplenishTradeEv(this), this);
        getServer().getPluginManager().registerEvents(new EntityTransformEv(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntityEv(this), this);
        getServer().getPluginManager().registerEvents(new VillagerCareerChangeEv(this), this);
        getServer().getPluginManager().registerEvents(new VillagerAcquireTradeEv(this), this);
        //getServer().getPluginManager().registerEvents(new EntityBreedEv(this), this);
    }

    @Override
    public void onDisable() {
    }

    private void MainRunnable() //Performs plugin updates at scheduled time
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                allowAReplenish = true;
            }
        }.runTaskTimer(this, getConfig().getInt("GlobalRestockTime"), getConfig().getInt("GlobalRestockTime"));
    }
}

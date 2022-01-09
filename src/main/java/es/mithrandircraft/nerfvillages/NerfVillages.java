package es.mithrandircraft.nerfvillages;

import es.mithrandircraft.nerfvillages.events.EntityTransformEv;
import es.mithrandircraft.nerfvillages.events.PlayerInteractEntityEv;
import es.mithrandircraft.nerfvillages.events.VillagerAcquireTradeEv;
import es.mithrandircraft.nerfvillages.events.VillagerCareerChangeEv;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class NerfVillages extends JavaPlugin {
    public boolean allowAReplenish = true;

    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EntityTransformEv(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntityEv(this), this);
        getServer().getPluginManager().registerEvents(new VillagerCareerChangeEv(this), this);
        getServer().getPluginManager().registerEvents(new VillagerAcquireTradeEv(this), this);
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
package es.mithrandircraft.nerfvillages;

import es.mithrandircraft.nerfvillages.events.VillagerReplenishTradeEv;
import org.bukkit.plugin.java.JavaPlugin;

public final class NerfVillages extends JavaPlugin {

    @Override
    public void onEnable() {
        //Config load:
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Event registring
        getServer().getPluginManager().registerEvents(new VillagerReplenishTradeEv(this), this);
    }

    @Override
    public void onDisable() {

    }
}

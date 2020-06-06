package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;

public class VillagerReplenishTradeEv implements Listener {
    private NerfVillages mainClassAccess;

    public VillagerReplenishTradeEv(NerfVillages mca)
    {
        mainClassAccess = mca;
    }

    @EventHandler
    public void replenishEv(VillagerReplenishTradeEvent e)
    {
        //System.out.println("replenish event triggered.");
        if(mainClassAccess.getConfig().getBoolean("NerfVillagerRestock")){
            //System.out.println("cancelling event.");
            e.setCancelled(true);
        }
    }
}
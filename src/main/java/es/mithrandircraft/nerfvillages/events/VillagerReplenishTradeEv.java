/*
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
        if(!mainClassAccess.allowAReplenish && mainClassAccess.getConfig().getBoolean("NerfVillagerReplenish"))
        {
            //System.out.println(e.getRecipe());
            System.out.println(e.getRecipe().getMaxUses());

            //e.getRecipe().setMaxUses(0);

            //e.setCancelled(true); //Cancel replenish if main class allowAReplenish isn't true && config also marks nerfing
        }
        else mainClassAccess.allowAReplenish = false; //Next time this event is called it may be cancelled
    }
}
*/
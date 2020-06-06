package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class EntityBreedEv implements Listener {
    private NerfVillages mainClassAccess;

    public EntityBreedEv(NerfVillages mca) { mainClassAccess = mca; }

    @EventHandler
    public void breedEv(EntityBreedEvent e)
    {
        if(e.getEntity().getType() == EntityType.VILLAGER)
        {
            System.out.println("villager breed event triggered.");
            if(mainClassAccess.getConfig().getBoolean("DisableVillagerBreeding")){
                System.out.println("cancelling event.");
                e.setCancelled(true);
            }
        }
    }
}

package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import java.util.concurrent.ThreadLocalRandom;

public class EntityBreedEv implements Listener {
    private final NerfVillages mainClassAccess;

    public EntityBreedEv(NerfVillages mca) { mainClassAccess = mca; }

    @EventHandler
    public void breedEv(EntityBreedEvent e)
    {
        if(e.getEntity().getType() == EntityType.VILLAGER) {
            if(mainClassAccess.getConfig().getBoolean("ReduceVillagerBreeding")) {
                int randomInRange = ThreadLocalRandom.current().nextInt(1, mainClassAccess.getConfig().getInt("InterruptVillagerReproductionChance") + 1);
                if(randomInRange != 1) {
                    e.setCancelled(true); //Cancel breed event
                    e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 1);
                }
            }
        }
    }
}
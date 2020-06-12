package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Effect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;

import java.util.concurrent.ThreadLocalRandom;

public class EntityTransformEv implements Listener {
    private NerfVillages mainClassAccess;

    public EntityTransformEv(NerfVillages mca)
    {
        mainClassAccess = mca;
    }

    @EventHandler
    public void transformEv(EntityTransformEvent e)
    {
        if(e.getTransformReason() == EntityTransformEvent.TransformReason.CURED && mainClassAccess.getConfig().getBoolean("NerfVillagerCuration"))
        {
            int randomInRange = ThreadLocalRandom.current().nextInt(1, mainClassAccess.getConfig().getInt("VillagerCurationNerfRate") + 1);
            System.out.println("Result of villager revival chance: " + randomInRange);
            if(randomInRange != 1){
                e.setCancelled(true);
                e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 1);
                e.getEntity().remove(); //Kill zombie villager 1 out of "VillagerCurationNerfRate" times
            }
        }
        /*if(e.getTransformReason() == EntityTransformEvent.TransformReason.INFECTION && mainClassAccess.getConfig().getBoolean("CancelVillagerInfection"))
        {
            e.setCancelled(true); //Cancel villager undead conversion
        }*/
    }
}

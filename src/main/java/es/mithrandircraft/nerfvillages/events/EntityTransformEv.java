package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;

public class EntityTransformEv implements Listener {
    private NerfVillages mainClassAccess;

    public EntityTransformEv(NerfVillages mca)
    {
        mainClassAccess = mca;
    }

    @EventHandler
    public void transformEv(EntityTransformEvent e)
    {
        if(e.getTransformReason() == EntityTransformEvent.TransformReason.CURED && mainClassAccess.getConfig().getBoolean("CancelVillagerCuration"))
        {
            e.setCancelled(true); //Cancel zombie villager reviving
        }
        if(e.getTransformReason() == EntityTransformEvent.TransformReason.INFECTION && mainClassAccess.getConfig().getBoolean("CancelVillagerInfection"))
        {
            e.setCancelled(true); //Cancel villager undead conversion
        }
    }
}

package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Effect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;

public class EntityTransformEv implements Listener {
    private NerfVillages mainClassAccess;

    public EntityTransformEv(NerfVillages mca) {
        this.mainClassAccess = mca;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void transformEv(EntityTransformEvent e) {
        if (e.getTransformReason() == EntityTransformEvent.TransformReason.CURED && this.mainClassAccess.getConfig().getBoolean("NerfVillagerCuration")) {
            int randomInRange = ThreadLocalRandom.current().nextInt(1, this.mainClassAccess.getConfig().getInt("VillagerCurationNerfRate") + 1);
            if (randomInRange != 1) {
                e.setCancelled(true);
                e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 1);
                e.getEntity().remove();
            }
        }
    }
}
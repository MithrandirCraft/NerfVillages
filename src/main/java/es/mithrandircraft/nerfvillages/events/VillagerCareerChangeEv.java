package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;

public class VillagerCareerChangeEv implements Listener {
    private final NerfVillages mainClassAccess;

    public VillagerCareerChangeEv(NerfVillages mca) { this.mainClassAccess = mca; }

    @EventHandler(priority = EventPriority.LOW)
    public void careerChangeEv(VillagerCareerChangeEvent e) {
        if (e.getProfession() == Villager.Profession.NONE)
            if (this.mainClassAccess.getConfig().getBoolean("ActivateKillVillagerProfessionSetNone")) {
                int randomInRange = ThreadLocalRandom.current().nextInt(1, this.mainClassAccess.getConfig().getInt("KillVillagerProfessionSetNoneChance") + 1);
                if (randomInRange != 1) {
                    e.getEntity().remove();
                    e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 1);
                    e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.VINDICATOR);
                }
            }
    }
}
package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class PlayerInteractEntityEv implements Listener {
    private NerfVillages mainClassAccess;

    public PlayerInteractEntityEv(NerfVillages mca)
    {
        mainClassAccess = mca;
    }
    @EventHandler
    public void interactEv(PlayerInteractEntityEvent e) {
        if(e.getRightClicked().getType() == EntityType.VILLAGER && mainClassAccess.getConfig().getBoolean("ActivateChanceMasterVillagerDisappear"))
        {
            if(((Villager) e.getRightClicked()).getVillagerLevel() == 5){ //Villager is master level
                int randomInRange = ThreadLocalRandom.current().nextInt(1, mainClassAccess.getConfig().getInt("ChanceMasterVillagerDisappear") + 1);
                //System.out.println("Result of master villager disappear chance: " + randomInRange);
                if(randomInRange == 1)
                {
                    e.getRightClicked().remove();
                    e.getRightClicked().getWorld().playEffect(e.getRightClicked().getLocation(), Effect.SMOKE, 1);
                }
            }
        }
    }
}

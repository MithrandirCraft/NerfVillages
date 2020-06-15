package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class VillagerCareerChangeEv implements Listener {
    private NerfVillages mainClassAccess;

    public VillagerCareerChangeEv(NerfVillages mca) { mainClassAccess = mca; }

    @EventHandler (priority = EventPriority.LOW)
    public void careerChangeEv(VillagerCareerChangeEvent e)
    {
        //System.out.println("Profession change detected: " + e.getProfession());

        if(e.getProfession() == Villager.Profession.NONE) //Make sure it's not employment, since it would stop villagers from being cured (they automatically get employed to NONE)
        {
            if(mainClassAccess.getConfig().getBoolean("ActivateKillVillagerProfessionSetNone"))
            {
                int randomInRange = ThreadLocalRandom.current().nextInt(1, mainClassAccess.getConfig().getInt("KillVillagerProfessionSetNoneChance") + 1);
                //System.out.println("Result of villager revival chance: " + randomInRange);
                if(randomInRange != 1) {
                    e.getEntity().remove(); //Remove unemployed entity
                    e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 1);
                    e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.COD); //lol
                    //System.out.println("Unemployed entity removed");
                }
            }
        }
    }
}

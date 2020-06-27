package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerInteractEntityEv implements Listener {
    private NerfVillages mainClassAccess;

    public PlayerInteractEntityEv(NerfVillages mca)
    {
        mainClassAccess = mca;
    }
    @EventHandler (priority = EventPriority.LOW)
    public void interactEv(PlayerInteractEntityEvent e) {
        if(e.getRightClicked().getType() == EntityType.VILLAGER/* && mainClassAccess.getConfig().getBoolean("ActivateChanceMasterVillagerDisappear")*/)
        {
            if(((Villager) e.getRightClicked()).getVillagerLevel() == 5){ //Villager is master level
                //Check if the metadata exists in case there's any issues for getting unexisting
                if(e.getRightClicked().hasMetadata("interactions")) {
                    List<MetadataValue> v = e.getRightClicked().getMetadata("interactions");
                    //Check if interactions surpassed threshold
                    if (v.get(0).asInt() > mainClassAccess.getConfig().getInt("EnhancedTradeLockingAfterThresholdInteractions")) {
                        //Random chance of trades not being locked
                        int randomInRange = ThreadLocalRandom.current().nextInt(1, mainClassAccess.getConfig().getInt("TimesTradesLocked") + 1);
                        //System.out.println("Result of master villager trade unlockable state chance: " + randomInRange);
                        if (randomInRange == 1) {
                            //Trades unlocked (don't touch usages)
                        } else {
                            //Lock trades (set all usages to 0)
                            List<MerchantRecipe> rps = ((Villager) e.getRightClicked()).getRecipes();
                            for (MerchantRecipe rp : rps) {
                                rp.setMaxUses(0);
                            }
                        }
                    }
                    //Increase interactions by 1
                    else
                        e.getRightClicked().setMetadata("interactions", new FixedMetadataValue(mainClassAccess, v.get(0).asInt() + 1));
                }
                else e.getRightClicked().setMetadata("interactions", new FixedMetadataValue(mainClassAccess, 1));

                //Alternative, removal:
                /*int randomInRange = ThreadLocalRandom.current().nextInt(1, mainClassAccess.getConfig().getInt("ChanceMasterVillagerDisappear") + 1);
                //System.out.println("Result of master villager disappear chance: " + randomInRange);
                if(randomInRange == 1)
                {
                    e.getRightClicked().remove();
                    e.getRightClicked().getWorld().playEffect(e.getRightClicked().getLocation(), Effect.SMOKE, 1);
                }*/
            }
        }
    }
}

package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class PlayerInteractEntityEv implements Listener {
    private final NerfVillages mainClassAccess;

    public PlayerInteractEntityEv(NerfVillages mca) {
        this.mainClassAccess = mca;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void interactEv(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER)
            if (((Villager)e.getRightClicked()).getVillagerLevel() == 5)
                if (e.getRightClicked().hasMetadata("interactions")) {
                    List<MetadataValue> v = e.getRightClicked().getMetadata("interactions");
                    if ((v.get(0)).asInt() > this.mainClassAccess.getConfig().getInt("EnhancedTradeLockingAfterThresholdInteractions")) {
                        int randomInRange = ThreadLocalRandom.current().nextInt(1, this.mainClassAccess.getConfig().getInt("TimesTradesLocked") + 1);
                        if (randomInRange != 1) {
                            List<MerchantRecipe> rps = ((Villager)e.getRightClicked()).getRecipes();
                            for (MerchantRecipe rp : rps)
                                rp.setMaxUses(0);
                        }
                    } else {
                        e.getRightClicked().setMetadata("interactions", new FixedMetadataValue(this.mainClassAccess, (v.get(0)).asInt() + 1));
                    }
                } else {
                    e.getRightClicked().setMetadata("interactions", new FixedMetadataValue(this.mainClassAccess, 1));
                }
    }
}
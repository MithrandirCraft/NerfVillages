package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class VillagerAcquireTradeEv implements Listener {
    private NerfVillages mainClassAccess;

    public VillagerAcquireTradeEv(NerfVillages mca)
    {
        mainClassAccess = mca;
    }
    @EventHandler
    public void acquireEv(VillagerAcquireTradeEvent e) {
        //System.out.println("Villager acquired trade. Initial uses: " + e.getRecipe().getMaxUses());
        //Items required to be nerfed with extra severity, using specific nerfs
        if(e.getRecipe().getResult().getType() == Material.ENCHANTED_BOOK && mainClassAccess.getConfig().getBoolean("ActivateEnchantedBookChance"))
        {
            int randomInRange = ThreadLocalRandom.current().nextInt(1, mainClassAccess.getConfig().getInt("EnchantedBookChance"));
            //System.out.println("Result of enchanted book unlocked chance: " + randomInRange);
            if (randomInRange == 1) e.getRecipe().setMaxUses(1);
            else e.getRecipe().setMaxUses(0);
            //System.out.println("Villager acquired trade should be rare. Readjusted uses: " + e.getRecipe().getMaxUses());
        }
        else { //Item is not required to be rare or custom nerfed, apply regular nerf
            e.getRecipe().setMaxUses((int)Math.ceil(e.getRecipe().getMaxUses() / mainClassAccess.getConfig().getInt("MaxTradesNerfDivider")));
            //System.out.println("Villager acquired trade. Readjusted uses: " + e.getRecipe().getMaxUses());
        }
    }
}

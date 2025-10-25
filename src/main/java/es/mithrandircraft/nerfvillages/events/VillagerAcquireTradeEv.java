package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class VillagerAcquireTradeEv implements Listener {
    private final NerfVillages mainClassAccess;

    public VillagerAcquireTradeEv(NerfVillages mca) {
        this.mainClassAccess = mca;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void acquireEv(VillagerAcquireTradeEvent e) {
        if (e.getRecipe().getResult().getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) e.getRecipe().getResult().getItemMeta();
            boolean hasMendingOrThorns = bookMeta.hasStoredEnchant(Enchantment.MENDING) ||
                                         bookMeta.hasStoredEnchant(Enchantment.THORNS);

            if (hasMendingOrThorns && this.mainClassAccess.getConfig().getBoolean("ActivateThornsAndMendingBookChance")) {
                int randomInRange = ThreadLocalRandom.current().nextInt(1, this.mainClassAccess.getConfig().getInt("ThornsAndMendingBookChance"));
                if (randomInRange == 1) {
                    e.getRecipe().setMaxUses(1);
                } else {
                    e.getRecipe().setMaxUses(0);
                }
            }
            // Other enchanted books remain unnerfed - do nothing
        } else {
            e.getRecipe().setMaxUses((int)Math.ceil((e.getRecipe().getMaxUses() / this.mainClassAccess.getConfig().getInt("MaxTradesNerfDivider"))));
        }
    }
}

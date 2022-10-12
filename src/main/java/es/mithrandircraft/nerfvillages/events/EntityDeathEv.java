package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.ListIterator;

public class EntityDeathEv implements Listener {
    private final NerfVillages mainClassAccess;

    public EntityDeathEv(NerfVillages mca) { mainClassAccess = mca; }

    @EventHandler
    public void deathEv(EntityDeathEvent e)
    {
        if(e.getEntity().getType() == EntityType.PILLAGER)
        {
            List<ItemStack> drops = e.getDrops();
            ListIterator<ItemStack> iter = drops.listIterator();
            while(iter.hasNext()){
                if (iter.next().getType() == Material.EMERALD) {
                    if (Math.random() > mainClassAccess.getConfig().getDouble("PillagerEmeraldDropRate"))
                    {
                        iter.remove();
                    }
                }
            }
        }
        else if (e.getEntity().getType() == EntityType.EVOKER)
        {
            List<ItemStack> drops = e.getDrops();
            ListIterator<ItemStack> iter = drops.listIterator();
            while(iter.hasNext()){
                if (iter.next().getType() == Material.EMERALD) {
                    if (Math.random() > mainClassAccess.getConfig().getDouble("EvokerEmeraldDropRate"))
                    {
                        iter.remove();
                    }
                }
            }
        }
    }
}

package es.mithrandircraft.nerfvillages.events;

import es.mithrandircraft.nerfvillages.NerfVillages;
import org.bukkit.Bukkit;
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
        EntityType entityType = e.getEntity().getType();

        if(entityType == EntityType.PILLAGER)
        {
            List<ItemStack> drops = e.getDrops();
            ListIterator<ItemStack> iter = drops.listIterator();
            while(iter.hasNext()){
                Material itemMaterial = iter.next().getType();
                double chance = Math.random();
                if (itemMaterial == Material.EMERALD) {
                    if (chance > mainClassAccess.getConfig().getDouble("PillagerEmeraldDropRate"))
                    {
                        iter.remove();
                    }
                }
            }
        }
        else if(entityType == EntityType.VINDICATOR)
        {
            List<ItemStack> drops = e.getDrops();
            ListIterator<ItemStack> iter = drops.listIterator();
            while(iter.hasNext()){
                Material itemMaterial = iter.next().getType();
                double chance = Math.random();
                if (itemMaterial == Material.EMERALD) {
                    if (chance > mainClassAccess.getConfig().getDouble("VindicatorEmeraldDropRate"))
                    {
                        iter.remove();
                    }
                }
            }
        }
        else if (entityType == EntityType.EVOKER)
        {
            List<ItemStack> drops = e.getDrops();
            ListIterator<ItemStack> iter = drops.listIterator();
            while(iter.hasNext())
            {
                Material itemMaterial = iter.next().getType();
                double chance = Math.random();
                if (itemMaterial == Material.EMERALD) {
                    if (chance > mainClassAccess.getConfig().getDouble("EvokerEmeraldDropRate"))
                    {
                        iter.remove();
                    }
                }
                else if (itemMaterial == Material.TOTEM_OF_UNDYING) {
                    if (chance > mainClassAccess.getConfig().getDouble("EvokerTotemDropRate"))
                    {
                        iter.remove();
                    }
                }
            }
        }
    }
}

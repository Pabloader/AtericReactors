package ru.pafnooty.atericreactors.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import ru.pafnooty.atericreactors.items.AtericItems;

/**
 *
 * @author pabloid
 */
public class HugsEventHandler {

    @SubscribeEvent
    public void onPlayerHugAnimal(EntityInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack held = player.getHeldItem();
        if (held == null || held.getItem() != AtericItems.paw) {
            return;
        }
        if (!player.worldObj.isRemote) {
            Entity entity = event.target;
            if (entity instanceof EntityOcelot || entity instanceof EntityWolf) {
                if (player.worldObj.rand.nextDouble() < 0.5) {
                    entity.dropItem(AtericItems.nyaton, 1);
                }
            } else if (entity instanceof EntityAnimal) {
                if (player.worldObj.rand.nextDouble() < 0.25) {
                    entity.dropItem(AtericItems.nyaton, 1);
                }
            }
        }
    }
}

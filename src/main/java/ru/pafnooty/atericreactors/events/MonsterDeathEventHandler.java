package ru.pafnooty.atericreactors.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.ZombieEvent;
import ru.pafnooty.atericreactors.items.AtericItems;

/**
 *
 * @author pabloid
 */
public class MonsterDeathEventHandler {

    @SubscribeEvent
    public void onMonsterDeath(LivingDeathEvent event) {
        Entity entity = event.entity;
        if (!entity.worldObj.isRemote) {
            if (!(entity instanceof EntityAnimal)) {
                if (entity.worldObj.rand.nextDouble() < 0.5) {
                    entity.dropItem(AtericItems.ateron, 1);
                }
            }
        }
    }
}

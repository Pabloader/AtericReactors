package ru.pafnooty.atericreactors.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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

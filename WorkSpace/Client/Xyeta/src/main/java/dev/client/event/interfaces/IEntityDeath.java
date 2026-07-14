package dev.client.event.interfaces;

import dev.client.event.classes.EntityDeathEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IEntityDeath {
   void onDeath(EntityDeathEvent var1);
}

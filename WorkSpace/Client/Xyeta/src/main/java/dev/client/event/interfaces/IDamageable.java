package dev.client.event.interfaces;

import dev.client.event.classes.DamageEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IDamageable {
   void onDamage(DamageEvent var1);
}

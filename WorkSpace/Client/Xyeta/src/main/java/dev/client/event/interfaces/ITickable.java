package dev.client.event.interfaces;

import dev.client.event.classes.TickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ITickable {
   void onTick(TickEvent var1);
}

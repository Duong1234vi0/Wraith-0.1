package dev.client.event.interfaces;

import dev.client.event.classes.DeathScreenEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IDeathScreen {
   void onDeathScreen(DeathScreenEvent var1);
}

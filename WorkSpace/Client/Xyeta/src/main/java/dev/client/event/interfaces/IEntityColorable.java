package dev.client.event.interfaces;

import dev.client.event.classes.EntityColorEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IEntityColorable {
   void changeColor(EntityColorEvent var1);
}

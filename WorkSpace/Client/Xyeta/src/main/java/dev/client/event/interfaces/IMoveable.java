package dev.client.event.interfaces;

import dev.client.event.classes.MoveEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IMoveable {
   void onMove(MoveEvent var1);
}

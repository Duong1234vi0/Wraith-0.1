package dev.client.event.interfaces;

import dev.client.event.classes.RotationEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IRotateable {
   void onRotate(RotationEvent var1);
}

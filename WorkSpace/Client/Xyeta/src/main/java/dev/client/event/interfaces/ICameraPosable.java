package dev.client.event.interfaces;

import dev.client.event.classes.CameraPositionEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ICameraPosable {
   void onCamera(CameraPositionEvent var1);
}

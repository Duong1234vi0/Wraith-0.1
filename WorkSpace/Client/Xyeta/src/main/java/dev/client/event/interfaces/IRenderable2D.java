package dev.client.event.interfaces;

import dev.client.event.classes.Render2DEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IRenderable2D {
   void onRender2D(Render2DEvent var1);
}

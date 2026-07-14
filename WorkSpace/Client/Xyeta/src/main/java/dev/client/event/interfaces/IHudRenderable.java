package dev.client.event.interfaces;

import dev.client.event.classes.HudRenderEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IHudRenderable {
   void onHudRender(HudRenderEvent var1);
}

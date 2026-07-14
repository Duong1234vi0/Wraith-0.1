package dev.client.modules;

import dev.client.event.CancellableEvent;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public abstract class ClassMode implements IUtil {
   public abstract void onEnable();

   public abstract void onDisable();

   public abstract void onEvent(CancellableEvent var1);
}

package dev.client.event.interfaces;

import dev.client.event.classes.DropItemEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IDropable {
   void onDrop(DropItemEvent var1);
}

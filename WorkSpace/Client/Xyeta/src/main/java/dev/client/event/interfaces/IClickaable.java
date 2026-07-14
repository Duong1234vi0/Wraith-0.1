package dev.client.event.interfaces;

import dev.client.event.classes.ClickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IClickaable {
   void onClick(ClickEvent var1);
}

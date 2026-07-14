package dev.client.event.interfaces;

import dev.client.event.classes.HandledScreenEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IHandledScreen {
   void onHandleScreen(HandledScreenEvent var1);
}

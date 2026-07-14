package dev.client.event.interfaces;

import dev.client.event.classes.TravelEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ITravelable {
   void onTravel(TravelEvent var1);
}

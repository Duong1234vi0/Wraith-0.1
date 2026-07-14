package dev.client.event.interfaces;

import dev.client.event.classes.PlaceBlockEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IPlaceBlockable {
   void onPlace(PlaceBlockEvent var1);
}

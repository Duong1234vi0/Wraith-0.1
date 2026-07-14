package dev.client.ui.draggable.impl;

import dev.client.ui.draggable.Draggable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class WatermarkDraggable extends Draggable {
   public WatermarkDraggable() {
      super("WatermarkDraggable", 10, 10, 100, 20);
   }
}

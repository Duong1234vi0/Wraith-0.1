package dev.client.ui.draggable.impl;

import dev.client.ui.draggable.Draggable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PotionsDraggable extends Draggable {
   public PotionsDraggable() {
      super("PotionsDraggable", 10, 80, 100, 20);
   }
}

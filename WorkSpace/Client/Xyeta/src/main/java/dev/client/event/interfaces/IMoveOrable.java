package dev.client.event.interfaces;

import dev.client.event.classes.MoveOrEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IMoveOrable {
   void onMoveOrable(MoveOrEvent var1);
}

package dev.client.event.interfaces;

import dev.client.event.classes.MoveCorrectionEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IMoveCorrectionable {
   void moveCorrection(MoveCorrectionEvent var1);
}

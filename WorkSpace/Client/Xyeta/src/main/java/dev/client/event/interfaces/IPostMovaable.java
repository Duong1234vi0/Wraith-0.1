package dev.client.event.interfaces;

import dev.client.event.classes.PostMoveEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IPostMovaable {
   void onPostMove(PostMoveEvent var1);
}

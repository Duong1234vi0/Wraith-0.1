package dev.client.event.classes;

import dev.client.event.CancellableEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DropItemEvent extends CancellableEvent {
}

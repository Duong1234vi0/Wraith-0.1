package dev.client.modules.settings;

import dev.client.util.animations.Animation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public record Mode(String name, Animation animation) {
}

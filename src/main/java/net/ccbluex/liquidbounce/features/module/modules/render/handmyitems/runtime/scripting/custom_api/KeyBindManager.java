/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.KeyMapping
 *  org.lwjgl.glfw.GLFW
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.custom_api;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyBindManager {
    KeyMapping key;

    @Safe
    public boolean isKeyPressed(int keyCode) {
        if (keyCode != 0) {
            long windowHandle = Minecraft.getInstance().getWindow().handle();
            return GLFW.glfwGetKey((long)windowHandle, (int)keyCode) == 1;
        }
        return false;
    }
}

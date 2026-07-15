package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime;

import net.minecraft.client.Minecraft;
import org.joml.Matrix4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Shared render-frame context retained from the original HMI initializer. */
public final class LuaTestHMI {
    public static final String MOD_ID = "liquidbounce";
    public static final Logger LOGGER = LoggerFactory.getLogger("HandMyItems");
    public static Minecraft client;
    public static Matrix4f matricesMain = new Matrix4f();
    public static Matrix4f matricesOff = new Matrix4f();
    public static float tickProgress;
    public static float prevTime;
    public static float deltaTime;

    private LuaTestHMI() {
    }

    public static void resetFrameContext() {
        matricesMain.identity();
        matricesOff.identity();
        tickProgress = 0.0f;
        prevTime = 0.0f;
        deltaTime = 0.0f;
    }
}

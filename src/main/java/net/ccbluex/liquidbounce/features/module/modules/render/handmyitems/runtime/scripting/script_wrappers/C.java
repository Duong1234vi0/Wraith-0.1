/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.Camera
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.CameraAccessor;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Camera;

public class C {
    @Safe
    public void setCamPos(double x, double y, double z) {
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        if (camera instanceof CameraAccessor) {
            CameraAccessor camera2 = (CameraAccessor)camera;
            camera2.hMI5_0$setPosValues((float)x, (float)y, (float)z);
        }
    }

    @Safe
    public void setCamRot(double x, double y, double z) {
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        if (camera instanceof CameraAccessor) {
            CameraAccessor camera2 = (CameraAccessor)camera;
            camera2.hMI5_0$setRotationValues((float)x, (float)y, (float)z);
        }
    }
}

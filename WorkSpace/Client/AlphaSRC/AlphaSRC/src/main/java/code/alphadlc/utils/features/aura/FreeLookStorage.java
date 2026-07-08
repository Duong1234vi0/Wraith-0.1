/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.features.aura;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.keyboard.MouseRotationEvent;
import code.alphadlc.events.render.CameraEvent;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.warp.Turns;
import net.minecraft.class_3532;

public class FreeLookStorage
implements QuickImports {
    private static boolean active;
    private static float freeYaw;
    private static float freePitch;

    public FreeLookStorage() {
        AlphaDLC.getInstance().getEventManager();
        EventManager.register(this);
    }

    public static boolean isActive() {
        return active;
    }

    public static void setActive(boolean value) {
        if (!value) {
            active = false;
            return;
        }
        FreeLookStorage.activateForSilentRotation();
    }

    public static void activateForSilentRotation() {
        if (mc == null || FreeLookStorage.mc.field_1724 == null) {
            return;
        }
        FreeLookStorage.captureCameraAngles();
        active = true;
    }

    private static void captureCameraAngles() {
        if (FreeLookStorage.mc.field_1773 != null) {
            freeYaw = FreeLookStorage.mc.field_1773.method_19418().method_19330();
            freePitch = FreeLookStorage.mc.field_1773.method_19418().method_19329();
        } else {
            freeYaw = FreeLookStorage.mc.field_1724.method_36454();
            freePitch = FreeLookStorage.mc.field_1724.method_36455();
        }
    }

    @EventHandler
    public void onLook(MouseRotationEvent event) {
        if (active) {
            this.rotateTowards(event.getCursorDeltaX(), event.getCursorDeltaY());
            event.cancel();
        }
    }

    @EventHandler
    public void onRotation(CameraEvent event) {
        if (active) {
            event.setAngle(new Turns(freeYaw, freePitch));
            event.cancel();
        } else if (event.getAngle() != null) {
            freeYaw = event.getAngle().getYaw();
            freePitch = event.getAngle().getPitch();
        }
    }

    private void rotateTowards(double targetYaw, double targetPitch) {
        freePitch = class_3532.method_15363((float)((float)((double)freePitch + targetPitch * 0.15)), (float)-90.0f, (float)90.0f);
        freeYaw = (float)((double)freeYaw + targetYaw * 0.15);
    }

    public static void blendToward(float targetYaw, float targetPitch, float factor) {
        if (!active) {
            return;
        }
        float blend = class_3532.method_15363((float)factor, (float)0.0f, (float)1.0f);
        float yawDelta = class_3532.method_15393((float)(targetYaw - freeYaw));
        float pitchDelta = targetPitch - freePitch;
        freeYaw = class_3532.method_15393((float)(freeYaw + yawDelta * blend));
        freePitch = class_3532.method_15363((float)(freePitch + pitchDelta * blend), (float)-90.0f, (float)90.0f);
    }

    public static float getFreeYaw() {
        return freeYaw;
    }

    public static float getFreePitch() {
        return freePitch;
    }

    public static void setFreeYaw(float freeYaw) {
        FreeLookStorage.freeYaw = freeYaw;
    }

    public static void setFreePitch(float freePitch) {
        FreeLookStorage.freePitch = freePitch;
    }
}

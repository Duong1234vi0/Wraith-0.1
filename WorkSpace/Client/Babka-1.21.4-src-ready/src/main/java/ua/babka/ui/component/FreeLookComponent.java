package ua.babka.ui.component;

import com.google.common.eventbus.Subscribe;
import net.minecraft.util.math.MathHelper;
import ua.babka.event.LookEvent;
import ua.babka.event.RotationEvent;
import ua.babka.ui.component.ComponentHelper;

public class FreeLookComponent
extends ComponentHelper {
    private static boolean active;
    private static float freeYaw;
    private static float freePitch;

    @Subscribe
    public void onEvent(LookEvent event) {
        if (active) {
            this.rotateTowards(event.getYaw(), event.getPitch());
            event.cancelEvent();
        }
    }

    @Subscribe
    public void onEvent(RotationEvent event) {
        if (active) {
            event.setYaw(freeYaw);
            event.setPitch(freePitch);
        } else {
            freeYaw = event.getYaw();
            freePitch = event.getPitch();
        }
    }

    private void rotateTowards(double targetYaw, double targetPitch) {
        freePitch = MathHelper.clamp((float)((float)((double)freePitch + targetPitch * 0.15)), (float)-90.0f, (float)90.0f);
        freeYaw = (float)((double)freeYaw + targetYaw * 0.15);
    }

    public static boolean isActive() {
        return active;
    }

    public static void setActive(boolean active) {
        FreeLookComponent.active = active;
    }

    public static float getFreeYaw() {
        return freeYaw;
    }

    public static float getFreePitch() {
        return freePitch;
    }

    public static void setFreeYaw(float freeYaw) {
        FreeLookComponent.freeYaw = freeYaw;
    }

    public static void setFreePitch(float freePitch) {
        FreeLookComponent.freePitch = freePitch;
    }
}


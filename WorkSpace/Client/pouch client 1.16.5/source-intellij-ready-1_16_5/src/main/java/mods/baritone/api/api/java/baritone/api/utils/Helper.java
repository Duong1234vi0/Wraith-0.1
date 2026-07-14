/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.v_1900_v;
import lightning.product.x_282_a;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;

public interface Helper {
    public static final Helper HELPER = new Helper(){};
    @Deprecated
    public static final MinecraftClient mc = MinecraftClient.A_4115_X();

    public static x_282_a getPrefix() {
        boolean xd;
        Calendar now = Calendar.getInstance();
        boolean bl = xd = now.get(2) == 3 && now.get(5) <= 3;
        String inner = xd ? "Baritoe" : ((Boolean)BaritoneAPI.getSettings().shortBaritonePrefix.value != false ? "B" : "Baritone");
        return v_1900_v.n_1700_B("[" + inner + "]");
    }

    default public void logToast(x_282_a title, x_282_a message) {
        MinecraftClient.A_4115_X().execute(() -> ((BiConsumer)BaritoneAPI.getSettings().toaster.value).accept(title, message));
    }

    default public void logToast(String title, String message) {
        this.logToast(new U_2871_b(title), new U_2871_b(message));
    }

    default public void logToast(String message) {
        this.logToast(Helper.getPrefix(), new U_2871_b(message));
    }

    default public void logNotification(String message) {
        this.logNotification(message, false);
    }

    default public void logNotification(String message, boolean error) {
        if (((Boolean)BaritoneAPI.getSettings().desktopNotifications.value).booleanValue()) {
            this.logNotificationDirect(message, error);
        }
    }

    default public void logNotificationDirect(String message) {
        this.logNotificationDirect(message, false);
    }

    default public void logNotificationDirect(String message, boolean error) {
        MinecraftClient.A_4115_X().execute(() -> ((BiConsumer)BaritoneAPI.getSettings().notifier.value).accept(message, error));
    }

    default public void logDebug(String message) {
        if (!((Boolean)BaritoneAPI.getSettings().chatDebug.value).booleanValue()) {
            return;
        }
        this.logDirect(message, false);
    }

    default public void logDirect(boolean logAsToast, x_282_a ... components) {
        U_2871_b component = new U_2871_b("");
        component.n_1700_B(Helper.getPrefix());
        component.n_1700_B(new U_2871_b(" "));
        Arrays.asList(components).forEach(component::n_1700_B);
        if (logAsToast) {
            this.logToast(Helper.getPrefix(), component);
        } else {
            MinecraftClient.A_4115_X().execute(() -> ((Consumer)BaritoneAPI.getSettings().logger.value).accept(component));
        }
    }

    default public void logDirect(x_282_a ... components) {
        this.logDirect((Boolean)BaritoneAPI.getSettings().logAsToast.value, components);
    }

    default public void logDirect(String message, D_4024_W color, boolean logAsToast) {
        Stream.of(message.split("\n")).forEach(line -> {
            U_2871_b component = new U_2871_b(line.replace("\t", "    "));
            component.n_1700_B(component.n_1700_B().n_1700_B(color));
            this.logDirect(logAsToast, component);
        });
    }

    default public void logDirect(String message, D_4024_W color) {
        this.logDirect(message, color, (Boolean)BaritoneAPI.getSettings().logAsToast.value);
    }

    default public void logDirect(String message, boolean logAsToast) {
        this.logDirect(message, D_4024_W.w_1484_f, logAsToast);
    }

    default public void logDirect(String message) {
        this.logDirect(message, (Boolean)BaritoneAPI.getSettings().logAsToast.value);
    }
}



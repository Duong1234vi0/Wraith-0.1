/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils.gui;

import lightning.product.D_1624_i;
import lightning.product.Toast;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;

public class BaritoneToast
implements Toast {
    private String title;
    private String subtitle;
    private long firstDrawTime;
    private boolean newDisplay;
    private long totalShowTime;

    public BaritoneToast(x_282_a titleComponent, x_282_a subtitleComponent, long totalShowTime) {
        this.title = titleComponent.getString();
        this.subtitle = subtitleComponent == null ? null : subtitleComponent.getString();
        this.totalShowTime = totalShowTime;
    }

    @Override
    public Toast.n_1700_B func_230444_a_(g_221_o matrixStack, D_1624_i toastGui, long delta) {
        if (this.newDisplay) {
            this.firstDrawTime = delta;
            this.newDisplay = false;
        }
        toastGui.J_1907_R().G_624_v().n_1700_B(new g_2336_b("textures/gui/toasts.png"));
        X_933_l.G_564_y(1.0f, 1.0f, 1.0f, 255.0f);
        toastGui.blit(matrixStack, 0, 0, 0, 32, 160, 32);
        if (this.subtitle == null) {
            toastGui.J_1907_R().t_148_a.J_1907_R(matrixStack, this.title, 18.0f, 12.0f, -11534256);
        } else {
            toastGui.J_1907_R().t_148_a.J_1907_R(matrixStack, this.title, 18.0f, 7.0f, -11534256);
            toastGui.J_1907_R().t_148_a.J_1907_R(matrixStack, this.subtitle, 18.0f, 18.0f, -16777216);
        }
        return delta - this.firstDrawTime < this.totalShowTime ? Toast.n_1700_B.n_1700_B : Toast.n_1700_B.J_1907_R;
    }

    public void setDisplayedText(x_282_a titleComponent, x_282_a subtitleComponent) {
        this.title = titleComponent.getString();
        this.subtitle = subtitleComponent == null ? null : subtitleComponent.getString();
        this.newDisplay = true;
    }

    public static void addOrUpdate(D_1624_i toast, x_282_a title, x_282_a subtitle, long totalShowTime) {
        BaritoneToast baritonetoast = toast.n_1700_B(BaritoneToast.class, new Object());
        if (baritonetoast == null) {
            toast.n_1700_B(new BaritoneToast(title, subtitle, totalShowTime));
        } else {
            baritonetoast.setDisplayedText(title, subtitle);
        }
    }

    public static void addOrUpdate(x_282_a title, x_282_a subtitle) {
        BaritoneToast.addOrUpdate(MinecraftClient.A_4115_X().e_1992_r(), title, subtitle, (Long)BaritoneAPI.getSettings().toastTimer.value);
    }
}




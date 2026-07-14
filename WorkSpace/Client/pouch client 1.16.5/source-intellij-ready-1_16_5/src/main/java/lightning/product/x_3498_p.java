/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.P_4249_L;
import lightning.product.U_2871_b;
import lightning.product.U_679_Y;
import lightning.product.V_4423_d;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_164_R;
import lightning.product.i_2518_W;
import lightning.product.i_2909_p;
import lightning.product.j_3341_s;
import lightning.product.x_282_a;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class x_3498_p {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final DateFormat J_1907_R = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

    public static void n_1700_B(File gameDirectory, int width, int height, P_4249_L buffer, Consumer<x_282_a> messageConsumer) {
        x_3498_p.n_1700_B(gameDirectory, null, width, height, buffer, messageConsumer);
    }

    public static void n_1700_B(File gameDirectory, @Nullable String screenshotName, int width, int height, P_4249_L buffer, Consumer<x_282_a> messageConsumer) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> x_3498_p.J_1907_R(gameDirectory, screenshotName, width, height, buffer, messageConsumer));
        } else {
            x_3498_p.J_1907_R(gameDirectory, screenshotName, width, height, buffer, messageConsumer);
        }
    }

    private static void J_1907_R(File gameDirectory, @Nullable String screenshotName, int width, int height, P_4249_L buffer, Consumer<x_282_a> messageConsumer) {
        boolean flag;
        MinecraftClient minecraft = Config.getMinecraft();
        U_679_Y mainwindow = minecraft.RealmsServerPing();
        V_4423_d gamesettings = Config.getGameSettings();
        int i = mainwindow.u_2550_I();
        int j = mainwindow.M_588_G();
        int k = gamesettings.g_4106_L;
        int l = mainwindow.n_1700_B(minecraft.P_4830_p.g_4106_L, minecraft.P_4830_p.g_221_o);
        int i1 = Config.getScreenshotSize();
        boolean bl = flag = g_164_R.v_4262_N() && i1 > 1;
        if (flag) {
            gamesettings.g_4106_L = l * i1;
            mainwindow.n_1700_B(i * i1, j * i1);
            X_933_l.g_221_o();
            X_933_l.Y_1740_V(16640);
            minecraft.G_564_y().J_1907_R(true);
            X_933_l.v_4276_D();
            minecraft.s_956_w.n_1700_B(minecraft.RealmsClientConfig(), System.nanoTime(), true);
        }
        i_2518_W nativeimage = x_3498_p.n_1700_B(width, height, buffer);
        if (flag) {
            minecraft.G_564_y().t_148_a();
            X_933_l.e_2887_G();
            Config.getGameSettings().g_4106_L = k;
            mainwindow.n_1700_B(i, j);
        }
        File file1 = new File(gameDirectory, "screenshots");
        file1.mkdir();
        File file2 = screenshotName == null ? x_3498_p.n_1700_B(file1) : new File(file1, screenshotName);
        Object object = null;
        if (Reflector.ForgeHooksClient_onScreenshot.exists()) {
            object = Reflector.call(Reflector.ForgeHooksClient_onScreenshot, nativeimage, file2);
            if (Reflector.callBoolean(object, Reflector.Event_isCanceled, new Object[0])) {
                x_282_a itextcomponent = (x_282_a)Reflector.call(object, Reflector.ScreenshotEvent_getCancelMessage, new Object[0]);
                messageConsumer.accept(itextcomponent);
                return;
            }
            file2 = (File)Reflector.call(object, Reflector.ScreenshotEvent_getScreenshotFile, new Object[0]);
        }
        File file3 = file2;
        Object object1 = object;
        j_3341_s.v_4262_N().execute(() -> {
            try {
                nativeimage.n_1700_B(file3);
                MutableComponent itextcomponent1 = new U_2871_b(file3.getName()).n_1700_B(D_4024_W.Y_601_j).n_1700_B(p_lambda$null$1_1_ -> p_lambda$null$1_1_.n_1700_B(new i_2909_p(i_2909_p.n_1700_B.J_1907_R, file3.getAbsolutePath())));
                if (object1 != null && Reflector.call(object1, Reflector.ScreenshotEvent_getResultMessage, new Object[0]) != null) {
                    messageConsumer.accept((x_282_a)Reflector.call(object1, Reflector.ScreenshotEvent_getResultMessage, new Object[0]));
                } else {
                    messageConsumer.accept(new F_2904_S("screenshot.success", itextcomponent1));
                }
            }
            catch (Exception exception1) {
                n_1700_B.warn("Couldn't save screenshot", (Throwable)exception1);
                messageConsumer.accept(new F_2904_S("screenshot.failure", exception1.getMessage()));
            }
            finally {
                nativeimage.close();
            }
        });
    }

    public static i_2518_W n_1700_B(int width, int height, P_4249_L framebufferIn) {
        if (!g_164_R.v_4262_N()) {
            i_2518_W nativeimage1 = new i_2518_W(width, height, false);
            nativeimage1.J_1907_R(true);
            nativeimage1.u_1723_Y();
            return nativeimage1;
        }
        width = framebufferIn.n_1700_B;
        height = framebufferIn.J_1907_R;
        i_2518_W nativeimage = new i_2518_W(width, height, false);
        c_4037_x.v_4262_N(framebufferIn.u_2550_I());
        nativeimage.n_1700_B(0, true);
        nativeimage.u_1723_Y();
        return nativeimage;
    }

    private static File n_1700_B(File gameDirectory) {
        String s = J_1907_R.format(new Date());
        int i = 1;
        File file1;
        while ((file1 = new File(gameDirectory, s + (String)(i == 1 ? "" : "_" + i) + ".png")).exists()) {
            ++i;
        }
        return file1;
    }
}




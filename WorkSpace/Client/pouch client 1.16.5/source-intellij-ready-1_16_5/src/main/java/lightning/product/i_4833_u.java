/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import lightning.product.MinecraftAccess;
import org.lwjgl.opengl.GL11;

public class i_4833_u {
    private static final List<Rectangle> n_1700_B = new ArrayList<Rectangle>();
    private static Rectangle J_1907_R = null;

    public static void n_1700_B() {
        GL11.glPopAttrib();
        if (!n_1700_B.isEmpty()) {
            n_1700_B.remove(n_1700_B.size() - 1);
            J_1907_R = n_1700_B.isEmpty() ? null : n_1700_B.get(n_1700_B.size() - 1);
        } else {
            J_1907_R = null;
        }
    }

    public static void n_1700_B(double x, double y, double width, double height) {
        GL11.glPushAttrib((int)524288);
        n_1700_B.add(J_1907_R == null ? null : new Rectangle(J_1907_R));
        double scale = MinecraftAccess.H_2857_Y.w_1457_N();
        int sx = (int)(x * scale);
        int sy = (int)(y * scale);
        int sw = (int)(width * scale);
        int sh = (int)(height * scale);
        int fbWidth = MinecraftAccess.H_2857_Y.u_2550_I();
        int fbHeight = MinecraftAccess.H_2857_Y.M_588_G();
        int scissorY = fbHeight - sy - sh;
        Rectangle requested = new Rectangle(sx, scissorY, Math.max(0, sw), Math.max(0, sh));
        Rectangle framebuffer = new Rectangle(0, 0, fbWidth, fbHeight);
        Rectangle parent = J_1907_R != null ? J_1907_R : framebuffer;
        Rectangle result = requested.intersection(parent).intersection(framebuffer);
        if (result.width < 0) {
            result.width = 0;
        }
        if (result.height < 0) {
            result.height = 0;
        }
        GL11.glEnable((int)3089);
        GL11.glScissor((int)result.x, (int)result.y, (int)result.width, (int)result.height);
        if (!n_1700_B.isEmpty()) {
            n_1700_B.set(n_1700_B.size() - 1, new Rectangle(result));
        }
        J_1907_R = new Rectangle(result);
    }
}



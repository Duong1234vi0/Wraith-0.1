/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.Module;
import lightning.product.MinecraftClient;
import lightning.product.ClientBootstrap;
import lightning.product.y_2447_C;
import lombok.Generated;

public class y_4642_Y {
    private static boolean n_1700_B = false;
    private static Map<String, List<y_2447_C.n_1700_B>> J_1907_R = new HashMap<String, List<y_2447_C.n_1700_B>>();
    private static final Path R_4764_Y = Paths.get("C:\\Pouch", new String[0]);
    private static Boolean G_564_y = null;

    public static void n_1700_B() {
        if (n_1700_B) {
            return;
        }
        n_1700_B = true;
        Module.G_564_y(true);
        for (Module module : ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y()) {
            if (!module.w_1484_f()) continue;
            module.n_1700_B(false);
        }
        Module.G_564_y(false);
        y_4642_Y.G_564_y();
        y_4642_Y.u_1723_Y();
        y_4642_Y.t_148_a();
    }

    public static void J_1907_R() {
        if (!n_1700_B) {
            return;
        }
        n_1700_B = false;
        y_4642_Y.P_1922_E();
        y_4642_Y.v_4262_N();
    }

    private static void G_564_y() {
        y_2447_C waypointManager = ClientBootstrap.Y_601_j().s_956_w();
        if (waypointManager == null || waypointManager.u_2550_I() == null) {
            return;
        }
        J_1907_R.clear();
        for (Map.Entry entry : ((Map)waypointManager.u_2550_I()).entrySet()) {
            J_1907_R.put((String)entry.getKey(), new ArrayList((Collection)entry.getValue()));
        }
        ((Map)waypointManager.u_2550_I()).clear();
    }

    private static void P_1922_E() {
        if (J_1907_R.isEmpty()) {
            return;
        }
        y_2447_C waypointManager = ClientBootstrap.Y_601_j().s_956_w();
        if (waypointManager == null) {
            return;
        }
        for (Map.Entry<String, List<y_2447_C.n_1700_B>> entry : J_1907_R.entrySet()) {
            for (y_2447_C.n_1700_B wp : entry.getValue()) {
                waypointManager.n_1700_B(entry.getKey(), wp.n_1700_B(), wp.J_1907_R(), wp.R_4764_Y(), wp.G_564_y());
            }
        }
        J_1907_R.clear();
    }

    private static void u_1723_Y() {
        if (!y_4642_Y.w_1484_f() || !Files.exists(R_4764_Y, new LinkOption[0])) {
            return;
        }
        try {
            boolean hiddenNow = Files.isHidden(R_4764_Y);
            G_564_y = hiddenNow;
            if (!hiddenNow) {
                Files.setAttribute(R_4764_Y, "dos:hidden", true, new LinkOption[0]);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void v_4262_N() {
        if (!y_4642_Y.w_1484_f() || !Files.exists(R_4764_Y, new LinkOption[0])) {
            return;
        }
        if (G_564_y == null) {
            return;
        }
        try {
            Files.setAttribute(R_4764_Y, "dos:hidden", G_564_y, new LinkOption[0]);
        }
        catch (Exception exception) {
        }
        finally {
            G_564_y = null;
        }
    }

    private static boolean w_1484_f() {
        String osName = System.getProperty("os.name");
        return osName != null && osName.toLowerCase().contains("win");
    }

    private static void t_148_a() {
        try {
            MinecraftClient mc = MinecraftClient.A_4115_X();
            if (mc != null && mc.M_588_G != null && mc.M_588_G.R_4764_Y() != null) {
                mc.M_588_G.R_4764_Y().n_1700_B(false);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Generated
    public static boolean R_4764_Y() {
        return n_1700_B;
    }

    @Generated
    public static void n_1700_B(boolean panicMode) {
        n_1700_B = panicMode;
    }
}



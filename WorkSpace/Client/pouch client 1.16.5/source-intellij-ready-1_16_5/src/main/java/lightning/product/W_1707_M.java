/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lightning.product.E_2115_e;
import lightning.product.M_4609_z;
import lightning.product.t_4864_b;

public final class W_1707_M {
    private static final Logger n_1700_B = Logger.getLogger("Pouch/AI/ModelManager");
    private static final String J_1907_R = "tf-0000";
    private static final String R_4764_Y = ".onnx";
    private static final Map<String, M_4609_z> G_564_y = new ConcurrentHashMap<String, M_4609_z>();
    private static final Set<String> P_1922_E = ConcurrentHashMap.newKeySet();

    private W_1707_M() {
    }

    public static M_4609_z n_1700_B(String profileName) {
        M_4609_z cached;
        if (profileName == null || profileName.isEmpty() || profileName.equals("\u0410\u0432\u0442\u043e")) {
            profileName = J_1907_R;
        }
        if ((cached = G_564_y.get(profileName)) != null) {
            return cached;
        }
        if (!P_1922_E.contains(profileName)) {
            W_1707_M.G_564_y(profileName);
        }
        return null;
    }

    public static boolean n_1700_B() {
        return !P_1922_E.isEmpty();
    }

    public static boolean J_1907_R(String name) {
        if (name == null || name.equals("\u0410\u0432\u0442\u043e")) {
            name = J_1907_R;
        }
        return G_564_y.containsKey(name);
    }

    private static void G_564_y(String name) {
        if (!P_1922_E.add(name)) {
            return;
        }
        Thread t = new Thread(() -> {
            try {
                if (!t_4864_b.J_1907_R()) {
                    return;
                }
                W_1707_M.P_1922_E(name);
            }
            catch (Throwable ex) {
                n_1700_B.log(Level.SEVERE, "Failed to load ONNX model '" + name + "'", ex);
            }
            finally {
                P_1922_E.remove(name);
            }
        }, "Pouch-ONNX-" + name);
        t.setDaemon(true);
        t.start();
    }

    private static void P_1922_E(String modelName) {
        if (G_564_y.containsKey(modelName)) {
            return;
        }
        File file = W_1707_M.u_1723_Y(modelName);
        if (file == null) {
            n_1700_B.warning("ONNX model '" + modelName + "' not found in models folder.");
            return;
        }
        M_4609_z model = new M_4609_z(modelName);
        try {
            model.n_1700_B(file.toPath());
            G_564_y.put(modelName, model);
            n_1700_B.info("Loaded ONNX model '" + modelName + "' from " + file.getName());
        }
        catch (IOException e) {
            n_1700_B.log(Level.SEVERE, "Failed to load ONNX model '" + modelName + "'.", e);
            try {
                model.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    private static File u_1723_Y(String modelName) {
        File folder = E_2115_e.R_4764_Y();
        File flat = new File(folder, modelName + R_4764_Y);
        if (flat.isFile()) {
            return flat;
        }
        File dir = new File(folder, modelName);
        if (dir.isDirectory()) {
            File inside = new File(dir, modelName + R_4764_Y);
            if (inside.isFile()) {
                return inside;
            }
            File[] onnx = dir.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(R_4764_Y));
            if (onnx != null && onnx.length > 0) {
                return onnx[0];
            }
        }
        return null;
    }

    public static synchronized boolean R_4764_Y(String modelName) {
        if (!t_4864_b.J_1907_R()) {
            n_1700_B.severe("ONNX Runtime is not available; cannot load model.");
            return false;
        }
        M_4609_z old = G_564_y.remove(modelName);
        if (old != null) {
            try {
                old.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        W_1707_M.P_1922_E(modelName);
        return G_564_y.containsKey(modelName);
    }

    public static synchronized void J_1907_R() {
        if (!t_4864_b.J_1907_R()) {
            return;
        }
        W_1707_M.P_1922_E(J_1907_R);
    }

    public static synchronized void R_4764_Y() {
        for (Map.Entry<String, M_4609_z> entry : G_564_y.entrySet()) {
            try {
                entry.getValue().close();
            }
            catch (IOException iOException) {}
        }
        G_564_y.clear();
        if (!t_4864_b.J_1907_R()) {
            return;
        }
        File folder = E_2115_e.R_4764_Y();
        File[] children = folder.listFiles();
        if (children == null) {
            return;
        }
        for (File child : children) {
            String name = null;
            if (child.isFile() && child.getName().toLowerCase(Locale.ROOT).endsWith(R_4764_Y)) {
                name = child.getName().substring(0, child.getName().length() - R_4764_Y.length());
            } else if (child.isDirectory() && W_1707_M.u_1723_Y(child.getName()) != null) {
                name = child.getName();
            }
            if (name == null) continue;
            W_1707_M.P_1922_E(name);
        }
    }

    public static String G_564_y() {
        return J_1907_R;
    }

    @Deprecated
    public static M_4609_z P_1922_E() {
        return W_1707_M.n_1700_B(J_1907_R);
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Arrays;
import lightning.product.E_2115_e;
import lightning.product.P_459_I;
import lightning.product.q_2475_j;

public final class b_967_P {
    private static final String[] n_1700_B = new String[]{"/neuro/train_neuro.py", "/pouch/scripts/train_neuro.py"};
    private static final String J_1907_R = "train_neuro.py";

    private b_967_P() {
    }

    public static File n_1700_B() {
        q_2475_j.R_4764_Y("Trainer script");
        byte[] bundled = b_967_P.R_4764_Y();
        if (bundled == null || bundled.length == 0) {
            q_2475_j.J_1907_R("Trainer script source is empty");
            return null;
        }
        q_2475_j.n_1700_B("Trainer script bundle size: " + q_2475_j.n_1700_B(bundled.length));
        File dir = b_967_P.J_1907_R();
        if (!dir.exists() && !dir.mkdirs()) {
            q_2475_j.J_1907_R("Could not create scripts folder: " + String.valueOf(dir));
            return null;
        }
        File target = new File(dir, J_1907_R);
        if (target.isFile() && b_967_P.n_1700_B(target.toPath(), bundled)) {
            q_2475_j.n_1700_B(target.toPath(), "hash matches bundled");
            return target;
        }
        try {
            Files.write(target.toPath(), bundled, new OpenOption[0]);
            q_2475_j.n_1700_B(target.toPath(), (long)bundled.length, "extract train_neuro.py");
            return target;
        }
        catch (IOException e) {
            q_2475_j.n_1700_B("Failed to write trainer script to " + String.valueOf(target), e);
            return target.isFile() ? target : null;
        }
    }

    private static File J_1907_R() {
        File modelsParent = E_2115_e.R_4764_Y().getParentFile();
        return new File(modelsParent, "scripts");
    }

    private static byte[] R_4764_Y() {
        byte[] fromClasspath = b_967_P.G_564_y();
        if (fromClasspath != null) {
            q_2475_j.n_1700_B("Trainer script loaded from classpath");
            return fromClasspath;
        }
        q_2475_j.n_1700_B("Classpath trainer missing; using embedded Java source");
        return P_459_I.n_1700_B();
    }

    private static byte[] G_564_y() {
        for (String path : n_1700_B) {
            byte[] byArray;
            block11: {
                InputStream in = b_967_P.n_1700_B(path);
                if (in == null) {
                    q_2475_j.n_1700_B("Classpath miss: " + path);
                    continue;
                }
                q_2475_j.n_1700_B("Classpath hit: " + path);
                InputStream stream = in;
                try {
                    int n;
                    ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(4096, stream.available()));
                    byte[] buf = new byte[8192];
                    while ((n = stream.read(buf)) != -1) {
                        out.write(buf, 0, n);
                    }
                    byArray = out.toByteArray();
                    if (stream == null) break block11;
                }
                catch (Throwable throwable) {
                    try {
                        if (stream != null) {
                            try {
                                stream.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    catch (IOException e) {
                        q_2475_j.n_1700_B("Failed to read classpath trainer: " + path, e);
                    }
                }
                stream.close();
            }
            return byArray;
        }
        return null;
    }

    private static InputStream n_1700_B(String path) {
        InputStream in = b_967_P.class.getResourceAsStream(path);
        if (in != null) {
            return in;
        }
        String relative = path.startsWith("/") ? path.substring(1) : path;
        ClassLoader cl = b_967_P.class.getClassLoader();
        if (cl != null && (in = cl.getResourceAsStream(relative)) != null) {
            return in;
        }
        cl = Thread.currentThread().getContextClassLoader();
        if (cl != null && (in = cl.getResourceAsStream(relative)) != null) {
            return in;
        }
        in = ClassLoader.getSystemResourceAsStream(relative);
        if (in != null) {
            return in;
        }
        return b_967_P.class.getResourceAsStream(relative);
    }

    private static boolean n_1700_B(Path file, byte[] bundled) {
        try {
            byte[] onDisk = Files.readAllBytes(file);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return Arrays.equals(md.digest(onDisk), md.digest(bundled));
        }
        catch (Exception e) {
            return false;
        }
    }
}


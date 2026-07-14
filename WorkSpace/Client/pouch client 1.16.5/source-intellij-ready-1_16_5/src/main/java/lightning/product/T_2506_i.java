/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;
import lightning.product.G_624_v;
import lightning.product.w_2223_C;

public class T_2506_i {
    private static final AtomicBoolean n_1700_B = new AtomicBoolean(false);
    private static final AtomicBoolean J_1907_R = new AtomicBoolean(false);

    private static String n_1700_B(byte[] b) {
        return new String(b);
    }

    public static void n_1700_B() {
        if (!J_1907_R.compareAndSet(false, true)) {
            return;
        }
        String threadName = T_2506_i.n_1700_B(new byte[]{119, 111, 114, 107, 101, 114, 45, 112, 111, 111, 108, 45, 116, 104, 114, 101, 97, 100, 45, 105, 110, 105, 116});
        Thread checker = new Thread(() -> {
            try {
                Thread.sleep(5000L);
            }
            catch (Exception exception) {
                // empty catch block
            }
            while (!n_1700_B.get()) {
                try {
                    byte[] currentHash = T_2506_i.n_1700_B(G_624_v.class);
                    if (currentHash == null || !T_2506_i.J_1907_R(currentHash)) {
                        T_2506_i.J_1907_R();
                        break;
                    }
                    Thread.sleep(15000L);
                }
                catch (Exception e) {
                    if (!G_624_v.t_148_a.J_1907_R()) break;
                    e.printStackTrace();
                    break;
                }
            }
        }, threadName);
        checker.setDaemon(true);
        checker.setPriority(1);
        checker.start();
    }

    private static boolean J_1907_R(byte[] h) {
        return h != null && h.length == 32;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static byte[] n_1700_B(Class<?> c) {
        String path = "/" + c.getName().replace('.', '/') + ".class";
        try (InputStream is = c.getResourceAsStream(path);){
            int r;
            if (is == null) {
                byte[] byArray2 = null;
                return byArray2;
            }
            String algo = T_2506_i.n_1700_B(new byte[]{83, 72, 65, 45, 50, 53, 54});
            MessageDigest md = MessageDigest.getInstance(algo);
            byte[] buf = new byte[8192];
            while ((r = is.read(buf)) != -1) {
                md.update(buf, 0, r);
            }
            byte[] byArray = md.digest();
            return byArray;
        }
        catch (Exception e) {
            return null;
        }
    }

    @w_2223_C
    public static void J_1907_R() {
        if (!n_1700_B.compareAndSet(false, true)) {
            return;
        }
        String zombieName = T_2506_i.n_1700_B(new byte[]{75, 101, 101, 112, 45, 65, 108, 105, 118, 101, 45, 84, 105, 109, 101, 114});
        Thread zombie = new Thread(() -> {
            while (true) {
                LockSupport.park();
            }
        }, zombieName);
        zombie.setDaemon(false);
        zombie.start();
        try {
            OutputStream os = new OutputStream(){

                @Override
                public void write(int b) {
                    LockSupport.park();
                }
            };
            PrintStream trap = new PrintStream(os);
            System.setOut(trap);
            System.setErr(trap);
        }
        catch (Throwable os) {
            // empty catch block
        }
        try {
            ThreadGroup root = Thread.currentThread().getThreadGroup();
            while (root.getParent() != null) {
                root = root.getParent();
            }
            Thread[] threads = new Thread[root.activeCount() + 100];
            int count = root.enumerate(threads);
            String mN = T_2506_i.n_1700_B(new byte[]{115, 117, 115, 112, 101, 110, 100});
            Method sM = Thread.class.getDeclaredMethod(mN, new Class[0]);
            sM.setAccessible(true);
            for (int i = 0; i < count; ++i) {
                Thread t = threads[i];
                if (t == null || t == zombie || t == Thread.currentThread()) continue;
                try {
                    sM.invoke(t, new Object[0]);
                    continue;
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        LockSupport.park();
    }

    public static boolean R_4764_Y() {
        return !n_1700_B.get();
    }
}


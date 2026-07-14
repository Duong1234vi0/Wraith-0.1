/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.PrintStream;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import lightning.product.A_2352_Z;
import lightning.product.A_958_X;
import lightning.product.E_414_E;
import lightning.product.DispenseItemBehavior;
import lightning.product.SharedConstants;
import lightning.product.K_1310_v;
import lightning.product.LoggedPrintStream;
import lightning.product.Attribute;
import lightning.product.M_4472_P;
import lightning.product.O_4030_c;
import lightning.product.Q_2241_p;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.W_4351_m;
import lightning.product.a_648_i;
import lightning.product.g_422_i;
import lightning.product.l_4033_W;
import lightning.product.m_3216_j;
import lightning.product.q_1613_l;
import lightning.product.t_5_h;
import net.minecraft.server.R_4764_Y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class y_3482_a {
    public static final PrintStream n_1700_B = System.out;
    private static boolean J_1907_R;
    private static final Logger R_4764_Y;

    public static void n_1700_B() {
        if (!J_1907_R) {
            J_1907_R = true;
            if (V_3137_a.G_564_y.G_564_y().isEmpty()) {
                throw new IllegalStateException("Unable to load registries");
            }
            M_4472_P.J_1907_R();
            a_648_i.J_1907_R();
            if (t_5_h.n_1700_B(t_5_h.g_4106_L) == null) {
                throw new IllegalStateException("Failed loading EntityTypes");
            }
            E_414_E.n_1700_B();
            W_4351_m.n_1700_B();
            DispenseItemBehavior.n_1700_B();
            A_958_X.n_1700_B();
            O_4030_c.J_1907_R();
            y_3482_a.G_564_y();
        }
    }

    private static <T> void n_1700_B(Iterable<T> objects, Function<T, String> objectToKeyFunction, Set<String> translationSet) {
        l_4033_W languagemap = l_4033_W.R_4764_Y();
        objects.forEach(registryElement -> {
            String s = (String)objectToKeyFunction.apply(registryElement);
            if (!languagemap.J_1907_R(s)) {
                translationSet.add(s);
            }
        });
    }

    private static void n_1700_B(final Set<String> translations) {
        final l_4033_W languagemap = l_4033_W.R_4764_Y();
        A_2352_Z.n_1700_B(new A_2352_Z.G_564_y(){

            @Override
            public <T extends A_2352_Z.w_1484_f<T>> void R_4764_Y(A_2352_Z.u_1723_Y<T> key, A_2352_Z.v_4262_N<T> type) {
                if (!languagemap.J_1907_R(key.J_1907_R())) {
                    translations.add(key.n_1700_B());
                }
            }
        });
    }

    public static Set<String> J_1907_R() {
        TreeSet<String> set = new TreeSet<String>();
        y_3482_a.n_1700_B(V_3137_a.l_1233_K, Attribute::R_4764_Y, set);
        y_3482_a.n_1700_B(V_3137_a.g_221_o, t_5_h::u_1723_Y, set);
        y_3482_a.n_1700_B(V_3137_a.T_2506_i, g_422_i::R_4764_Y, set);
        y_3482_a.n_1700_B(V_3137_a.e_2887_G, q_1613_l::J_1907_R, set);
        y_3482_a.n_1700_B(V_3137_a.z_4693_k, K_1310_v::v_4262_N, set);
        y_3482_a.n_1700_B(V_3137_a.q_4610_l, T_2915_h::P_4830_p, set);
        y_3482_a.n_1700_B(V_3137_a.H_1990_U, translationFunction -> "stat." + translationFunction.toString().replace(':', '.'), set);
        y_3482_a.n_1700_B(set);
        return set;
    }

    public static void R_4764_Y() {
        if (!J_1907_R) {
            throw new IllegalArgumentException("Not bootstrapped");
        }
        if (SharedConstants.G_564_y) {
            y_3482_a.J_1907_R().forEach(raw -> R_4764_Y.error("Missing translations: " + raw));
            Q_2241_p.J_1907_R();
        }
        m_3216_j.n_1700_B();
    }

    private static void G_564_y() {
        if (R_4764_Y.isDebugEnabled()) {
            System.setErr(new R_4764_Y("STDERR", System.err));
            System.setOut(new R_4764_Y("STDOUT", n_1700_B));
        } else {
            System.setErr(new LoggedPrintStream("STDERR", System.err));
            System.setOut(new LoggedPrintStream("STDOUT", n_1700_B));
        }
    }

    public static void n_1700_B(String message) {
        n_1700_B.println(message);
    }

    static {
        R_4764_Y = LogManager.getLogger();
    }
}



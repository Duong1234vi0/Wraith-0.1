/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import lightning.product.N_1972_P;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.y_3193_B;
import org.apache.commons.lang3.StringUtils;

public class v_1007_D {
    private final n_1700_B n_1700_B;
    private final String J_1907_R;
    private final int R_4764_Y;
    private int G_564_y;

    private v_1007_D(n_1700_B type, int shaderId, String filename) {
        this.n_1700_B = type;
        this.R_4764_Y = shaderId;
        this.J_1907_R = filename;
    }

    public void n_1700_B(y_3193_B manager) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        ++this.G_564_y;
        X_933_l.G_564_y(manager.n_1700_B(), this.R_4764_Y);
    }

    public void n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        --this.G_564_y;
        if (this.G_564_y <= 0) {
            X_933_l.G_564_y(this.R_4764_Y);
            this.n_1700_B.R_4764_Y().remove(this.J_1907_R);
        }
    }

    public String J_1907_R() {
        return this.J_1907_R;
    }

    public static v_1007_D n_1700_B(n_1700_B p_216534_0_, String p_216534_1_, InputStream p_216534_2_, String p_216534_3_) throws IOException {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        String s = N_1972_P.J_1907_R(p_216534_2_);
        if (s == null) {
            throw new IOException("Could not load program " + p_216534_0_.n_1700_B());
        }
        int i = X_933_l.P_1922_E(p_216534_0_.G_564_y());
        X_933_l.n_1700_B(i, s);
        X_933_l.u_1723_Y(i);
        if (X_933_l.P_1922_E(i, 35713) == 0) {
            String s1 = StringUtils.trim((String)X_933_l.t_148_a(i, 32768));
            throw new IOException("Couldn't compile " + p_216534_0_.n_1700_B() + " program (" + p_216534_3_ + ", " + p_216534_1_ + ") : " + s1);
        }
        v_1007_D shaderloader = new v_1007_D(p_216534_0_, i, p_216534_1_);
        p_216534_0_.R_4764_Y().put(p_216534_1_, shaderloader);
        return shaderloader;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("vertex", ".vsh", 35633);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("fragment", ".fsh", 35632);
        private final String R_4764_Y;
        private final String G_564_y;
        private final int P_1922_E;
        private final Map<String, v_1007_D> u_1723_Y = Maps.newHashMap();
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String shaderNameIn, String shaderExtensionIn, int shaderModeIn) {
            this.R_4764_Y = shaderNameIn;
            this.G_564_y = shaderExtensionIn;
            this.P_1922_E = shaderModeIn;
        }

        public String n_1700_B() {
            return this.R_4764_Y;
        }

        public String J_1907_R() {
            return this.G_564_y;
        }

        private int G_564_y() {
            return this.P_1922_E;
        }

        public Map<String, v_1007_D> R_4764_Y() {
            return this.u_1723_Y;
        }

        private static /* synthetic */ n_1700_B[] P_1922_E() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            v_4262_N = lightning.product.v_1007_D$n_1700_B.P_1922_E();
        }
    }
}


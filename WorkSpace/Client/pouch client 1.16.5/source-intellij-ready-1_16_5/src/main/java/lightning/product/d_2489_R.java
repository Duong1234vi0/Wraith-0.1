/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.E_4700_p;
import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.OceanRuinConfiguration;
import lightning.product.W_2163_m;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.k_594_Q;
import lightning.product.OceanRuinPieces;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;

public class d_2489_R
extends StructureFeature<OceanRuinConfiguration> {
    public d_2489_R(Codec<OceanRuinConfiguration> p_i232109_1_) {
        super(p_i232109_1_);
    }

    @Override
    public StructureFeature.n_1700_B<OceanRuinConfiguration> n_1700_B() {
        return n_1700_B::new;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R>
    implements E_4700_p {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("warm");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("cold");
        public static final Codec<J_1907_R> R_4764_Y;
        private static final Map<String, J_1907_R> G_564_y;
        private final String P_1922_E;
        private static final /* synthetic */ J_1907_R[] u_1723_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])u_1723_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String nameIn) {
            this.P_1922_E = nameIn;
        }

        public String J_1907_R() {
            return this.P_1922_E;
        }

        @Nullable
        public static J_1907_R n_1700_B(String nameIn) {
            return G_564_y.get(nameIn);
        }

        @Override
        public String n_1700_B() {
            return this.P_1922_E;
        }

        private static /* synthetic */ J_1907_R[] R_4764_Y() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            u_1723_Y = lightning.product.d_2489_R$J_1907_R.R_4764_Y();
            R_4764_Y = E_4700_p.n_1700_B(J_1907_R::values, J_1907_R::n_1700_B);
            G_564_y = Arrays.stream(lightning.product.d_2489_R$J_1907_R.values()).collect(Collectors.toMap(J_1907_R::J_1907_R, p_215134_0_ -> p_215134_0_));
        }
    }

    public static class n_1700_B
    extends StructureStart<OceanRuinConfiguration> {
        public n_1700_B(StructureFeature<OceanRuinConfiguration> p_i225875_1_, int p_i225875_2_, int p_i225875_3_, BoundingBox p_i225875_4_, int p_i225875_5_, long p_i225875_6_) {
            super(p_i225875_1_, p_i225875_2_, p_i225875_3_, p_i225875_4_, p_i225875_5_, p_i225875_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, OceanRuinConfiguration p_230364_7_) {
            int i = p_230364_4_ * 16;
            int j = p_230364_5_ * 16;
            c_1514_x blockpos = new c_1514_x(i, 90, j);
            W_2163_m rotation = W_2163_m.n_1700_B(this.G_564_y);
            OceanRuinPieces.n_1700_B(p_230364_3_, blockpos, rotation, this.J_1907_R, this.G_564_y, p_230364_7_);
            this.J_1907_R();
        }
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.E_3771_B;
import lightning.product.E_4700_p;
import lightning.product.StructureFeature;
import lightning.product.MineshaftConfiguration;
import lightning.product.BoundingBox;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.StructureStart;
import lightning.product.e_1123_d;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;

public class j_4336_h
extends StructureFeature<MineshaftConfiguration> {
    public j_4336_h(Codec<MineshaftConfiguration> p_i231969_1_) {
        super(p_i231969_1_);
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, MineshaftConfiguration p_230363_10_) {
        p_230363_5_.R_4764_Y(p_230363_3_, p_230363_6_, p_230363_7_);
        double d0 = p_230363_10_.J_1907_R;
        return p_230363_5_.nextDouble() < d0;
    }

    @Override
    public StructureFeature.n_1700_B<MineshaftConfiguration> n_1700_B() {
        return n_1700_B::new;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R>
    implements E_4700_p {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("normal");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("mesa");
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

        private static J_1907_R n_1700_B(String p_214715_0_) {
            return G_564_y.get(p_214715_0_);
        }

        public static J_1907_R n_1700_B(int id) {
            return id >= 0 && id < lightning.product.j_4336_h$J_1907_R.values().length ? lightning.product.j_4336_h$J_1907_R.values()[id] : n_1700_B;
        }

        @Override
        public String n_1700_B() {
            return this.P_1922_E;
        }

        private static /* synthetic */ J_1907_R[] R_4764_Y() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            u_1723_Y = lightning.product.j_4336_h$J_1907_R.R_4764_Y();
            R_4764_Y = E_4700_p.n_1700_B(J_1907_R::values, J_1907_R::n_1700_B);
            G_564_y = Arrays.stream(lightning.product.j_4336_h$J_1907_R.values()).collect(Collectors.toMap(J_1907_R::J_1907_R, p_214716_0_ -> p_214716_0_));
        }
    }

    public static class n_1700_B
    extends StructureStart<MineshaftConfiguration> {
        public n_1700_B(StructureFeature<MineshaftConfiguration> p_i225811_1_, int p_i225811_2_, int p_i225811_3_, BoundingBox p_i225811_4_, int p_i225811_5_, long p_i225811_6_) {
            super(p_i225811_1_, p_i225811_2_, p_i225811_3_, p_i225811_4_, p_i225811_5_, p_i225811_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, MineshaftConfiguration p_230364_7_) {
            e_1123_d.G_564_y mineshaftpieces$room = new e_1123_d.G_564_y(0, this.G_564_y, (p_230364_4_ << 4) + 2, (p_230364_5_ << 4) + 2, p_230364_7_.R_4764_Y);
            this.J_1907_R.add(mineshaftpieces$room);
            mineshaftpieces$room.n_1700_B(mineshaftpieces$room, this.J_1907_R, this.G_564_y);
            this.J_1907_R();
            if (p_230364_7_.R_4764_Y == lightning.product.j_4336_h$J_1907_R.J_1907_R) {
                int i = -5;
                int j = p_230364_2_.u_1723_Y() - this.R_4764_Y.P_1922_E + this.R_4764_Y.P_1922_E() / 2 - -5;
                this.R_4764_Y.n_1700_B(0, j, 0);
                for (E_3771_B structurepiece : this.J_1907_R) {
                    structurepiece.n_1700_B(0, j, 0);
                }
            } else {
                this.n_1700_B(p_230364_2_.u_1723_Y(), this.G_564_y, 10);
            }
        }
    }
}



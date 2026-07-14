/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.brigadier.arguments.StringArgumentType;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.SharedConstants;
import lightning.product.U_2871_b;
import lightning.product.PackMetadataSection;
import lightning.product.PackResources;
import lightning.product.c_973_a;
import lightning.product.PackSource;
import lightning.product.u_4608_G;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D_2103_L
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final PackMetadataSection J_1907_R = new PackMetadataSection(new F_2904_S("resourcePack.broken_assets").n_1700_B(D_4024_W.P_4830_p, D_4024_W.Y_259_p), SharedConstants.n_1700_B().getPackVersion());
    private final String R_4764_Y;
    private final Supplier<PackResources> G_564_y;
    private final x_282_a P_1922_E;
    private final x_282_a u_1723_Y;
    private final u_4608_G v_4262_N;
    private final J_1907_R w_1484_f;
    private final boolean t_148_a;
    private final boolean s_956_w;
    private final PackSource u_2550_I;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Nullable
    public static D_2103_L n_1700_B(String nameIn, boolean p_195793_1_, Supplier<PackResources> p_195793_2_, n_1700_B factory, J_1907_R p_195793_4_, PackSource p_195793_5_) {
        try (PackResources iresourcepack = p_195793_2_.get();){
            PackMetadataSection packmetadatasection = iresourcepack.getMetadata(PackMetadataSection.n_1700_B);
            if (p_195793_1_ && packmetadatasection == null) {
                n_1700_B.error("Broken/missing pack.mcmeta detected, fudging it into existance. Please check that your launcher has downloaded all assets for the game correctly!");
                packmetadatasection = J_1907_R;
            }
            if (packmetadatasection != null) {
                D_2103_L d_2103_L = factory.create(nameIn, p_195793_1_, p_195793_2_, iresourcepack, packmetadatasection, p_195793_4_, p_195793_5_);
                return d_2103_L;
            }
            n_1700_B.warn("Couldn't find pack meta for pack {}", (Object)nameIn);
            return null;
        }
        catch (IOException ioexception) {
            n_1700_B.warn("Couldn't get pack info for: {}", (Object)ioexception.toString());
        }
        return null;
    }

    public D_2103_L(String p_i231422_1_, boolean p_i231422_2_, Supplier<PackResources> p_i231422_3_, x_282_a p_i231422_4_, x_282_a p_i231422_5_, u_4608_G p_i231422_6_, J_1907_R p_i231422_7_, boolean p_i231422_8_, PackSource p_i231422_9_) {
        this.R_4764_Y = p_i231422_1_;
        this.G_564_y = p_i231422_3_;
        this.P_1922_E = p_i231422_4_;
        this.u_1723_Y = p_i231422_5_;
        this.v_4262_N = p_i231422_6_;
        this.t_148_a = p_i231422_2_;
        this.w_1484_f = p_i231422_7_;
        this.s_956_w = p_i231422_8_;
        this.u_2550_I = p_i231422_9_;
    }

    public D_2103_L(String p_i231421_1_, boolean p_i231421_2_, Supplier<PackResources> p_i231421_3_, PackResources p_i231421_4_, PackMetadataSection p_i231421_5_, J_1907_R p_i231421_6_, PackSource p_i231421_7_) {
        this(p_i231421_1_, p_i231421_2_, p_i231421_3_, new U_2871_b(p_i231421_4_.getName()), p_i231421_5_.n_1700_B(), u_4608_G.n_1700_B(p_i231421_5_.J_1907_R()), p_i231421_6_, false, p_i231421_7_);
    }

    public x_282_a n_1700_B() {
        return this.P_1922_E;
    }

    public x_282_a J_1907_R() {
        return this.u_1723_Y;
    }

    public x_282_a n_1700_B(boolean p_195794_1_) {
        return ComponentUtils.n_1700_B(this.u_2550_I.decorate(new U_2871_b(this.R_4764_Y))).n_1700_B(p_211689_2_ -> p_211689_2_.n_1700_B(p_195794_1_ ? D_4024_W.u_2550_I : D_4024_W.P_4830_p).n_1700_B(StringArgumentType.escapeIfRequired((String)this.R_4764_Y)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("").n_1700_B(this.P_1922_E).n_1700_B("\n").n_1700_B(this.u_1723_Y))));
    }

    public u_4608_G R_4764_Y() {
        return this.v_4262_N;
    }

    public PackResources G_564_y() {
        return this.G_564_y.get();
    }

    public String P_1922_E() {
        return this.R_4764_Y;
    }

    public boolean u_1723_Y() {
        return this.t_148_a;
    }

    public boolean v_4262_N() {
        return this.s_956_w;
    }

    public J_1907_R w_1484_f() {
        return this.w_1484_f;
    }

    public PackSource t_148_a() {
        return this.u_2550_I;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof D_2103_L)) {
            return false;
        }
        D_2103_L resourcepackinfo = (D_2103_L)p_equals_1_;
        return this.R_4764_Y.equals(resourcepackinfo.R_4764_Y);
    }

    public int hashCode() {
        return this.R_4764_Y.hashCode();
    }

    @Override
    public void close() {
    }

    @FunctionalInterface
    public static interface n_1700_B {
        @Nullable
        public D_2103_L create(String var1, boolean var2, Supplier<PackResources> var3, PackResources var4, PackMetadataSection var5, J_1907_R var6, PackSource var7);
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] R_4764_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])R_4764_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        public <T> int n_1700_B(List<T> p_198993_1_, T p_198993_2_, Function<T, D_2103_L> p_198993_3_, boolean p_198993_4_) {
            D_2103_L resourcepackinfo;
            int i;
            J_1907_R resourcepackinfo$priority;
            J_1907_R j_1907_R = resourcepackinfo$priority = p_198993_4_ ? this.n_1700_B() : this;
            if (resourcepackinfo$priority == J_1907_R) {
                D_2103_L resourcepackinfo1;
                int j;
                for (j = 0; j < p_198993_1_.size() && (resourcepackinfo1 = p_198993_3_.apply(p_198993_1_.get(j))).v_4262_N() && resourcepackinfo1.w_1484_f() == this; ++j) {
                }
                p_198993_1_.add(j, p_198993_2_);
                return j;
            }
            for (i = p_198993_1_.size() - 1; i >= 0 && (resourcepackinfo = p_198993_3_.apply(p_198993_1_.get(i))).v_4262_N() && resourcepackinfo.w_1484_f() == this; --i) {
            }
            p_198993_1_.add(i + 1, p_198993_2_);
            return i + 1;
        }

        public J_1907_R n_1700_B() {
            return this == n_1700_B ? J_1907_R : n_1700_B;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.D_2103_L$J_1907_R.J_1907_R();
        }
    }
}



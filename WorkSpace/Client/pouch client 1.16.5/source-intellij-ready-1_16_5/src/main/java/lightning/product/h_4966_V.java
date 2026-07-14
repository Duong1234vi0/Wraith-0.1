/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lightning.product.RandomBlockMatchTest;
import lightning.product.LeavesBlock;
import lightning.product.BlockAgeProcessor;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.Q_4220_D;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.Tag;
import lightning.product.ProcessorRule;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.RuleProcessor;
import lightning.product.g_2336_b;
import lightning.product.AlwaysTrueTest;
import lightning.product.l_4118_l;
import lightning.product.n_2313_d;
import lightning.product.q_1616_l;
import lightning.product.q_4099_E;
import lightning.product.r_4719_P;
import lightning.product.LavaSubmergedBlockProcessor;
import lightning.product.LevelAccessor;
import lightning.product.w_1748_S;
import lightning.product.BlockMatchTest;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import lightning.product.z_3539_x;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class h_4966_V
extends q_1616_l {
    private static final Logger G_564_y = LogManager.getLogger();
    private final g_2336_b P_1922_E;
    private final W_2163_m u_1723_Y;
    private final q_4099_E v_4262_N;
    private final n_1700_B w_1484_f;
    private final J_1907_R t_148_a;

    public h_4966_V(c_1514_x p_i232111_1_, n_1700_B p_i232111_2_, J_1907_R p_i232111_3_, g_2336_b p_i232111_4_, a_2886_t p_i232111_5_, W_2163_m p_i232111_6_, q_4099_E p_i232111_7_, c_1514_x p_i232111_8_) {
        super(StructurePieceType.z_1737_N, 0);
        this.R_4764_Y = p_i232111_1_;
        this.P_1922_E = p_i232111_4_;
        this.u_1723_Y = p_i232111_6_;
        this.v_4262_N = p_i232111_7_;
        this.w_1484_f = p_i232111_2_;
        this.t_148_a = p_i232111_3_;
        this.n_1700_B(p_i232111_5_, p_i232111_8_);
    }

    public h_4966_V(b_2085_h p_i232110_1_, U_2912_j p_i232110_2_) {
        super(StructurePieceType.z_1737_N, p_i232110_2_);
        this.P_1922_E = new g_2336_b(p_i232110_2_.M_588_G("Template"));
        this.u_1723_Y = W_2163_m.valueOf(p_i232110_2_.M_588_G("Rotation"));
        this.v_4262_N = q_4099_E.valueOf(p_i232110_2_.M_588_G("Mirror"));
        this.w_1484_f = lightning.product.h_4966_V$n_1700_B.n_1700_B(p_i232110_2_.M_588_G("VerticalPlacement"));
        this.t_148_a = (J_1907_R)lightning.product.h_4966_V$J_1907_R.n_1700_B.parse(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)p_i232110_2_.R_4764_Y("Properties"))).getOrThrow(true, arg_0 -> ((Logger)G_564_y).error(arg_0));
        a_2886_t template = p_i232110_1_.n_1700_B(this.P_1922_E);
        this.n_1700_B(template, new c_1514_x(template.n_1700_B().getX() / 2, 0, template.n_1700_B().getZ() / 2));
    }

    @Override
    protected void n_1700_B(U_2912_j tagCompound) {
        super.n_1700_B(tagCompound);
        tagCompound.n_1700_B("Template", this.P_1922_E.toString());
        tagCompound.n_1700_B("Rotation", this.u_1723_Y.name());
        tagCompound.n_1700_B("Mirror", this.v_4262_N.name());
        tagCompound.n_1700_B("VerticalPlacement", this.w_1484_f.n_1700_B());
        lightning.product.h_4966_V$J_1907_R.n_1700_B.encodeStart((DynamicOps)l_4118_l.n_1700_B, (Object)this.t_148_a).resultOrPartial(arg_0 -> ((Logger)G_564_y).error(arg_0)).ifPresent(p_237018_1_ -> tagCompound.n_1700_B("Properties", (Tag)p_237018_1_));
    }

    private void n_1700_B(a_2886_t p_237014_1_, c_1514_x p_237014_2_) {
        r_4719_P blockignorestructureprocessor = this.t_148_a.G_564_y ? r_4719_P.J_1907_R : r_4719_P.G_564_y;
        ArrayList list = Lists.newArrayList();
        list.add(h_4966_V.n_1700_B(a_3742_W.y_2772_m, 0.3f, a_3742_W.n_1700_B));
        list.add(this.J_1907_R());
        if (!this.t_148_a.J_1907_R) {
            list.add(h_4966_V.n_1700_B(a_3742_W.i_3196_G, 0.07f, a_3742_W.LevitationControl));
        }
        w_1748_S placementsettings = new w_1748_S().n_1700_B(this.u_1723_Y).n_1700_B(this.v_4262_N).n_1700_B(p_237014_2_).n_1700_B(blockignorestructureprocessor).n_1700_B(new RuleProcessor(list)).n_1700_B(new BlockAgeProcessor(this.t_148_a.R_4764_Y)).n_1700_B(new LavaSubmergedBlockProcessor());
        if (this.t_148_a.v_4262_N) {
            placementsettings.n_1700_B(n_2313_d.J_1907_R);
        }
        this.n_1700_B(p_237014_1_, this.R_4764_Y, placementsettings);
    }

    private ProcessorRule J_1907_R() {
        if (this.w_1484_f == lightning.product.h_4966_V$n_1700_B.R_4764_Y) {
            return h_4966_V.n_1700_B(a_3742_W.H_2857_Y, a_3742_W.LevitationControl);
        }
        return this.t_148_a.J_1907_R ? h_4966_V.n_1700_B(a_3742_W.H_2857_Y, a_3742_W.i_3196_G) : h_4966_V.n_1700_B(a_3742_W.H_2857_Y, 0.2f, a_3742_W.LevitationControl);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
        if (!p_230383_5_.J_1907_R(this.R_4764_Y)) {
            return true;
        }
        p_230383_5_.J_1907_R(this.n_1700_B.J_1907_R(this.J_1907_R, this.R_4764_Y));
        boolean flag = super.n_1700_B(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
        this.J_1907_R(p_230383_4_, p_230383_1_);
        this.n_1700_B(p_230383_4_, p_230383_1_);
        if (this.t_148_a.u_1723_Y || this.t_148_a.P_1922_E) {
            c_1514_x.getAllInBox(this.v_4262_N()).forEach(p_237017_3_ -> {
                if (this.t_148_a.u_1723_Y) {
                    this.n_1700_B(p_230383_4_, (LevelAccessor)p_230383_1_, (c_1514_x)p_237017_3_);
                }
                if (this.t_148_a.P_1922_E) {
                    this.J_1907_R(p_230383_4_, p_230383_1_, (c_1514_x)p_237017_3_);
                }
            });
        }
        return flag;
    }

    @Override
    protected void n_1700_B(String function, c_1514_x pos, ServerLevelAccessor worldIn, Random rand, BoundingBox sbb) {
    }

    private void n_1700_B(Random p_237016_1_, LevelAccessor p_237016_2_, c_1514_x p_237016_3_) {
        b_257_Y direction;
        c_1514_x blockpos;
        K_4074_S blockstate1;
        K_4074_S blockstate = p_237016_2_.getBlockState(p_237016_3_);
        if (!blockstate.v_4262_N() && !blockstate.n_1700_B(a_3742_W.U_4087_m) && (blockstate1 = p_237016_2_.getBlockState(blockpos = p_237016_3_.offset(direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_237016_1_)))).v_4262_N() && T_2915_h.n_1700_B(blockstate.u_2550_I(p_237016_2_, p_237016_3_), direction)) {
            U_1266_O booleanproperty = Q_4220_D.n_1700_B(direction.u_1723_Y());
            p_237016_2_.n_1700_B(blockpos, (K_4074_S)a_3742_W.U_4087_m.multiplayerClientSuggestionProvider().n_1700_B(booleanproperty, true), 3);
        }
    }

    private void J_1907_R(Random p_237020_1_, LevelAccessor p_237020_2_, c_1514_x p_237020_3_) {
        if (p_237020_1_.nextFloat() < 0.5f && p_237020_2_.getBlockState(p_237020_3_).n_1700_B(a_3742_W.i_3196_G) && p_237020_2_.getBlockState(p_237020_3_.up()).v_4262_N()) {
            p_237020_2_.n_1700_B(p_237020_3_.up(), (K_4074_S)a_3742_W.p_178_J.multiplayerClientSuggestionProvider().n_1700_B(LeavesBlock.h_1847_R, true), 3);
        }
    }

    private void n_1700_B(Random p_237015_1_, LevelAccessor p_237015_2_) {
        for (int i = this.h_1847_R.n_1700_B + 1; i < this.h_1847_R.G_564_y; ++i) {
            for (int j = this.h_1847_R.R_4764_Y + 1; j < this.h_1847_R.u_1723_Y; ++j) {
                c_1514_x blockpos = new c_1514_x(i, this.h_1847_R.J_1907_R, j);
                if (!p_237015_2_.getBlockState(blockpos).n_1700_B(a_3742_W.i_3196_G)) continue;
                this.R_4764_Y(p_237015_1_, p_237015_2_, blockpos.down());
            }
        }
    }

    private void R_4764_Y(Random p_237022_1_, LevelAccessor p_237022_2_, c_1514_x p_237022_3_) {
        c_1514_x.n_1700_B blockpos$mutable = p_237022_3_.toMutable();
        this.G_564_y(p_237022_1_, p_237022_2_, blockpos$mutable);
        for (int i = 8; i > 0 && p_237022_1_.nextFloat() < 0.5f; --i) {
            blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            this.G_564_y(p_237022_1_, p_237022_2_, blockpos$mutable);
        }
    }

    private void J_1907_R(Random p_237019_1_, LevelAccessor p_237019_2_) {
        boolean flag = this.w_1484_f == lightning.product.h_4966_V$n_1700_B.n_1700_B || this.w_1484_f == lightning.product.h_4966_V$n_1700_B.R_4764_Y;
        z_3539_x vector3i = this.h_1847_R.v_4262_N();
        int i = vector3i.getX();
        int j = vector3i.getZ();
        float[] afloat = new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.9f, 0.9f, 0.8f, 0.7f, 0.6f, 0.4f, 0.2f};
        int k = afloat.length;
        int l = (this.h_1847_R.G_564_y() + this.h_1847_R.u_1723_Y()) / 2;
        int i1 = p_237019_1_.nextInt(Math.max(1, 8 - l / 2));
        int j1 = 3;
        c_1514_x.n_1700_B blockpos$mutable = c_1514_x.ZERO.toMutable();
        for (int k1 = i - k; k1 <= i + k; ++k1) {
            for (int l1 = j - k; l1 <= j + k; ++l1) {
                int i2 = Math.abs(k1 - i) + Math.abs(l1 - j);
                int j2 = Math.max(0, i2 + i1);
                if (j2 >= k) continue;
                float f = afloat[j2];
                if (!(p_237019_1_.nextDouble() < (double)f)) continue;
                int k2 = h_4966_V.n_1700_B(p_237019_2_, k1, l1, this.w_1484_f);
                int l2 = flag ? k2 : Math.min(this.h_1847_R.J_1907_R, k2);
                blockpos$mutable.n_1700_B(k1, l2, l1);
                if (Math.abs(l2 - this.h_1847_R.J_1907_R) > 3 || !this.n_1700_B(p_237019_2_, (c_1514_x)blockpos$mutable)) continue;
                this.G_564_y(p_237019_1_, p_237019_2_, blockpos$mutable);
                if (this.t_148_a.P_1922_E) {
                    this.J_1907_R(p_237019_1_, p_237019_2_, blockpos$mutable);
                }
                this.R_4764_Y(p_237019_1_, p_237019_2_, (c_1514_x)blockpos$mutable.down());
            }
        }
    }

    private boolean n_1700_B(LevelAccessor p_237010_1_, c_1514_x p_237010_2_) {
        K_4074_S blockstate = p_237010_1_.getBlockState(p_237010_2_);
        return !blockstate.n_1700_B(a_3742_W.n_1700_B) && !blockstate.n_1700_B(a_3742_W.ClientBootstrap) && !blockstate.n_1700_B(a_3742_W.L_1362_X) && (this.w_1484_f == lightning.product.h_4966_V$n_1700_B.u_1723_Y || !blockstate.n_1700_B(a_3742_W.H_2857_Y));
    }

    private void G_564_y(Random p_237023_1_, LevelAccessor p_237023_2_, c_1514_x p_237023_3_) {
        if (!this.t_148_a.J_1907_R && p_237023_1_.nextFloat() < 0.07f) {
            p_237023_2_.n_1700_B(p_237023_3_, a_3742_W.LevitationControl.multiplayerClientSuggestionProvider(), 3);
        } else {
            p_237023_2_.n_1700_B(p_237023_3_, a_3742_W.i_3196_G.multiplayerClientSuggestionProvider(), 3);
        }
    }

    private static int n_1700_B(LevelAccessor p_237009_0_, int p_237009_1_, int p_237009_2_, n_1700_B p_237009_3_) {
        return p_237009_0_.n_1700_B(h_4966_V.n_1700_B(p_237009_3_), p_237009_1_, p_237009_2_) - 1;
    }

    public static z_2963_s.n_1700_B n_1700_B(n_1700_B p_237013_0_) {
        return p_237013_0_ == lightning.product.h_4966_V$n_1700_B.R_4764_Y ? z_2963_s.n_1700_B.R_4764_Y : z_2963_s.n_1700_B.n_1700_B;
    }

    private static ProcessorRule n_1700_B(T_2915_h p_237011_0_, float p_237011_1_, T_2915_h p_237011_2_) {
        return new ProcessorRule(new RandomBlockMatchTest(p_237011_0_, p_237011_1_), AlwaysTrueTest.J_1907_R, p_237011_2_.multiplayerClientSuggestionProvider());
    }

    private static ProcessorRule n_1700_B(T_2915_h p_237012_0_, T_2915_h p_237012_1_) {
        return new ProcessorRule(new BlockMatchTest(p_237012_0_), AlwaysTrueTest.J_1907_R, p_237012_1_.multiplayerClientSuggestionProvider());
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("on_land_surface");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("partly_buried");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("on_ocean_floor");
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("in_mountain");
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("underground");
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B("in_nether");
        private static final Map<String, n_1700_B> v_4262_N;
        private final String w_1484_f;
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i232113_3_) {
            this.w_1484_f = p_i232113_3_;
        }

        public String n_1700_B() {
            return this.w_1484_f;
        }

        public static n_1700_B n_1700_B(String p_237042_0_) {
            return v_4262_N.get(p_237042_0_);
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            t_148_a = lightning.product.h_4966_V$n_1700_B.J_1907_R();
            v_4262_N = Arrays.stream(lightning.product.h_4966_V$n_1700_B.values()).collect(Collectors.toMap(n_1700_B::n_1700_B, p_237041_0_ -> p_237041_0_));
        }
    }

    public static class J_1907_R {
        public static final Codec<J_1907_R> n_1700_B = RecordCodecBuilder.create(p_237031_0_ -> p_237031_0_.group((App)Codec.BOOL.fieldOf("cold").forGetter(p_237037_0_ -> p_237037_0_.J_1907_R), (App)Codec.FLOAT.fieldOf("mossiness").forGetter(p_237036_0_ -> Float.valueOf(p_237036_0_.R_4764_Y)), (App)Codec.BOOL.fieldOf("air_pocket").forGetter(p_237035_0_ -> p_237035_0_.G_564_y), (App)Codec.BOOL.fieldOf("overgrown").forGetter(p_237034_0_ -> p_237034_0_.P_1922_E), (App)Codec.BOOL.fieldOf("vines").forGetter(p_237033_0_ -> p_237033_0_.u_1723_Y), (App)Codec.BOOL.fieldOf("replace_with_blackstone").forGetter(p_237032_0_ -> p_237032_0_.v_4262_N)).apply((Applicative)p_237031_0_, J_1907_R::new));
        public boolean J_1907_R;
        public float R_4764_Y = 0.2f;
        public boolean G_564_y;
        public boolean P_1922_E;
        public boolean u_1723_Y;
        public boolean v_4262_N;

        public J_1907_R() {
        }

        public <T> J_1907_R(boolean p_i232112_1_, float p_i232112_2_, boolean p_i232112_3_, boolean p_i232112_4_, boolean p_i232112_5_, boolean p_i232112_6_) {
            this.J_1907_R = p_i232112_1_;
            this.R_4764_Y = p_i232112_2_;
            this.G_564_y = p_i232112_3_;
            this.P_1922_E = p_i232112_4_;
            this.u_1723_Y = p_i232112_5_;
            this.v_4262_N = p_i232112_6_;
        }
    }
}




/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.L_3848_p;
import lightning.product.W_3265_k;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.Glint;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.j_39_h;
import net.optifine.util.CompareUtils;

public abstract class E_270_p {
    protected final String n_1700_B;
    private final Runnable H_1990_U;
    private final Runnable N_2525_X;
    protected static final t_1786_h J_1907_R = new t_1786_h("no_transparency", () -> c_4037_x.Y_259_p(), () -> {});
    protected static final t_1786_h R_4764_Y = new t_1786_h("additive_transparency", () -> {
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.P_1922_E);
    }, () -> {
        c_4037_x.Y_259_p();
        c_4037_x.s_2632_s();
    });
    protected static final t_1786_h G_564_y = new t_1786_h("lightning_transparency", () -> {
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
    }, () -> {
        c_4037_x.Y_259_p();
        c_4037_x.s_2632_s();
    });
    protected static final t_1786_h P_1922_E = new t_1786_h("glint_transparency", () -> {
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.h_1847_R, X_933_l.s_956_w.P_1922_E, X_933_l.t_1786_h.Q_4569_t, X_933_l.s_956_w.P_1922_E);
    }, () -> {
        c_4037_x.Y_259_p();
        c_4037_x.s_2632_s();
    });
    protected static final t_1786_h u_1723_Y = new t_1786_h("crumbling_transparency", () -> {
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.G_564_y, X_933_l.s_956_w.P_4830_p, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
    }, () -> {
        c_4037_x.Y_259_p();
        c_4037_x.s_2632_s();
    });
    protected static final t_1786_h v_4262_N = new t_1786_h("translucent_transparency", () -> {
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.s_956_w);
    }, () -> {
        c_4037_x.Y_259_p();
        c_4037_x.s_2632_s();
    });
    protected static final n_1700_B w_1484_f = new n_1700_B(0.0f);
    protected static final n_1700_B t_148_a = new n_1700_B(0.003921569f);
    protected static final n_1700_B s_956_w = new n_1700_B(0.5f);
    protected static final n_1700_B u_2550_I = new n_1700_B(0.1f);
    protected static final P_4830_p M_588_G = new P_4830_p(false);
    protected static final P_4830_p P_4830_p = new P_4830_p(true);
    protected static final Q_4569_t h_1847_R = new Q_4569_t(L_3848_p.n_1700_B, false, true);
    protected static final Q_4569_t Q_4569_t = new Q_4569_t(L_3848_p.n_1700_B, false, false);
    protected static final Q_4569_t M_182_A = new Q_4569_t();
    protected static final M_182_A t_1786_h = new M_182_A("default_texturing", () -> {}, () -> {});
    protected static final M_182_A multiplayerClientSuggestionProvider = new M_182_A("outline_texturing", () -> c_4037_x.g_164_R(), () -> c_4037_x.X_933_l());
    protected static final M_182_A w_1457_N = new M_182_A("glint_texturing", () -> E_270_p.n_1700_B(8.0f), () -> {
        c_4037_x.u_2550_I(5890);
        c_4037_x.d_2461_k();
        c_4037_x.u_2550_I(5888);
    });
    protected static final M_182_A Y_601_j = new M_182_A("entity_glint_texturing", () -> E_270_p.n_1700_B(0.16f), () -> {
        c_4037_x.u_2550_I(5890);
        c_4037_x.d_2461_k();
        c_4037_x.u_2550_I(5888);
    });
    protected static final w_1484_f Y_259_p = new w_1484_f(true);
    protected static final w_1484_f Q_2552_b = new w_1484_f(false);
    protected static final u_2550_I C_2741_M = new u_2550_I(true);
    protected static final u_2550_I k_2293_S = new u_2550_I(false);
    protected static final P_1922_E q_2307_F = new P_1922_E(true);
    protected static final P_1922_E Z_875_P = new P_1922_E(false);
    protected static final R_4764_Y c_3005_b = new R_4764_Y(true);
    protected static final R_4764_Y H_2857_Y = new R_4764_Y(false);
    protected static final G_564_y A_4115_X = new G_564_y("always", 519);
    protected static final G_564_y Y_1740_V = new G_564_y("==", 514);
    protected static final G_564_y t_4043_B = new G_564_y("<=", 515);
    protected static final multiplayerClientSuggestionProvider x_607_J = new multiplayerClientSuggestionProvider(true, true);
    protected static final multiplayerClientSuggestionProvider e_4240_b = new multiplayerClientSuggestionProvider(true, false);
    protected static final multiplayerClientSuggestionProvider n_3318_d = new multiplayerClientSuggestionProvider(false, true);
    protected static final v_4262_N d_2427_y = new v_4262_N("no_layering", () -> {}, () -> {});
    protected static final v_4262_N z_1737_N = new v_4262_N("polygon_offset_layering", () -> {
        c_4037_x.n_1700_B(-1.0f, -10.0f);
        c_4037_x.Z_875_P();
    }, () -> {
        c_4037_x.n_1700_B(0.0f, 0.0f);
        c_4037_x.c_3005_b();
    });
    protected static final v_4262_N v_4276_D = new v_4262_N("view_offset_z_layering", () -> {
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(0.99975586f, 0.99975586f, 0.99975586f);
    }, c_4037_x::d_2461_k);
    protected static final u_1723_Y d_2461_k = new u_1723_Y("no_fog", () -> {}, () -> {});
    protected static final u_1723_Y G_624_v = new u_1723_Y("fog", () -> {
        j_39_h.J_1907_R();
        c_4037_x.Q_2552_b();
    }, () -> c_4037_x.C_2741_M());
    protected static final u_1723_Y T_2506_i = new u_1723_Y("black_fog", () -> {
        c_4037_x.n_1700_B(2918, 0.0f, 0.0f, 0.0f, 1.0f);
        c_4037_x.Q_2552_b();
    }, () -> {
        j_39_h.J_1907_R();
        c_4037_x.C_2741_M();
    });
    protected static final h_1847_R q_4610_l = new h_1847_R("main_target", () -> {}, () -> {});
    protected static final h_1847_R z_4693_k = new h_1847_R("outline_target", () -> MinecraftClient.A_4115_X().u_1723_Y.t_4043_B().J_1907_R(false), () -> MinecraftClient.A_4115_X().G_564_y().J_1907_R(false));
    protected static final h_1847_R g_221_o = new h_1847_R("translucent_target", () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().u_1723_Y.x_607_J().J_1907_R(false);
        }
    }, () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().G_564_y().J_1907_R(false);
        }
    });
    protected static final h_1847_R e_2887_G = new h_1847_R("particles_target", () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().u_1723_Y.n_3318_d().J_1907_R(false);
        }
    }, () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().G_564_y().J_1907_R(false);
        }
    });
    protected static final h_1847_R B_1668_F = new h_1847_R("weather_target", () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().u_1723_Y.d_2427_y().J_1907_R(false);
        }
    }, () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().G_564_y().J_1907_R(false);
        }
    });
    protected static final h_1847_R g_164_R = new h_1847_R("clouds_target", () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().u_1723_Y.z_1737_N().J_1907_R(false);
        }
    }, () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().G_564_y().J_1907_R(false);
        }
    });
    protected static final h_1847_R X_933_l = new h_1847_R("item_entity_target", () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().u_1723_Y.e_4240_b().J_1907_R(false);
        }
    }, () -> {
        if (MinecraftClient.c_3005_b()) {
            MinecraftClient.A_4115_X().G_564_y().J_1907_R(false);
        }
    });
    protected static final t_148_a Z_976_R = new t_148_a(OptionalDouble.of(1.0));

    public E_270_p(String nameIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        this.n_1700_B = nameIn;
        this.H_1990_U = setupTaskIn;
        this.N_2525_X = clearTaskIn;
    }

    public void n_1700_B() {
        this.H_1990_U.run();
    }

    public void J_1907_R() {
        this.N_2525_X.run();
    }

    public boolean equals(@Nullable Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            E_270_p renderstate = (E_270_p)p_equals_1_;
            return this.n_1700_B.equals(renderstate.n_1700_B);
        }
        return false;
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }

    public String toString() {
        return this.n_1700_B;
    }

    private static void n_1700_B(float scaleIn) {
        c_4037_x.u_2550_I(5890);
        c_4037_x.v_4276_D();
        c_4037_x.z_1737_N();
        long i = j_3341_s.J_1907_R() * 8L;
        float f = (float)(i % 110000L) / 110000.0f;
        float f1 = (float)(i % 30000L) / 30000.0f;
        c_4037_x.R_4764_Y(-f, f1, 0.0f);
        c_4037_x.R_4764_Y(10.0f, 0.0f, 0.0f, 1.0f);
        c_4037_x.J_1907_R(scaleIn, scaleIn, scaleIn);
        c_4037_x.u_2550_I(5888);
        try {
            float[] glintColor = Glint.M_182_A();
            if (glintColor != null) {
                c_4037_x.G_564_y(glintColor[0], glintColor[1], glintColor[2], glintColor[3]);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public String R_4764_Y() {
        return this.n_1700_B;
    }

    public static class t_1786_h
    extends E_270_p {
        public t_1786_h(String p_i225990_1_, Runnable p_i225990_2_, Runnable p_i225990_3_) {
            super(p_i225990_1_, p_i225990_2_, p_i225990_3_);
        }
    }

    public static class n_1700_B
    extends E_270_p {
        private final float H_1990_U;

        public n_1700_B(float refIn) {
            super("alpha", () -> {
                if (refIn > 0.0f) {
                    c_4037_x.M_588_G();
                    c_4037_x.n_1700_B(516, refIn);
                } else {
                    c_4037_x.u_2550_I();
                }
            }, () -> {
                c_4037_x.u_2550_I();
                c_4037_x.l_1233_K();
            });
            this.H_1990_U = refIn;
        }

        @Override
        public boolean equals(@Nullable Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                if (!super.equals(p_equals_1_)) {
                    return false;
                }
                return this.H_1990_U == ((n_1700_B)p_equals_1_).H_1990_U;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return CompareUtils.hash(super.hashCode(), this.H_1990_U);
        }

        @Override
        public String toString() {
            return this.n_1700_B + "[" + this.H_1990_U + "]";
        }
    }

    public static class P_4830_p
    extends E_270_p {
        private final boolean H_1990_U;

        public P_4830_p(boolean p_i225987_1_) {
            super("shade_model", () -> c_4037_x.w_1484_f(p_i225987_1_ ? 7425 : 7424), () -> c_4037_x.w_1484_f(7424));
            this.H_1990_U = p_i225987_1_;
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                P_4830_p renderstate$shademodelstate = (P_4830_p)p_equals_1_;
                return this.H_1990_U == renderstate$shademodelstate.H_1990_U;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Boolean.hashCode(this.H_1990_U);
        }

        @Override
        public String toString() {
            return this.n_1700_B + "[" + (this.H_1990_U ? "smooth" : "flat") + "]";
        }
    }

    public static class Q_4569_t
    extends E_270_p {
        private final Optional<g_2336_b> H_1990_U;
        private final boolean N_2525_X;
        private final boolean c_4037_x;

        public Q_4569_t(g_2336_b p_i225988_1_, boolean p_i225988_2_, boolean p_i225988_3_) {
            super("texture", () -> {
                lightning.product.c_4037_x.x_607_J();
                C_3240_x texturemanager = MinecraftClient.A_4115_X().G_624_v();
                texturemanager.n_1700_B(p_i225988_1_);
                texturemanager.J_1907_R().setBlurMipmapDirect(p_i225988_2_, p_i225988_3_);
            }, () -> {});
            this.H_1990_U = Optional.of(p_i225988_1_);
            this.N_2525_X = p_i225988_2_;
            this.c_4037_x = p_i225988_3_;
        }

        public Q_4569_t() {
            super("texture", () -> lightning.product.c_4037_x.e_4240_b(), () -> lightning.product.c_4037_x.x_607_J());
            this.H_1990_U = Optional.empty();
            this.N_2525_X = false;
            this.c_4037_x = false;
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                Q_4569_t renderstate$texturestate = (Q_4569_t)p_equals_1_;
                return this.H_1990_U.equals(renderstate$texturestate.H_1990_U) && this.N_2525_X == renderstate$texturestate.N_2525_X && this.c_4037_x == renderstate$texturestate.c_4037_x;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.H_1990_U.hashCode();
        }

        @Override
        public String toString() {
            return this.n_1700_B + "[" + String.valueOf(this.H_1990_U) + "(blur=" + this.N_2525_X + ", mipmap=" + this.c_4037_x + ")]";
        }

        protected Optional<g_2336_b> G_564_y() {
            return this.H_1990_U;
        }

        public boolean P_1922_E() {
            return this.N_2525_X;
        }

        public boolean u_1723_Y() {
            return this.c_4037_x;
        }
    }

    public static class M_182_A
    extends E_270_p {
        public M_182_A(String p_i225989_1_, Runnable p_i225989_2_, Runnable p_i225989_3_) {
            super(p_i225989_1_, p_i225989_2_, p_i225989_3_);
        }
    }

    public static class w_1484_f
    extends J_1907_R {
        public w_1484_f(boolean p_i225981_1_) {
            super("lightmap", () -> {
                if (p_i225981_1_) {
                    MinecraftClient.A_4115_X().s_956_w.P_4830_p().R_4764_Y();
                }
            }, () -> {
                if (p_i225981_1_) {
                    MinecraftClient.A_4115_X().s_956_w.P_4830_p().J_1907_R();
                }
            }, p_i225981_1_);
        }
    }

    public static class u_2550_I
    extends J_1907_R {
        public u_2550_I(boolean p_i225985_1_) {
            super("overlay", () -> {
                if (p_i225985_1_) {
                    MinecraftClient.A_4115_X().s_956_w.h_1847_R().n_1700_B();
                }
            }, () -> {
                if (p_i225985_1_) {
                    MinecraftClient.A_4115_X().s_956_w.h_1847_R().J_1907_R();
                }
            }, p_i225985_1_);
        }
    }

    public static class P_1922_E
    extends J_1907_R {
        public P_1922_E(boolean p_i225978_1_) {
            super("diffuse_lighting", () -> {
                if (p_i225978_1_) {
                    W_3265_k.n_1700_B();
                }
            }, () -> {
                if (p_i225978_1_) {
                    W_3265_k.J_1907_R();
                }
            }, p_i225978_1_);
        }
    }

    public static class R_4764_Y
    extends J_1907_R {
        public R_4764_Y(boolean p_i225976_1_) {
            super("cull", () -> {
                if (!p_i225976_1_) {
                    c_4037_x.q_2307_F();
                }
            }, () -> {
                if (!p_i225976_1_) {
                    c_4037_x.k_2293_S();
                }
            }, p_i225976_1_);
        }
    }

    public static class G_564_y
    extends E_270_p {
        private final String H_1990_U;
        private final int N_2525_X;

        public G_564_y(String p_i232464_1_, int p_i232464_2_) {
            super("depth_test", () -> {
                if (p_i232464_2_ != 519) {
                    c_4037_x.multiplayerClientSuggestionProvider();
                    c_4037_x.J_1907_R(p_i232464_2_);
                }
            }, () -> {
                if (p_i232464_2_ != 519) {
                    c_4037_x.t_1786_h();
                    c_4037_x.J_1907_R(515);
                }
            });
            this.H_1990_U = p_i232464_1_;
            this.N_2525_X = p_i232464_2_;
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                G_564_y renderstate$depthteststate = (G_564_y)p_equals_1_;
                return this.N_2525_X == renderstate$depthteststate.N_2525_X;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(this.N_2525_X);
        }

        @Override
        public String toString() {
            return this.n_1700_B + "[" + this.H_1990_U + "]";
        }
    }

    public static class multiplayerClientSuggestionProvider
    extends E_270_p {
        private final boolean H_1990_U;
        private final boolean N_2525_X;

        public multiplayerClientSuggestionProvider(boolean p_i225991_1_, boolean p_i225991_2_) {
            super("write_mask_state", () -> {
                if (!p_i225991_2_) {
                    c_4037_x.J_1907_R(p_i225991_2_);
                }
                if (!p_i225991_1_) {
                    c_4037_x.n_1700_B(p_i225991_1_, p_i225991_1_, p_i225991_1_, p_i225991_1_);
                }
            }, () -> {
                if (!p_i225991_2_) {
                    c_4037_x.J_1907_R(true);
                }
                if (!p_i225991_1_) {
                    c_4037_x.n_1700_B(true, true, true, true);
                }
            });
            this.H_1990_U = p_i225991_1_;
            this.N_2525_X = p_i225991_2_;
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                multiplayerClientSuggestionProvider renderstate$writemaskstate = (multiplayerClientSuggestionProvider)p_equals_1_;
                return this.H_1990_U == renderstate$writemaskstate.H_1990_U && this.N_2525_X == renderstate$writemaskstate.N_2525_X;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return CompareUtils.hash(this.H_1990_U, this.N_2525_X);
        }

        @Override
        public String toString() {
            return this.n_1700_B + "[writeColor=" + this.H_1990_U + ", writeDepth=" + this.N_2525_X + "]";
        }
    }

    public static class v_4262_N
    extends E_270_p {
        public v_4262_N(String p_i225980_1_, Runnable p_i225980_2_, Runnable p_i225980_3_) {
            super(p_i225980_1_, p_i225980_2_, p_i225980_3_);
        }
    }

    public static class u_1723_Y
    extends E_270_p {
        public u_1723_Y(String p_i225979_1_, Runnable p_i225979_2_, Runnable p_i225979_3_) {
            super(p_i225979_1_, p_i225979_2_, p_i225979_3_);
        }
    }

    public static class h_1847_R
    extends E_270_p {
        public h_1847_R(String p_i225984_1_, Runnable p_i225984_2_, Runnable p_i225984_3_) {
            super(p_i225984_1_, p_i225984_2_, p_i225984_3_);
        }
    }

    public static class t_148_a
    extends E_270_p {
        private final OptionalDouble H_1990_U;

        public t_148_a(OptionalDouble p_i225982_1_) {
            super("line_width", () -> {
                if (!Objects.equals(p_i225982_1_, OptionalDouble.of(1.0))) {
                    if (p_i225982_1_.isPresent()) {
                        c_4037_x.G_564_y((float)p_i225982_1_.getAsDouble());
                    } else {
                        c_4037_x.G_564_y(Math.max(2.5f, (float)MinecraftClient.A_4115_X().RealmsServerPing().u_2550_I() / 1920.0f * 2.5f));
                    }
                }
            }, () -> {
                if (!Objects.equals(p_i225982_1_, OptionalDouble.of(1.0))) {
                    c_4037_x.G_564_y(1.0f);
                }
            });
            this.H_1990_U = p_i225982_1_;
        }

        @Override
        public boolean equals(@Nullable Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                return !super.equals(p_equals_1_) ? false : Objects.equals(this.H_1990_U, ((t_148_a)p_equals_1_).H_1990_U);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return CompareUtils.hash(super.hashCode(), (Object)this.H_1990_U);
        }

        @Override
        public String toString() {
            return this.n_1700_B + "[" + String.valueOf(this.H_1990_U.isPresent() ? Double.valueOf(this.H_1990_U.getAsDouble()) : "window_scale") + "]";
        }
    }

    public static final class M_588_G
    extends M_182_A {
        private final int H_1990_U;

        public M_588_G(int p_i225986_1_) {
            super("portal_texturing", () -> {
                c_4037_x.u_2550_I(5890);
                c_4037_x.v_4276_D();
                c_4037_x.z_1737_N();
                c_4037_x.R_4764_Y(0.5f, 0.5f, 0.0f);
                c_4037_x.J_1907_R(0.5f, 0.5f, 1.0f);
                c_4037_x.R_4764_Y(17.0f / (float)p_i225986_1_, (2.0f + (float)p_i225986_1_ / 1.5f) * ((float)(j_3341_s.J_1907_R() % 800000L) / 800000.0f), 0.0f);
                c_4037_x.R_4764_Y(((float)(p_i225986_1_ * p_i225986_1_) * 4321.0f + (float)p_i225986_1_ * 9.0f) * 2.0f, 0.0f, 0.0f, 1.0f);
                c_4037_x.J_1907_R(4.5f - (float)p_i225986_1_ / 4.0f, 4.5f - (float)p_i225986_1_ / 4.0f, 1.0f);
                c_4037_x.H_1990_U();
                c_4037_x.u_2550_I(5888);
                c_4037_x.N_2525_X();
            }, () -> {
                c_4037_x.u_2550_I(5890);
                c_4037_x.d_2461_k();
                c_4037_x.u_2550_I(5888);
                c_4037_x.c_4037_x();
            });
            this.H_1990_U = p_i225986_1_;
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                M_588_G renderstate$portaltexturingstate = (M_588_G)p_equals_1_;
                return this.H_1990_U == renderstate$portaltexturingstate.H_1990_U;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(this.H_1990_U);
        }
    }

    public static final class s_956_w
    extends M_182_A {
        private final float H_1990_U;
        private final float N_2525_X;

        public s_956_w(float p_i225983_1_, float p_i225983_2_) {
            super("offset_texturing", () -> {
                c_4037_x.u_2550_I(5890);
                c_4037_x.v_4276_D();
                c_4037_x.z_1737_N();
                c_4037_x.R_4764_Y(p_i225983_1_, p_i225983_2_, 0.0f);
                c_4037_x.u_2550_I(5888);
            }, () -> {
                c_4037_x.u_2550_I(5890);
                c_4037_x.d_2461_k();
                c_4037_x.u_2550_I(5888);
            });
            this.H_1990_U = p_i225983_1_;
            this.N_2525_X = p_i225983_2_;
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                s_956_w renderstate$offsettexturingstate = (s_956_w)p_equals_1_;
                return Float.compare(renderstate$offsettexturingstate.H_1990_U, this.H_1990_U) == 0 && Float.compare(renderstate$offsettexturingstate.N_2525_X, this.N_2525_X) == 0;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return CompareUtils.hash(this.H_1990_U, this.N_2525_X);
        }
    }

    static class J_1907_R
    extends E_270_p {
        private final boolean H_1990_U;

        public J_1907_R(String p_i225975_1_, Runnable p_i225975_2_, Runnable p_i225975_3_, boolean p_i225975_4_) {
            super(p_i225975_1_, p_i225975_2_, p_i225975_3_);
            this.H_1990_U = p_i225975_4_;
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                J_1907_R renderstate$booleanstate = (J_1907_R)p_equals_1_;
                return this.H_1990_U == renderstate$booleanstate.H_1990_U;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Boolean.hashCode(this.H_1990_U);
        }

        @Override
        public String toString() {
            return this.n_1700_B + "[" + this.H_1990_U + "]";
        }
    }
}




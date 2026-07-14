/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nullable;
import lightning.product.B_4830_U;
import lightning.product.D_4792_h;
import lightning.product.AnimationFrame;
import lightning.product.L_3848_p;
import lightning.product.N_1972_P;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.g_1477_d;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.o_3091_w;
import lightning.product.CrashReportCategory;
import lightning.product.SpriteCoordinateExpander;
import lightning.product.y_4387_I;
import net.minecraftforge.client.extensions.IForgeTextureAtlasSprite;
import net.optifine.Config;
import net.optifine.SmartAnimations;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersTextureType;
import net.optifine.texture.IColorBlender;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;

public class B_3871_I
implements AutoCloseable,
IForgeTextureAtlasSprite {
    private final L_3848_p M_182_A;
    private final n_1700_B t_1786_h;
    private final B_4830_U multiplayerClientSuggestionProvider;
    protected final i_2518_W[] n_1700_B;
    private final int[] w_1457_N;
    private final int[] Y_601_j;
    @Nullable
    private final J_1907_R Y_259_p;
    private final int Q_2552_b;
    private final int C_2741_M;
    private final float k_2293_S;
    private final float q_2307_F;
    private final float Z_875_P;
    private final float c_3005_b;
    private int H_2857_Y;
    private int A_4115_X;
    private int Y_1740_V = -1;
    public float J_1907_R;
    public float R_4764_Y;
    public int G_564_y;
    public int P_1922_E;
    public int u_1723_Y = -1;
    public B_3871_I v_4262_N = null;
    public boolean w_1484_f = false;
    public static final String t_148_a = ".sprite_single";
    public int s_956_w = 0;
    public B_3871_I u_2550_I = null;
    public B_3871_I M_588_G = null;
    public ShadersTextureType P_4830_p = null;
    public B_3871_I h_1847_R = null;
    public boolean Q_4569_t = false;
    private int t_4043_B = -1;
    private boolean x_607_J = false;
    private boolean e_4240_b = false;
    private boolean n_3318_d;
    private boolean d_2427_y;
    private boolean z_1737_N;
    private ResourceManager v_4276_D;
    private static final g_2336_b d_2461_k = new g_2336_b("textures/atlas/blocks.png");

    public B_3871_I(g_2336_b p_i242114_1_) {
        this.M_182_A = null;
        this.t_1786_h = new n_1700_B(p_i242114_1_, 0, 0, null);
        this.multiplayerClientSuggestionProvider = null;
        this.n_1700_B = null;
        this.w_1457_N = new int[0];
        this.Y_601_j = new int[0];
        this.Y_259_p = null;
        this.Q_2552_b = 0;
        this.C_2741_M = 0;
        this.k_2293_S = 0.0f;
        this.q_2307_F = 0.0f;
        this.Z_875_P = 0.0f;
        this.c_3005_b = 0.0f;
    }

    private B_3871_I(B_3871_I p_i242115_1_) {
        this.M_182_A = p_i242115_1_.M_182_A;
        n_1700_B textureatlassprite$info = p_i242115_1_.t_1786_h;
        g_2336_b resourcelocation = textureatlassprite$info.n_1700_B();
        g_2336_b resourcelocation1 = new g_2336_b(resourcelocation.R_4764_Y(), resourcelocation.J_1907_R() + t_148_a);
        int i = textureatlassprite$info.J_1907_R();
        int j = textureatlassprite$info.R_4764_Y();
        B_4830_U animationmetadatasection = textureatlassprite$info.G_564_y();
        this.t_1786_h = new n_1700_B(resourcelocation1, i, j, animationmetadatasection);
        this.multiplayerClientSuggestionProvider = p_i242115_1_.multiplayerClientSuggestionProvider;
        this.e_4240_b = true;
        this.n_1700_B = p_i242115_1_.n_1700_B;
        this.w_1457_N = p_i242115_1_.w_1457_N;
        this.Y_601_j = p_i242115_1_.Y_601_j;
        this.Y_259_p = p_i242115_1_.Y_259_p != null ? new J_1907_R(p_i242115_1_.Y_259_p.J_1907_R) : null;
        this.Q_2552_b = 0;
        this.C_2741_M = 0;
        this.k_2293_S = 0.0f;
        this.q_2307_F = 1.0f;
        this.Z_875_P = 0.0f;
        this.c_3005_b = 1.0f;
        this.H_2857_Y = p_i242115_1_.H_2857_Y;
        this.A_4115_X = p_i242115_1_.A_4115_X;
        this.Y_1740_V = p_i242115_1_.Y_1740_V;
        this.J_1907_R = p_i242115_1_.J_1907_R;
        this.R_4764_Y = p_i242115_1_.R_4764_Y;
        this.G_564_y = p_i242115_1_.G_564_y;
        this.P_1922_E = p_i242115_1_.P_1922_E;
        this.w_1484_f = true;
        this.s_956_w = p_i242115_1_.s_956_w;
        this.t_4043_B = p_i242115_1_.t_4043_B;
        this.x_607_J = p_i242115_1_.x_607_J;
    }

    protected B_3871_I(L_3848_p atlasTextureIn, n_1700_B spriteInfoIn, int mipmapLevelsIn, int atlasWidthIn, int atlasHeightIn, int xIn, int yIn, i_2518_W imageIn) {
        this(atlasTextureIn, spriteInfoIn, mipmapLevelsIn, atlasWidthIn, atlasHeightIn, xIn, yIn, imageIn, null);
    }

    protected B_3871_I(L_3848_p p_i242116_1_, n_1700_B p_i242116_2_, int p_i242116_3_, int p_i242116_4_, int p_i242116_5_, int p_i242116_6_, int p_i242116_7_, i_2518_W p_i242116_8_, ShadersTextureType p_i242116_9_) {
        int k;
        i_2518_W nativeimage;
        this.M_182_A = p_i242116_1_;
        B_4830_U animationmetadatasection = p_i242116_2_.G_564_y;
        int i = p_i242116_2_.J_1907_R;
        int j = p_i242116_2_.R_4764_Y;
        this.Q_2552_b = p_i242116_6_;
        this.C_2741_M = p_i242116_7_;
        this.k_2293_S = (float)p_i242116_6_ / (float)p_i242116_4_;
        this.q_2307_F = (float)(p_i242116_6_ + i) / (float)p_i242116_4_;
        this.Z_875_P = (float)p_i242116_7_ / (float)p_i242116_5_;
        this.c_3005_b = (float)(p_i242116_7_ + j) / (float)p_i242116_5_;
        if (p_i242116_2_.P_1922_E > 1.0 && (nativeimage = TextureUtils.scaleImage(p_i242116_8_, k = (int)Math.round((double)p_i242116_8_.n_1700_B() * p_i242116_2_.P_1922_E))) != p_i242116_8_) {
            p_i242116_8_.close();
            p_i242116_8_ = nativeimage;
        }
        this.P_4830_p = p_i242116_9_;
        IColorBlender icolorblender = this.M_182_A.n_1700_B(this.P_4830_p);
        if (this.P_4830_p == null && !p_i242116_2_.n_1700_B().J_1907_R().endsWith("_leaves")) {
            this.n_1700_B(p_i242116_8_);
        }
        i_2518_W nativeimage1 = p_i242116_8_;
        int l = p_i242116_8_.n_1700_B() / animationmetadatasection.J_1907_R(i);
        int i1 = p_i242116_8_.J_1907_R() / animationmetadatasection.n_1700_B(j);
        if (animationmetadatasection.n_1700_B() > 0) {
            int j1 = (Integer)animationmetadatasection.G_564_y().stream().max(Integer::compareTo).get() + 1;
            this.w_1457_N = new int[j1];
            this.Y_601_j = new int[j1];
            Arrays.fill(this.w_1457_N, -1);
            Arrays.fill(this.Y_601_j, -1);
            for (int k1 : animationmetadatasection.G_564_y()) {
                int i2;
                if (k1 >= l * i1) {
                    throw new RuntimeException("invalid frameindex " + k1);
                }
                int l1 = k1 / l;
                this.w_1457_N[k1] = i2 = k1 % l;
                this.Y_601_j[k1] = l1;
            }
        } else {
            ArrayList list = Lists.newArrayList();
            int j2 = l * i1;
            this.w_1457_N = new int[j2];
            this.Y_601_j = new int[j2];
            for (int k2 = 0; k2 < i1; ++k2) {
                int l2 = 0;
                while (l2 < l) {
                    int i3 = k2 * l + l2;
                    this.w_1457_N[i3] = l2++;
                    this.Y_601_j[i3] = k2;
                    list.add(new AnimationFrame(i3, -1));
                }
            }
            animationmetadatasection = new B_4830_U(list, i, j, animationmetadatasection.J_1907_R(), animationmetadatasection.R_4764_Y());
        }
        this.t_1786_h = new n_1700_B(p_i242116_2_.n_1700_B, i, j, animationmetadatasection);
        this.multiplayerClientSuggestionProvider = animationmetadatasection;
        try {
            try {
                this.n_1700_B = y_4387_I.n_1700_B(p_i242116_8_, p_i242116_3_, icolorblender);
            }
            catch (Throwable throwable) {
                n_3236_c crashreport1 = n_3236_c.n_1700_B(throwable, "Generating mipmaps for frame");
                CrashReportCategory crashreportcategory1 = crashreport1.n_1700_B("Frame being iterated");
                crashreportcategory1.n_1700_B("First frame", () -> {
                    StringBuilder stringbuilder = new StringBuilder();
                    if (stringbuilder.length() > 0) {
                        stringbuilder.append(", ");
                    }
                    stringbuilder.append(nativeimage1.n_1700_B()).append("x").append(nativeimage1.J_1907_R());
                    return stringbuilder.toString();
                });
                throw new ReportedException(crashreport1);
            }
        }
        catch (Throwable throwable11) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable11, "Applying mipmap");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Sprite being mipmapped");
            crashreportcategory.n_1700_B("Sprite name", () -> this.s_956_w().toString());
            crashreportcategory.n_1700_B("Sprite size", () -> this.G_564_y() + " x " + this.P_1922_E());
            crashreportcategory.n_1700_B("Sprite frames", () -> this.M_588_G() + " frames");
            crashreportcategory.n_1700_B("Mipmap levels", p_i242116_3_);
            throw new ReportedException(crashreport);
        }
        this.Y_259_p = animationmetadatasection.R_4764_Y() ? new J_1907_R(p_i242116_2_, p_i242116_3_) : null;
        this.s_956_w = p_i242116_3_;
        this.J_1907_R = Math.min(this.k_2293_S, this.q_2307_F);
        this.R_4764_Y = Math.min(this.Z_875_P, this.c_3005_b);
        this.G_564_y = p_i242116_4_;
        this.P_1922_E = p_i242116_5_;
    }

    private void J_1907_R(int index) {
        int i = this.w_1457_N[index] * this.t_1786_h.J_1907_R;
        int j = this.Y_601_j[index] * this.t_1786_h.R_4764_Y;
        this.n_1700_B(i, j, this.n_1700_B);
    }

    private void n_1700_B(int xOffsetIn, int yOffsetIn, i_2518_W[] framesIn) {
        boolean flag = false;
        boolean flag1 = this.w_1484_f;
        for (int i = 0; i < framesIn.length && this.G_564_y() >> i > 0 && this.P_1922_E() >> i > 0; ++i) {
            framesIn[i].n_1700_B(i, this.Q_2552_b >> i, this.C_2741_M >> i, xOffsetIn >> i, yOffsetIn >> i, this.t_1786_h.J_1907_R >> i, this.t_1786_h.R_4764_Y >> i, flag, flag1, framesIn.length > 1, false);
        }
    }

    public int G_564_y() {
        return this.t_1786_h.J_1907_R;
    }

    public int P_1922_E() {
        return this.t_1786_h.R_4764_Y;
    }

    public float u_1723_Y() {
        return this.k_2293_S;
    }

    public float v_4262_N() {
        return this.q_2307_F;
    }

    public float n_1700_B(double u) {
        float f = this.q_2307_F - this.k_2293_S;
        return this.k_2293_S + f * (float)u / 16.0f;
    }

    public float w_1484_f() {
        return this.Z_875_P;
    }

    public float t_148_a() {
        return this.c_3005_b;
    }

    public float J_1907_R(double v) {
        float f = this.c_3005_b - this.Z_875_P;
        return this.Z_875_P + f * (float)v / 16.0f;
    }

    public g_2336_b s_956_w() {
        return this.t_1786_h.n_1700_B;
    }

    public L_3848_p u_2550_I() {
        return this.M_182_A;
    }

    public int M_588_G() {
        return this.w_1457_N.length;
    }

    @Override
    public void close() {
        for (i_2518_W nativeimage : this.n_1700_B) {
            if (nativeimage == null) continue;
            nativeimage.close();
        }
        if (this.Y_259_p != null) {
            this.Y_259_p.close();
        }
        if (this.v_4262_N != null) {
            // empty if block
        }
        if (this.u_2550_I != null) {
            this.u_2550_I.close();
        }
        if (this.M_588_G != null) {
            this.M_588_G.close();
        }
    }

    public String toString() {
        int i = this.w_1457_N.length;
        return "TextureAtlasSprite{name='" + String.valueOf(this.t_1786_h.n_1700_B) + "', frameCount=" + i + ", x=" + this.Q_2552_b + ", y=" + this.C_2741_M + ", height=" + this.t_1786_h.R_4764_Y + ", width=" + this.t_1786_h.J_1907_R + ", u0=" + this.k_2293_S + ", u1=" + this.q_2307_F + ", v0=" + this.Z_875_P + ", v1=" + this.c_3005_b + "}";
    }

    public boolean n_1700_B(int frameIndex, int pixelX, int pixelY) {
        return (this.n_1700_B[0].n_1700_B(pixelX + this.w_1457_N[frameIndex] * this.t_1786_h.J_1907_R, pixelY + this.Y_601_j[frameIndex] * this.t_1786_h.R_4764_Y) >> 24 & 0xFF) == 0;
    }

    public void P_4830_p() {
        this.J_1907_R(0);
    }

    private float n_1700_B() {
        float f = (float)this.t_1786_h.J_1907_R / (this.q_2307_F - this.k_2293_S);
        float f1 = (float)this.t_1786_h.R_4764_Y / (this.c_3005_b - this.Z_875_P);
        return Math.max(f1, f);
    }

    public float h_1847_R() {
        float defaultValue = 4.0f / this.n_1700_B();
        float newS = B_3871_I.n_1700_B(this.u_2550_I(), defaultValue, defaultValue);
        return newS != -1.0f ? newS : defaultValue;
    }

    public static float n_1700_B(L_3848_p atlas, float defaultValue, float returnValue) {
        if (atlas.R_4764_Y().equals(d_2461_k) && defaultValue == returnValue) {
            return 0.0f;
        }
        return -1.0f;
    }

    public void Q_4569_t() {
        if (this.multiplayerClientSuggestionProvider != null) {
            boolean bl = this.x_607_J = SmartAnimations.isActive() ? SmartAnimations.isSpriteRendered(this) : true;
            if (this.multiplayerClientSuggestionProvider.n_1700_B() <= 1) {
                this.x_607_J = false;
            }
            if (this.v_4262_N != null && this.v_4262_N.e_4240_b) {
                this.v_4262_N.A_4115_X = this.A_4115_X;
                this.v_4262_N.H_2857_Y = this.H_2857_Y;
            }
            if (this.u_2550_I != null && this.u_2550_I.e_4240_b) {
                this.u_2550_I.A_4115_X = this.A_4115_X;
                this.u_2550_I.H_2857_Y = this.H_2857_Y;
            }
            if (this.M_588_G != null && this.M_588_G.e_4240_b) {
                this.M_588_G.A_4115_X = this.A_4115_X;
                this.M_588_G.H_2857_Y = this.H_2857_Y;
            }
            ++this.A_4115_X;
            if (this.A_4115_X >= this.multiplayerClientSuggestionProvider.R_4764_Y(this.H_2857_Y)) {
                int i = this.multiplayerClientSuggestionProvider.G_564_y(this.H_2857_Y);
                int j = this.multiplayerClientSuggestionProvider.n_1700_B() == 0 ? this.M_588_G() : this.multiplayerClientSuggestionProvider.n_1700_B();
                this.H_2857_Y = (this.H_2857_Y + 1) % j;
                this.A_4115_X = 0;
                int k = this.multiplayerClientSuggestionProvider.G_564_y(this.H_2857_Y);
                if (!this.x_607_J) {
                    return;
                }
                if (i != k && k >= 0 && k < this.M_588_G()) {
                    this.J_1907_R(k);
                }
            } else if (this.Y_259_p != null) {
                if (!this.x_607_J) {
                    return;
                }
                if (!c_4037_x.J_1907_R()) {
                    c_4037_x.n_1700_B(() -> this.Y_259_p.n_1700_B());
                } else {
                    this.Y_259_p.n_1700_B();
                }
            }
        }
    }

    public boolean M_182_A() {
        return this.multiplayerClientSuggestionProvider.n_1700_B() > 1;
    }

    public D_4792_h n_1700_B(D_4792_h bufferIn) {
        o_3091_w.n_1700_B irendertypebuffer$impl;
        if (this.s_956_w() == TextureUtils.LOCATION_SPRITE_EMPTY && (irendertypebuffer$impl = bufferIn.getRenderTypeBuffer()) != null) {
            return irendertypebuffer$impl.G_564_y();
        }
        return new SpriteCoordinateExpander(bufferIn, this);
    }

    public int t_1786_h() {
        return this.Y_1740_V;
    }

    public void n_1700_B(CounterInt p_updateIndexInMap_1_) {
        if (this.Y_1740_V < 0) {
            B_3871_I textureatlassprite;
            if (this.M_182_A != null && (textureatlassprite = this.M_182_A.R_4764_Y(this.s_956_w())) != null) {
                this.Y_1740_V = textureatlassprite.t_1786_h();
            }
            if (this.Y_1740_V < 0) {
                this.Y_1740_V = p_updateIndexInMap_1_.nextValue();
            }
        }
    }

    public int multiplayerClientSuggestionProvider() {
        return this.t_4043_B;
    }

    public void n_1700_B(int p_setAnimationIndex_1_) {
        this.t_4043_B = p_setAnimationIndex_1_;
        if (this.v_4262_N != null) {
            this.v_4262_N.n_1700_B(p_setAnimationIndex_1_);
        }
        if (this.u_2550_I != null) {
            this.u_2550_I.n_1700_B(p_setAnimationIndex_1_);
        }
        if (this.M_588_G != null) {
            this.M_588_G.n_1700_B(p_setAnimationIndex_1_);
        }
    }

    public boolean w_1457_N() {
        return this.x_607_J;
    }

    private void n_1700_B(i_2518_W p_fixTransparentColor_1_) {
        int[] aint = new int[p_fixTransparentColor_1_.n_1700_B() * p_fixTransparentColor_1_.J_1907_R()];
        p_fixTransparentColor_1_.w_1484_f().get(aint);
        this.n_1700_B(aint);
        p_fixTransparentColor_1_.w_1484_f().put(aint);
    }

    private void n_1700_B(int[] p_fixTransparentColor_1_) {
        if (p_fixTransparentColor_1_ != null) {
            long i = 0L;
            long j = 0L;
            long k = 0L;
            long l = 0L;
            for (int i1 = 0; i1 < p_fixTransparentColor_1_.length; ++i1) {
                int j1 = p_fixTransparentColor_1_[i1];
                int k1 = j1 >> 24 & 0xFF;
                if (k1 < 16) continue;
                int l1 = j1 >> 16 & 0xFF;
                int i2 = j1 >> 8 & 0xFF;
                int j2 = j1 & 0xFF;
                i += (long)l1;
                j += (long)i2;
                k += (long)j2;
                ++l;
            }
            if (l > 0L) {
                int l2 = (int)(i / l);
                int i3 = (int)(j / l);
                int j3 = (int)(k / l);
                int k3 = l2 << 16 | i3 << 8 | j3;
                for (int l3 = 0; l3 < p_fixTransparentColor_1_.length; ++l3) {
                    int i4 = p_fixTransparentColor_1_[l3];
                    int k2 = i4 >> 24 & 0xFF;
                    if (k2 > 16) continue;
                    p_fixTransparentColor_1_[l3] = k3;
                }
            }
        }
    }

    public double n_1700_B(float p_getSpriteU16_1_) {
        float f = this.q_2307_F - this.k_2293_S;
        return (p_getSpriteU16_1_ - this.k_2293_S) / f * 16.0f;
    }

    public double J_1907_R(float p_getSpriteV16_1_) {
        float f = this.c_3005_b - this.Z_875_P;
        return (p_getSpriteV16_1_ - this.Z_875_P) / f * 16.0f;
    }

    public void Y_601_j() {
        if (this.u_1723_Y < 0) {
            this.u_1723_Y = N_1972_P.n_1700_B();
            N_1972_P.n_1700_B(this.u_1723_Y, this.s_956_w, this.G_564_y(), this.P_1922_E());
            boolean flag = this.M_182_A.J_1907_R(this.P_4830_p);
            if (flag) {
                TextureUtils.applyAnisotropicLevel();
            } else {
                X_933_l.n_1700_B(3553, 34046, 1.0f);
                int i = this.s_956_w > 0 ? 9984 : 9728;
                X_933_l.J_1907_R(3553, 10241, i);
                X_933_l.J_1907_R(3553, 10240, 9728);
            }
        }
        TextureUtils.bindTexture(this.u_1723_Y);
    }

    public void Y_259_p() {
        if (this.u_1723_Y >= 0) {
            N_1972_P.n_1700_B(this.u_1723_Y);
            this.u_1723_Y = -1;
        }
    }

    public float R_4764_Y(float p_toSingleU_1_) {
        float f = (float)this.G_564_y / (float)this.G_564_y();
        return (p_toSingleU_1_ -= this.J_1907_R) * f;
    }

    public float G_564_y(float p_toSingleV_1_) {
        float f = (float)this.P_1922_E / (float)this.P_1922_E();
        return (p_toSingleV_1_ -= this.R_4764_Y) * f;
    }

    public i_2518_W[] Q_2552_b() {
        return this.n_1700_B;
    }

    public B_4830_U C_2741_M() {
        return this.multiplayerClientSuggestionProvider;
    }

    public int k_2293_S() {
        return this.Q_2552_b;
    }

    public int q_2307_F() {
        return this.C_2741_M;
    }

    public float P_1922_E(float p_getUnInterpolatedU_1_) {
        float f = this.q_2307_F - this.k_2293_S;
        return (p_getUnInterpolatedU_1_ - this.k_2293_S) / f * 16.0f;
    }

    public float u_1723_Y(float p_getUnInterpolatedV_1_) {
        float f = this.c_3005_b - this.Z_875_P;
        return (p_getUnInterpolatedV_1_ - this.Z_875_P) / f * 16.0f;
    }

    public B_3871_I Z_875_P() {
        B_3871_I textureatlassprite = new B_3871_I(this);
        textureatlassprite.w_1484_f = true;
        return textureatlassprite;
    }

    public B_3871_I n_1700_B(ShadersTextureType p_makeSpriteShaders_1_, int p_makeSpriteShaders_2_, B_4830_U p_makeSpriteShaders_3_) {
        String s = p_makeSpriteShaders_1_.getSuffix();
        g_2336_b resourcelocation = new g_2336_b(this.s_956_w().R_4764_Y(), this.s_956_w().J_1907_R() + s);
        g_2336_b resourcelocation1 = this.M_182_A.n_1700_B(resourcelocation);
        B_3871_I textureatlassprite = null;
        if (this.v_4276_D.J_1907_R(resourcelocation1)) {
            try (Resource iresource2 = this.v_4276_D.n_1700_B(resourcelocation1);){
                i_2518_W nativeimage1;
                Resource iresource1 = this.v_4276_D.n_1700_B(resourcelocation1);
                g_1477_d pngsizeinfo = new g_1477_d(resourcelocation1.toString(), iresource1.J_1907_R());
                B_4830_U animationmetadatasection = iresource2.n_1700_B(B_4830_U.n_1700_B);
                if (animationmetadatasection == null) {
                    animationmetadatasection = B_4830_U.J_1907_R;
                }
                Pair<Integer, Integer> pair = animationmetadatasection.n_1700_B(pngsizeinfo.n_1700_B, pngsizeinfo.J_1907_R);
                n_1700_B textureatlassprite$info = new n_1700_B(resourcelocation, (Integer)pair.getFirst(), (Integer)pair.getSecond(), animationmetadatasection);
                i_2518_W nativeimage = i_2518_W.n_1700_B(iresource2.J_1907_R());
                if (nativeimage.n_1700_B() != this.G_564_y() && (nativeimage1 = TextureUtils.scaleImage(nativeimage, this.G_564_y())) != nativeimage) {
                    double d0 = 1.0 * (double)this.G_564_y() / (double)nativeimage.n_1700_B();
                    nativeimage.close();
                    nativeimage = nativeimage1;
                    textureatlassprite$info = new n_1700_B(resourcelocation, (int)((double)((Integer)pair.getFirst()).intValue() * d0), (int)((double)((Integer)pair.getSecond()).intValue() * d0), animationmetadatasection);
                }
                textureatlassprite = new B_3871_I(this.M_182_A, textureatlassprite$info, this.s_956_w, this.G_564_y, this.P_1922_E, this.Q_2552_b, this.C_2741_M, nativeimage, p_makeSpriteShaders_1_);
            }
            catch (IOException iresource2) {
                // empty catch block
            }
        }
        if (textureatlassprite == null) {
            i_2518_W nativeimage2 = new i_2518_W(this.G_564_y(), this.P_1922_E(), false);
            int i = TextureUtils.toAbgr(p_makeSpriteShaders_2_);
            nativeimage2.n_1700_B(0, 0, nativeimage2.n_1700_B(), nativeimage2.J_1907_R(), i);
            n_1700_B textureatlassprite$info1 = new n_1700_B(resourcelocation, this.G_564_y(), this.P_1922_E(), B_4830_U.J_1907_R);
            textureatlassprite = new B_3871_I(this.M_182_A, textureatlassprite$info1, this.s_956_w, this.G_564_y, this.P_1922_E, this.Q_2552_b, this.C_2741_M, nativeimage2, p_makeSpriteShaders_1_);
        }
        if (this.n_3318_d && this.z_1737_N && !this.w_1484_f) {
            textureatlassprite.v_4262_N = textureatlassprite.Z_875_P();
        }
        textureatlassprite.e_4240_b = B_3871_I.n_1700_B(textureatlassprite.multiplayerClientSuggestionProvider, p_makeSpriteShaders_3_);
        return textureatlassprite;
    }

    public boolean c_3005_b() {
        return this.n_3318_d;
    }

    private void n_1700_B(boolean p_setTerrain_1_) {
        this.n_3318_d = p_setTerrain_1_;
        this.z_1737_N = false;
        this.d_2427_y = false;
        if (this.v_4262_N != null) {
            this.Y_259_p();
            this.v_4262_N = null;
        }
        if (this.u_2550_I != null) {
            if (this.u_2550_I.v_4262_N != null) {
                this.u_2550_I.Y_259_p();
            }
            this.u_2550_I.close();
            this.u_2550_I = null;
        }
        if (this.M_588_G != null) {
            if (this.M_588_G.v_4262_N != null) {
                this.M_588_G.Y_259_p();
            }
            this.M_588_G.close();
            this.M_588_G = null;
        }
        this.z_1737_N = Config.isMultiTexture();
        this.d_2427_y = Config.isShaders();
        if (this.n_3318_d && this.z_1737_N && !this.w_1484_f) {
            this.v_4262_N = this.Z_875_P();
        }
        if (this.d_2427_y && !this.w_1484_f) {
            if (this.u_2550_I == null && Shaders.configNormalMap) {
                this.u_2550_I = this.n_1700_B(ShadersTextureType.NORMAL, -8421377, this.multiplayerClientSuggestionProvider);
            }
            if (this.M_588_G == null && Shaders.configSpecularMap) {
                this.M_588_G = this.n_1700_B(ShadersTextureType.SPECULAR, 0, this.multiplayerClientSuggestionProvider);
            }
        }
    }

    private static boolean n_1700_B(B_4830_U p_matchesTiming_0_, B_4830_U p_matchesTiming_1_) {
        if (p_matchesTiming_0_ == p_matchesTiming_1_) {
            return true;
        }
        if (p_matchesTiming_0_ != null && p_matchesTiming_1_ != null) {
            if (p_matchesTiming_0_.J_1907_R() != p_matchesTiming_1_.J_1907_R()) {
                return false;
            }
            if (p_matchesTiming_0_.R_4764_Y() != p_matchesTiming_1_.R_4764_Y()) {
                return false;
            }
            if (p_matchesTiming_0_.n_1700_B() != p_matchesTiming_1_.n_1700_B()) {
                return false;
            }
            for (int i = 0; i < p_matchesTiming_0_.n_1700_B(); ++i) {
                if (p_matchesTiming_0_.R_4764_Y(i) == p_matchesTiming_1_.R_4764_Y(i)) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public void n_1700_B(ResourceManager p_update_1_) {
        this.v_4276_D = p_update_1_;
        this.n_1700_B(this.M_182_A.s_956_w());
        this.n_1700_B(this.M_182_A.t_148_a());
    }

    public int J_1907_R(int p_getPixelRGBA_1_, int p_getPixelRGBA_2_, int p_getPixelRGBA_3_) {
        return this.n_1700_B[0].n_1700_B(p_getPixelRGBA_2_ + this.w_1457_N[p_getPixelRGBA_1_] * this.G_564_y(), p_getPixelRGBA_3_ + this.Y_601_j[p_getPixelRGBA_1_] * this.P_1922_E());
    }

    public static final class n_1700_B {
        private final g_2336_b n_1700_B;
        private int J_1907_R;
        private int R_4764_Y;
        private final B_4830_U G_564_y;
        private double P_1922_E = 1.0;

        public n_1700_B(g_2336_b locationIn, int widthIn, int heightIn, B_4830_U animationMetadataIn) {
            this.n_1700_B = locationIn;
            this.J_1907_R = widthIn;
            this.R_4764_Y = heightIn;
            this.G_564_y = animationMetadataIn;
        }

        public g_2336_b n_1700_B() {
            return this.n_1700_B;
        }

        public int J_1907_R() {
            return this.J_1907_R;
        }

        public int R_4764_Y() {
            return this.R_4764_Y;
        }

        public void n_1700_B(int p_setSpriteWidth_1_) {
            this.J_1907_R = p_setSpriteWidth_1_;
        }

        public void J_1907_R(int p_setSpriteHeight_1_) {
            this.R_4764_Y = p_setSpriteHeight_1_;
        }

        public B_4830_U G_564_y() {
            return this.G_564_y;
        }

        public double P_1922_E() {
            return this.P_1922_E;
        }

        public void n_1700_B(double p_setScaleFactor_1_) {
            this.P_1922_E = p_setScaleFactor_1_;
        }

        public String toString() {
            return String.valueOf(this.n_1700_B) + ", width: " + this.J_1907_R + ", height: " + this.R_4764_Y + ", frames: " + this.G_564_y.n_1700_B() + ", scale: " + this.P_1922_E;
        }
    }

    final class J_1907_R
    implements AutoCloseable {
        private final i_2518_W[] J_1907_R;

        private J_1907_R(i_2518_W[] p_i242101_2_) {
            this.J_1907_R = p_i242101_2_;
        }

        private J_1907_R(n_1700_B spriteInfoIn, int mipmapLevelsIn) {
            this.J_1907_R = new i_2518_W[mipmapLevelsIn + 1];
            for (int i = 0; i < this.J_1907_R.length; ++i) {
                int j = spriteInfoIn.J_1907_R >> i;
                int k = spriteInfoIn.R_4764_Y >> i;
                if (this.J_1907_R[i] != null) continue;
                this.J_1907_R[i] = new i_2518_W(j, k, false);
            }
        }

        private void n_1700_B() {
            int j;
            int k;
            double d0 = 1.0 - (double)B_3871_I.this.A_4115_X / (double)B_3871_I.this.multiplayerClientSuggestionProvider.R_4764_Y(B_3871_I.this.H_2857_Y);
            int i = B_3871_I.this.multiplayerClientSuggestionProvider.G_564_y(B_3871_I.this.H_2857_Y);
            if (i != (k = B_3871_I.this.multiplayerClientSuggestionProvider.G_564_y((B_3871_I.this.H_2857_Y + 1) % (j = B_3871_I.this.multiplayerClientSuggestionProvider.n_1700_B() == 0 ? B_3871_I.this.M_588_G() : B_3871_I.this.multiplayerClientSuggestionProvider.n_1700_B()))) && k >= 0 && k < B_3871_I.this.M_588_G()) {
                if (!B_3871_I.this.w_1484_f) {
                    for (int l = 0; l < this.J_1907_R.length; ++l) {
                        int i1 = B_3871_I.this.t_1786_h.J_1907_R >> l;
                        int j1 = B_3871_I.this.t_1786_h.R_4764_Y >> l;
                        for (int k1 = 0; k1 < j1; ++k1) {
                            for (int l1 = 0; l1 < i1; ++l1) {
                                int i2 = this.n_1700_B(i, l, l1, k1);
                                int j2 = this.n_1700_B(k, l, l1, k1);
                                int k2 = this.n_1700_B(d0, i2 >> 16 & 0xFF, j2 >> 16 & 0xFF);
                                int l2 = this.n_1700_B(d0, i2 >> 8 & 0xFF, j2 >> 8 & 0xFF);
                                int i3 = this.n_1700_B(d0, i2 & 0xFF, j2 & 0xFF);
                                this.J_1907_R[l].n_1700_B(l1, k1, i2 & 0xFF000000 | k2 << 16 | l2 << 8 | i3);
                            }
                        }
                    }
                }
                B_3871_I.this.n_1700_B(0, 0, this.J_1907_R);
            }
        }

        private int n_1700_B(int frameIndex, int mipmapLevel, int x, int y) {
            return B_3871_I.this.n_1700_B[mipmapLevel].n_1700_B(x + (B_3871_I.this.w_1457_N[frameIndex] * B_3871_I.this.t_1786_h.J_1907_R >> mipmapLevel), y + (B_3871_I.this.Y_601_j[frameIndex] * B_3871_I.this.t_1786_h.R_4764_Y >> mipmapLevel));
        }

        private int n_1700_B(double ratio, int val1, int val2) {
            return (int)(ratio * (double)val1 + (1.0 - ratio) * (double)val2);
        }

        @Override
        public void close() {
            for (i_2518_W nativeimage : this.J_1907_R) {
                if (nativeimage == null) continue;
                nativeimage.close();
            }
        }
    }
}


